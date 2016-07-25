package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Formatter;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.SpendingActivityPresenter;
import wallyson.com.br.mypocket.presenter.SpendingInterface;

public class SpendingActivity extends AppCompatActivity implements SpendingInterface {
    private EditText description, amount, emissionDate;
    private Spinner spCategory, spAccount, spCard;
    private RadioButton rbDebit, rbCard;
    private Button btnClean, btnSubmit;
    private SpendingActivityPresenter mPresenter;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        description = (EditText) findViewById(R.id.edtDescription);
        amount = (EditText) findViewById(R.id.edtBalance);
        emissionDate = (EditText) findViewById(R.id.edtEmissionDate);
        spCategory = (Spinner) findViewById(R.id.spnCategory);
        spAccount = (Spinner) findViewById(R.id.spnAccount);
        spCard = (Spinner) findViewById(R.id.spnCard);
        rbDebit = (RadioButton) findViewById(R.id.rbDebit);
        rbCard = (RadioButton) findViewById(R.id.rbCard);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPresenter = new SpendingActivityPresenter(this, this.getApplicationContext() );

        addCategorySpinner();
        addAccountSpinner();
        addCardSpinner();

        date = new Date(System.currentTimeMillis());
        SimpleDateFormat fo = new SimpleDateFormat("dd/MM/yyyy");
        emissionDate.setText(fo.format(date));
        emissionDate.setEnabled(false);

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( mPresenter.spendingRegistration() )
                    finish();
            }
        });
    }

    public void clean() {
        description.requestFocus();
        description.setText(null);
        amount.setText(null);
        emissionDate.setText(null);
        spCategory.setSelection(0);
        spAccount.setSelection(0);
        spCard.setSelection(0);
        rbDebit.setChecked(true);
    }

    public void addCategorySpinner() {
        final String[] arrayCategory = {
                this.getResources().getString(R.string.auto_transport),
                this.getResources().getString(R.string.bills),
                this.getResources().getString(R.string.business_services),
                this.getResources().getString(R.string.education),
                this.getResources().getString(R.string.entertainment),
                this.getResources().getString(R.string.food_dining),
                this.getResources().getString(R.string.gifts_donations),
                this.getResources().getString(R.string.health_fitness),
                this.getResources().getString(R.string.income),
                this.getResources().getString(R.string.investments),
                this.getResources().getString(R.string.kids),
                this.getResources().getString(R.string.other),
                this.getResources().getString(R.string.personal_care),
                this.getResources().getString(R.string.shopping),
                this.getResources().getString(R.string.taxes),
                this.getResources().getString(R.string.travel)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
    }

    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        ArrayList<String>arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCard.setAdapter(adapter);
    }

    public String getDescription() {
        return description.getText().toString();
    }

    public String getAmount() {
        return amount.getText().toString();
    }

    public String getEmissionDate() {
        return emissionDate.getText().toString();
    }

    public String getCategory() {
        return spCategory.getSelectedItem().toString();
    }

    public String getAccount() {
        return spAccount.getSelectedItem().toString();
    }

    public String getCard() {
        return spCard.getSelectedItem().toString();
    }

    public String getChoose() {
        if( rbCard.isChecked() == true )
            return rbCard.getText().toString();
        else
            return rbDebit.getText().toString();
    }

    public void successfullyInserted() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }

    public void registrationError() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }
}
