package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
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
    TableLayout tbSpending;
    SpendingMonthActivityPresenter mPresenter;
    ArrayList<Spending> spending;
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

        while (tbSpending.getChildCount() > 1) {
            int i = tbSpending.getChildCount() - 1;
            tbSpending.removeViewAt(i--);
        }

        for( int k = spending.size() -1; k > 0; k-- ) {
            int i = 1;

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView txtSpending = new TextView(this);
            txtSpending.setText(String.valueOf( spending.get(k).getAmount()));

            TextView txtDate = new TextView(this);
            txtDate.setText( spending.get(k).getEmissionDate() );

            TextView txtCategory = new TextView(this);
            txtCategory.setPadding(40, 0, 0, 0);
            txtCategory.setText( spending.get(k).getCategory() );

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
        total = 0.0f;

        for ( Spending sp: spending ){
            total += sp.getAmount();
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        edtTotal.setText(String.valueOf( df.format(total) ));
    }

}
