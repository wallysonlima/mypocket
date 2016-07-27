package wallyson.com.br.mypocket.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.User;
import wallyson.com.br.mypocket.presenter.SpendingYearActivityPresenter;
import wallyson.com.br.mypocket.presenter.SpendingYearInterface;

public class SpendingYearActivity extends AppCompatActivity implements SpendingYearInterface {
    private BarChart barChart;
    private Button btnSendEmail;
    private Date date;
    private GregorianCalendar dateCal;
    private String month, year;
    private SpendingYearActivityPresenter mPresenter;
    private ArrayList<String> monthYear = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_year);

        barChart = (BarChart) findViewById(R.id.chart);
        monthYear.add(getResources().getString(R.string.january));
        monthYear.add(getResources().getString(R.string.february));
        monthYear.add(getResources().getString(R.string.march));
        monthYear.add(getResources().getString(R.string.april));
        monthYear.add(getResources().getString(R.string.may));
        monthYear.add(getResources().getString(R.string.june));
        monthYear.add(getResources().getString(R.string.july));
        monthYear.add(getResources().getString(R.string.august));
        monthYear.add(getResources().getString(R.string.september));
        monthYear.add(getResources().getString(R.string.october));
        monthYear.add(getResources().getString(R.string.november));
        monthYear.add(getResources().getString(R.string.december));

        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        mPresenter = new SpendingYearActivityPresenter(this, this.getApplicationContext());
        date = new Date(System.currentTimeMillis());
        dateCal = new GregorianCalendar();
        dateCal.setTime(date);
        month = String.valueOf( dateCal.get(Calendar.MONTH) );
        year = String.valueOf( dateCal.get(Calendar.YEAR) );

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendByEmail();
            }
        });

        createMonthBarChart();
    }

    public void createMonthBarChart() {
        Float[] spendingMonthYear = mPresenter.AllSpendingForMonth();
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet dataSet;

        for ( int i = 0; i < spendingMonthYear.length; i++ ) {
            entries.add( new BarEntry( spendingMonthYear[i], i) );
        }

        dataSet = new BarDataSet(entries, getResources().getString(R.string.month));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(monthYear, dataSet);
        barChart.setDescription(getResources().getString(R.string.spending_by_month));
        barChart.setData(data);
    }

    // Send image from PieChart for Email
    public void sendByEmail() {
        UserDao userDao = new UserDao(this);
        User user = userDao.selectUser();
        String nameImage = user.getName() + "_Spending " + year +".jpg";
        barChart.saveToGallery(nameImage, 100);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "myPocket " + user.getName() +
                "Spending for Category on Month and Year: " + month );
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "myPocket");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/" + nameImage));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public ArrayList<String> getMonthYear() {
        return this.monthYear;
    }
}
