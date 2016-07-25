package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.model.ConfigurationAccount;
import wallyson.com.br.mypocket.model.ConfigurationCard;
import wallyson.com.br.mypocket.presenter.ConfigurationActivityPresenter;
import wallyson.com.br.mypocket.presenter.ConfigurationInterface;

public class ConfigurationActivity extends AppCompatActivity implements ConfigurationInterface {
    Spinner spnAccount, spnCard;
    EditText edtBalance, edtRenewalAccount, edtCredit, edtRenewalCredit;
    Button btnAlterAccount, btnAlterCredit;
    ConfigurationActivityPresenter mPresenter;
    ConfigurationAccount confAccount;
    ConfigurationCard confCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        spnAccount = (Spinner) findViewById(R.id.spnAccount);
        spnCard = (Spinner) findViewById(R.id.spnCard);
        edtBalance = (EditText) findViewById(R.id.edtBalance);
        edtRenewalAccount = (EditText) findViewById(R.id.edtRenewalAccount);
        edtCredit = (EditText) findViewById(R.id.edtCredit);
        edtRenewalCredit = (EditText) findViewById(R.id.edtRenewalCredit);
        btnAlterAccount = (Button) findViewById(R.id.btnAlterAccount);
        btnAlterCredit = (Button) findViewById(R.id.btnAlterCredit);
        mPresenter = new ConfigurationActivityPresenter(this, this.getApplicationContext());

        //Add elements in Spinner
        addAccountSpinner();
        addCardSpinner();

        spnAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                confAccount = mPresenter.getConfAccount(spnAccount.getSelectedItem().toString());

                if (confAccount != null) {
                    edtRenewalAccount.setText(confAccount.getReceiptDate());
                    edtBalance.setText(confAccount.getBalance().toString());
                } else {
                    edtRenewalAccount.setText("01");
                    edtBalance.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        spnCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                confCard = mPresenter.getConfCard(spnCard.getSelectedItem().toString());

                if ( confCard != null ) {
                    edtRenewalCredit.setText( confCard.getReceiptDate() );
                    edtCredit.setText( confCard.getCredit().toString() );
                } else {
                    edtRenewalCredit.setText("01");
                    edtCredit.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        btnAlterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.registrationConfigurationAccount();
            }
        });


        btnAlterCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.registrationConfigurationCard();
            }
        });
    }

    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAccount.setAdapter(adapter);
    }

     public void addCardSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }

    public String getAccountSpinner() {
        return spnAccount.getSelectedItem().toString();
    }

    public String getBalance() {
        return edtBalance.getText().toString();
    }

    public String getRenewalAccount() {
        return edtRenewalAccount.getText().toString();
    }

    public String getCardSpinner() {
        return spnCard.getSelectedItem().toString();
    }

    public String getCredit() {
        return edtCredit.getText().toString();
    }

    public String getRenewalCredit() {
        return edtRenewalCredit.getText().toString();
    }

    public void updatedSuccessfully() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.configuration_update_successfully), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }

    public void registrationError() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }
}
