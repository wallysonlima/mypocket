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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.model.Spending;
import wallyson.com.br.mypocket.presenter.SpendingAllInterface;
import wallyson.com.br.mypocket.presenter.SpendingAllPresenter;

public class SpendingAllActivity extends AppCompatActivity implements SpendingAllInterface {
    Spinner spnMonth;
    EditText edtTotal;
    TableLayout tbSpending;
    SpendingAllPresenter mPresenter;
    ArrayList<Spending> spending;

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
        tbSpending = (TableLayout) findViewById(R.id.tableSpending);
        mPresenter = new SpendingAllPresenter(this, this.getApplicationContext());
        spending = new ArrayList<>();
        addMonthSpinner();

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addSpendingTableLayout();
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
        spending = mPresenter.getSpendingForMonth();

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

    public int getPositionSpinner() {
        return spnMonth.getSelectedItemPosition();
    }


}
