package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.DeleteActivityPresenter;
import wallyson.com.br.mypocket.presenter.DeleteInterface;

public class DeleteActivity extends AppCompatActivity implements DeleteInterface {
    Spinner spnAccount, spnCard;
    Button btnDeleteAccount, btnDeleteCard, btnDeleteSpending;
    DeleteActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spnAccount = (Spinner) findViewById(R.id.spnDeleteAccount);
        spnCard = (Spinner) findViewById(R.id.spnDeleteCard);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
        btnDeleteCard = (Button) findViewById(R.id.btnDeleteCard);
        btnDeleteSpending = (Button) findViewById(R.id.btnDeleteSpending);
        addAccountSpinner();
        addCardSpinner();

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteAccount();
            }
        });

        btnDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteCard();
            }
        });
    }

    public void addAccountSpinner() {
        String[] arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        String[] arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }


    public String getAccountSpinner() {
        return spnAccount.getSelectedItem().toString();
    }

    public String getCardSpinner() {
        return spnCard.getSelectedItem().toString();
    }

    public void successfullyDeleted() {
        Toast.makeText(DeleteActivity.this, "@String/successfullyDelete", Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(DeleteActivity.this, "@String/DatabaseInsertError", Toast.LENGTH_SHORT).show();
    }

}
