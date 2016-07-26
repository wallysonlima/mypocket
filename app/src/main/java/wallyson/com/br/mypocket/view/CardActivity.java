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
import wallyson.com.br.mypocket.presenter.CardActivityPresenter;
import wallyson.com.br.mypocket.presenter.CardInterface;

public class CardActivity extends AppCompatActivity implements CardInterface {
    private EditText cardName, credit;
    private Spinner bankName, spnRenewalCredit;
    private Button btnClean, btnSubmit;
    private CardActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardName = (EditText) findViewById(R.id.edtCardName);
        credit = (EditText) findViewById(R.id.edtCredit);
        spnRenewalCredit = (Spinner) findViewById(R.id.spnRenewalCredit);
        bankName = (Spinner) findViewById(R.id.spnBankName);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPresenter = new CardActivityPresenter(this, this.getApplicationContext() );

        addRenewalCreditSpinner();
        addElementSpinner();

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter.cardRegistration())
                    finish();
            }
        });
    }

    public void addElementSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankName.setAdapter(adapter);
    }

    public void addRenewalCreditSpinner() {
        ArrayList<String> days = new ArrayList<>();

        for(int i = 1; i <= 31; i++)
            days.add( String.valueOf(i) );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRenewalCredit.setAdapter(adapter);
    }

    public void clean() {
        cardName.requestFocus();
        cardName.setText(null);
        credit.setText(null);
        spnRenewalCredit.setSelection(0);
        bankName.setSelection(0);
    }

    public String getCardName() {
        return cardName.getText().toString();
    }

    public String getCredit() {
        return credit.getText().toString();
    }

    public String getReceiptDate() {
        return spnRenewalCredit.getSelectedItem().toString();
    }

    public String getBankName() {
        return bankName.getSelectedItem().toString();
    }
    
    public void registrationError() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }
}
