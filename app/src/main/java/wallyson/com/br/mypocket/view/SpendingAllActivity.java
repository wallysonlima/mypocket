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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.Spending;
import wallyson.com.br.mypocket.model.User;
import wallyson.com.br.mypocket.presenter.SpendingAllInterface;
import wallyson.com.br.mypocket.presenter.SpendingAllPresenter;

public class SpendingAllActivity extends AppCompatActivity implements SpendingAllInterface {
    Spinner spnMonth;
    EditText edtTotal;
    Button btnSendEmail;
    TableLayout tbSpending;
    SpendingAllPresenter mPresenter;
    ArrayList<Spending> spending;
    String nameFile;
    UserDao userDao;
    User user;

    private String[] monthYear = {
            getResources().getString(R.string.january),
            getResources().getString(R.string.february),
            getResources().getString(R.string.march),
            getResources().getString(R.string.april),
            getResources().getString(R.string.may),
            getResources().getString(R.string.june),
            getResources().getString(R.string.july),
            getResources().getString(R.string.august),
            getResources().getString(R.string.september),
            getResources().getString(R.string.october),
            getResources().getString(R.string.november),
            getResources().getString(R.string.december)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_all);

        spnMonth = (Spinner) findViewById(R.id.spnMonth);
        edtTotal = (EditText) findViewById(R.id.edtTotal);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        tbSpending = (TableLayout) findViewById(R.id.tableSpending);
        mPresenter = new SpendingAllPresenter(this, this.getApplicationContext());
        spending = new ArrayList<>();
        addMonthSpinner();
        userDao = new UserDao(this);
        user = userDao.selectUser();

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
            txtDate.setText( sp.getCategory() );

            row.addView(txtSpending);
            row.addView(txtDate);
            row.addView(txtCategory);
            tbSpending.addView(row, i);
            i++;
        }

    }

    public String getMonthYear() {
        Date date = new Date(System.currentTimeMillis());
        GregorianCalendar dateCal;
        dateCal = new GregorianCalendar();
        dateCal.setTime(date);
        return spnMonth.getSelectedItemPosition() + "/" + String.valueOf( dateCal.get(Calendar.YEAR) );
    }

    public void setTotalAmount() {
        float total = 0.0f;

        for ( Spending sp: spending ){
            total += sp.getAmount();
        }

        edtTotal.setText(String.valueOf(total));
    }

    private void saveSpendingFile() {

        nameFile = user.getName() + " " + getMonthYear() + ".txt";

        try {
            String allSpending = user.getName() + " " +
                    getResources().getString(R.string.allSpendingMonth) + getMonthYear() + "\n\n\n";

            for( Spending sp: spending ) {
                allSpending +=
                        getResources().getString(R.string.spending) + " : " + String.valueOf( sp.getAmount() ) + " " +
                                getResources().getString(R.string.emissionDate) + " : " + sp.getEmissionDate() + " " +
                                getResources().getString(R.string.category) + " : " + sp.getCategory() + "\n";
            }

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
        emailIntent.setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()}).putExtra(android.content.Intent.EXTRA_SUBJECT, nameFile).putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.allSpendingMonth));
        String targetFilePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "tmp" + File.separator + nameFile;
        Uri attachmentUri = Uri.parse(targetFilePath);
        emailIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.parse("file://" + attachmentUri));
    }

}
