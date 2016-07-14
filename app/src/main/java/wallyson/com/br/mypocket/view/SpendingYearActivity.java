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
import com.github.mikephil.charting.utils.ColorTemplate;

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
    BarChart barChart = (BarChart) findViewById(R.id.chart);
    Button btnSendEmail;
    Date date;
    GregorianCalendar dateCal;
    String month, year;
    SpendingYearActivityPresenter mPresenter;

    private String[] monthYear = {
            getResources().getString(R.string.january),
            getResources().getString(R.string.february),
            getResources().getString(R.string.march),
            getResources().getString(R.string.april),
            getResources().getString(R.string.may),
            getResources().getString(R.string.june),
            getResources().getString(R.string.july),
            getResources().getString(R.string.august),
            getResources().getString(R.string.september),
            getResources().getString(R.string.october),
            getResources().getString(R.string.november),
            getResources().getString(R.string.december)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_spending);

        mPresenter = new SpendingYearActivityPresenter(this, this.getApplicationContext());
        date = new Date(System.currentTimeMillis());
        dateCal = new GregorianCalendar();
        dateCal.setTime(date);
        month = String.valueOf( dateCal.get(Calendar.MONTH) );
        year = String.valueOf( dateCal.get(Calendar.MONTH) );

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendByEmail();
            }
        });

        createMonthBarChart();
    }

    public void createMonthBarChart() {
        ArrayList<Float> spendingForCategory = mPresenter.AllSpendingForMonth();
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet dataSet;
        int i = 0;

        for ( Float sp: spendingForCategory ) {
            entries.add( new BarEntry(sp, i) );
            i++;
        }

        dataSet = new BarDataSet(entries, getResources().getString(R.string.month));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(monthYear, dataSet);
        barChart.setData(data);
        barChart.setDescription(getResources().getString(R.string.spendingByMonth));
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
                "Spending for Category on Month: " + month );
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "myPocket");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/" + nameImage));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public String[] getMonthYear() {
        return this.monthYear;
    }
}
