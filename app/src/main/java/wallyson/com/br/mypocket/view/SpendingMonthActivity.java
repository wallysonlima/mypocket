package wallyson.com.br.mypocket.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.Spending;
import wallyson.com.br.mypocket.model.User;
import wallyson.com.br.mypocket.presenter.SpendingMonthInterface;
import wallyson.com.br.mypocket.presenter.SpendingMonthActivityPresenter;

public class SpendingMonthActivity extends AppCompatActivity implements SpendingMonthInterface {
    Spinner spnMonth;
    EditText edtTotal;
    Button btnSendEmail;
    TableLayout tbSpending;
    SpendingMonthActivityPresenter mPresenter;
    ArrayList<Spending> spending;
    String nameFile;
    UserDao userDao;
    User user;
    float total;
    private ArrayList<String> monthYear = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_month);

        monthYear.add(getResources().getString(R.string.january));
        monthYear.add(getResources().getString(R.string.february));
        monthYear.add(getResources().getString(R.string.march));
        monthYear.add(getResources().getString(R.string.april));
        monthYear.add(getResources().getString(R.string.may));
        monthYear.add(getResources().getString(R.string.june));
        monthYear.add(getResources().getString(R.string.july));
        monthYear.add(getResources().getString(R.string.august));
        monthYear.add(getResources().getString(R.string.september));
        monthYear.add(getResources().getString(R.string.october));
        monthYear.add(getResources().getString(R.string.november));
        monthYear.add(getResources().getString(R.string.december));

        spnMonth = (Spinner) findViewById(R.id.spnMonth);
        edtTotal = (EditText) findViewById(R.id.edtTotal);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        tbSpending = (TableLayout) findViewById(R.id.tableSpending);
        mPresenter = new SpendingMonthActivityPresenter(this, this.getApplicationContext());
        spending = new ArrayList<>();
        addMonthSpinner();
        userDao = new UserDao(this);
        user = userDao.selectUser();
        total = 0.0f;



        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addSpendingTableLayout();
                setTotalAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFileByEmail();
            }
        });


    }

    public void addMonthSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, monthYear);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(adapter);
    }

    public void addSpendingTableLayout() {
        spending = mPresenter.getSpendingForMonthYear();

        // Order by Category
        Collections.sort(spending, new Comparator<Spending>() {
            @Override
            public int compare(Spending lhs, Spending rhs) {
                return lhs.getCategory().compareTo(rhs.getCategory());
            }
        });


        for( Spending sp: spending ) {
            int i = 1;

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView txtSpending = new TextView(this);
            txtSpending.setText(String.valueOf(sp.getAmount()));

            TextView txtDate = new TextView(this);
            txtDate.setText( sp.getEmissionDate() );

            TextView txtCategory = new TextView(this);
            txtCategory.setPadding(40, 0, 0, 0);
            txtCategory.setText( sp.getCategory() );

            row.addView(txtSpending);
            row.addView(txtDate);
            row.addView(txtCategory);
            tbSpending.addView(row, i);
            i++;
        }

    }

    public String getMonthYear() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        int month = spnMonth.getSelectedItemPosition();
        month++;

        if ( month >= 1 && month <= 9 ) {
            return ( "0" + String.valueOf(month) + "/" + format.format(date) );
        } else {
            return ( String.valueOf(month) + "/" + format.format(date) );
        }
    }

    public void setTotalAmount() {
        for ( Spending sp: spending ){
            total += sp.getAmount();
        }

        edtTotal.setText(String.valueOf(total));
    }

    private void saveSpendingFile() {

        nameFile = user.getName() + " " + getMonthYear() + ".txt";

        try {
            String allSpending = user.getName() + " " +
                    getResources().getString(R.string.all_spending_month) + getMonthYear() + "\n\n\n";

            for( Spending sp: spending ) {
                allSpending +=
                        getResources().getString(R.string.spending) + " : " + String.valueOf( sp.getAmount() ) + " " +
                                getResources().getString(R.string.emission_date) + " : " + sp.getEmissionDate() + " " +
                                getResources().getString(R.string.category) + " : " + sp.getCategory() + "\n";
            }

            allSpending += "\n\nTotal : " + String.valueOf(total);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput(nameFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(allSpending);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    // Send image from PieChart for Email
    public void sendFileByEmail() {
        saveSpendingFile();

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()}).putExtra(android.content.Intent.EXTRA_SUBJECT, nameFile).putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.all_spending_month));
        String targetFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "tmp" + File.separator + nameFile;
        Uri attachmentUri = Uri.parse(targetFilePath);
        emailIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + attachmentUri));
    }

}
