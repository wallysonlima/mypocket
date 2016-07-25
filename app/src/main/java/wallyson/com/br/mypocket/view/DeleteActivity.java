package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.DeleteActivityPresenter;
import wallyson.com.br.mypocket.presenter.DeleteInterface;

public class DeleteActivity extends AppCompatActivity implements DeleteInterface {
    private Spinner spnAccount, spnCard, spnSpending;
    private Button btnDeleteAccount, btnDeleteCard, btnDeleteSpending;
    private DeleteActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mPresenter = new DeleteActivityPresenter(this, this.getApplicationContext());

        spnCard = (Spinner) findViewById(R.id.spnDeleteCard);
        spnAccount = (Spinner) findViewById(R.id.spnDeleteAccount);
        spnSpending = (Spinner) findViewById(R.id.spnDeleteSpending);
        btnDeleteCard = (Button) findViewById(R.id.btnDeleteCard);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        btnDeleteSpending = (Button) findViewById(R.id.btnDeleteSpending);

        addCardSpinner();
        addAccountSpinner();
        addSpendingSpinner();

        btnDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteCard();
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteAccount();
            }
        });

        btnDeleteSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteSpending();
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
        ArrayList<String> arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }

    public void addSpendingSpinner() {
        ArrayList<String> arraySpendingName = mPresenter.getAllSpendingName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arraySpendingName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSpending.setAdapter(adapter);
    }

    public String getCardSpinner() {
        return spnCard.getSelectedItem().toString();
    }

    public String getAccountSpinner() {
        return spnAccount.getSelectedItem().toString();
    }

    public String getSpendingSpinner() {
        return spnSpending.getSelectedItem().toString();
    }

    public void successfullyDeleted() {
        Toast.makeText(DeleteActivity.this, "@String/successfullyDelete", Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(DeleteActivity.this, "@String/DatabaseInsertError", Toast.LENGTH_SHORT).show();
    }

}
