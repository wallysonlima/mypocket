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

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.SpendingActivityPresenter;
import wallyson.com.br.mypocket.presenter.SpendingInterface;

public class SpendingActivity extends AppCompatActivity implements SpendingInterface {
    private EditText description, amount, emissionDate;
    private Spinner spCategory, spAccount, spCard;
    private RadioButton rbDebit, rbCard;
    private Button btnClean, btnSubmit;
    private SpendingActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        description = (EditText) findViewById(R.id.edtDescription);
        amount = (EditText) findViewById(R.id.edtAmount);
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

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.SpendingRegistration();
                mPresenter.Debiting();
            }
        });
    }

    public void clean() {
        description.setText(null);
        amount.setText(null);
        emissionDate.setText(null);
        spCategory.setSelection(0);
        spAccount.setSelection(0);
        spCard.setSelection(0);
        rbDebit.setSelected(true);
    }

    public void addCategorySpinner() {
        String[] arrayCategory = {
                getResources().getString(R.string.autoTransport),
                getResources().getString(R.string.bills),
                getResources().getString(R.string.businessServices),
                getResources().getString(R.string.education),
                getResources().getString(R.string.entertainment),
                getResources().getString(R.string.foodDining),
                getResources().getString(R.string.giftsDonations),
                getResources().getString(R.string.healthFitness),
                getResources().getString(R.string.income),
                getResources().getString(R.string.investments),
                getResources().getString(R.string.kids),
                getResources().getString(R.string.other),
                getResources().getString(R.string.personalCare),
                getResources().getString(R.string.shopping),
                getResources().getString(R.string.taxes),
                getResources().getString(R.string.travel),
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccount.setAdapter(adapter);
    }

    public void addAccountSpinner() {
        String[] arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        String[] arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCard.setAdapter(adapter);
    }

    public String getDescription() {
        return description.getText().toString();
    }

    public Double getAmount() {
        return Double.parseDouble( amount.getText().toString() );
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
        Toast.makeText(SpendingActivity.this, "@String/successfullyRegistration", Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(SpendingActivity.this, "@String/DatabaseInsertError", Toast.LENGTH_SHORT).show();
    }
}
