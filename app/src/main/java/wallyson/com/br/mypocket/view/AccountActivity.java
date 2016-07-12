package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.AccountActivityPresenter;
import wallyson.com.br.mypocket.presenter.AccountInterface;

public class AccountActivity extends AppCompatActivity implements AccountInterface {
    private EditText bankName, balance, receiptDate;
    private Button btnClean, btnSubmit;
    private AccountActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        bankName = (EditText) findViewById(R.id.edtBankName);
        receiptDate = (EditText) findViewById(R.id.edtReceiptDate);
        balance = (EditText) findViewById(R.id.edtBalance);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPresenter = new AccountActivityPresenter(this, this.getApplicationContext() );

        btnClean.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.accountRegistration();
            }
        });
    }

    // Method for clean all editText
    public void clean() {
        bankName.setText(null);
        receiptDate.setText(null);
        balance.setText(null);
    }

    public String getBankName() {
        return bankName.getText().toString();
    }

    public Double getBalance() {
        return Double.parseDouble(balance.getText().toString());
    }

    public String getReceiptDate() {
        return receiptDate.getText().toString();
    }

    public void registrationError() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.registrationError), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.successfullyRegistration), Toast.LENGTH_SHORT).show();
    }

    public void invalidNumber() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.invalidNumber), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(AccountActivity.this, getResources().getString(R.string.DatabaseInsertError), Toast.LENGTH_SHORT).show();
    }
}
