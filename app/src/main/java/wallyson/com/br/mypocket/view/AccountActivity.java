package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.AccountActivityPresenter;
import wallyson.com.br.mypocket.presenter.AccountInterface;

public class AccountActivity extends AppCompatActivity implements AccountInterface {
    private EditText bankName, balance;
    private Button btnClean, btnSubmit;
    private AccountActivityPresenter mPresenter;
    private Spinner spnRenewalBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        bankName = (EditText) findViewById(R.id.edtBankName);
        spnRenewalBalance = (Spinner) findViewById(R.id.spnRenewalBalance);
        balance = (EditText) findViewById(R.id.edtBalance);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPresenter = new AccountActivityPresenter(this, this.getApplicationContext() );

        addRenewalBalanceSpinner();

        btnClean.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.accountRegistration() )
                    finish();
            }
        });
    }

    // Method for clean all editText
    public void clean() {
        bankName.requestFocus();
        bankName.setText(null);
        balance.setText(null);
        spnRenewalBalance.setSelection(0);
    }

    public void addRenewalBalanceSpinner() {
        ArrayList<String> days = new ArrayList<>();

        for(int i = 1; i <= 31; i++)
            days.add( String.valueOf(i) );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRenewalBalance.setAdapter(adapter);
    }


    public String getBankName() {
        return bankName.getText().toString();
    }

    public String getBalance() {
        return balance.getText().toString();
    }

    public String getReceiptDate() {
        return spnRenewalBalance.getSelectedItem().toString();
    }

    public void registrationError() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }
}
