package wallyson.com.br.mypocket.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.MainActivityPresenter;
import wallyson.com.br.mypocket.presenter.MainInterface;

public class MainActivity extends AppCompatActivity implements MainInterface {
    ImageButton btnSpending, btnBalance, btnSpendingCategory, btnSpendingMonth,
    btnSpendingYear, btnAccount, btnCard, btnDelete, btnConfiguration;
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            Intent intent = new Intent(MainActivity.this, InitialActivity.class);
            startActivity(intent);

            // mark first time has runned.
            if ( mPresenter.existUser() == true ) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();
            }

        }

        btnSpending = (ImageButton) findViewById(R.id.btnSpending);
        btnBalance = (ImageButton) findViewById(R.id.btnBalance);
        btnSpendingCategory = (ImageButton) findViewById(R.id.btnSpendingCategory);
        btnSpendingMonth = (ImageButton) findViewById(R.id.btnSpendingMonth);
        btnSpendingYear = (ImageButton) findViewById(R.id.btnSpendingYear);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnCard = (ImageButton) findViewById(R.id.btnCard);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnConfiguration = (ImageButton) findViewById(R.id.btnConfiguration);

        mPresenter = new MainActivityPresenter(this, this.getApplicationContext() );

        btnSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, SpendingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, BalanceActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpendingCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, SpendingCategoryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpendingMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, SpendingMonthActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSpendingYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, SpendingYearActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, CardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mPresenter.existAccount() == true && mPresenter.existCard() == true ) {
                    Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.errorEmptyAccountCard), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
