
package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.InitialActivityPresenter;
import wallyson.com.br.mypocket.presenter.InitialInterface;

public class InitialActivity extends AppCompatActivity implements InitialInterface {
    EditText name, email, bankName, balance;
    Button btClean, btSubmit;
    InitialActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        name = (EditText) findViewById(R.id.edtName);
        email = (EditText) findViewById(R.id.edtEmail);
        bankName = (EditText) findViewById(R.id.edtBankName);
        balance = (EditText) findViewById(R.id.edtBalance);
        btClean = (Button) findViewById(R.id.btClean);
        btSubmit = (Button) findViewById(R.id.btSubmit);
        mPresenter = new InitialActivityPresenter(this, this.getApplicationContext());

        btClean.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.initialRegistration();
            }
        });
    }

    // Método responsável por limpar os campos de texto
    public void clean() {
        name.setText(null);
        email.setText(null);
        bankName.setText(null);
        balance.setText(null);
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getEmail() {
        return email.getText().toString();
    }

    public String getBankName() {
        return bankName.getText().toString();
    }

    public Double getBalance() {
        return Double.parseDouble(balance.getText().toString());
    }

    public void registrationError() {
        Toast.makeText(InitialActivity.this, "@String/registrationError", Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(InitialActivity.this, "@String/successfullyRegistration", Toast.LENGTH_SHORT).show();
    }

    public void DatabaseInsertError() {
        Toast.makeText(InitialActivity.this, "@String/DatabaseInsertError", Toast.LENGTH_SHORT).show();
    }
}
