package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

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

}
