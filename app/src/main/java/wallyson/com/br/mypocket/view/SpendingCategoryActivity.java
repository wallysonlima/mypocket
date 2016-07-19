package wallyson.com.br.mypocket.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.dao.UserDao;
import wallyson.com.br.mypocket.model.User;
import wallyson.com.br.mypocket.presenter.CategoryActivityPresenter;
import wallyson.com.br.mypocket.presenter.CategoryInterface;

public class SpendingCategoryActivity extends AppCompatActivity implements CategoryInterface {
    PieChart pieChart = (PieChart) findViewById(R.id.chart);
    Button btnSendEmail;
    Date date;
    GregorianCalendar dateCal;
    String monthYear;

    private final String[] arrayCategory = {
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
    CategoryActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_category);

        mPresenter = new CategoryActivityPresenter(this, this.getApplicationContext() );
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendByEmail();
            }
        });
        date = new Date(System.currentTimeMillis());
        dateCal = new GregorianCalendar();
        dateCal.setTime(date);
        monthYear = String.valueOf( dateCal.get(Calendar.MONTH) ) + "/" + String.valueOf( dateCal.get(Calendar.YEAR) );

        createCategoryPieChart();
    }

    // Create PieChart and put all Spending by Category
    public void createCategoryPieChart() {
        ArrayList<Float> spendingForCategory = mPresenter.spendingForCategory(monthYear);
        ArrayList<Entry> entries = new ArrayList<>();
        PieDataSet dataSet;
        int i = 0;

        for ( Float sp: spendingForCategory ) {
            entries.add( new Entry(sp, i) );
            i++;
        }

        dataSet = new PieDataSet(entries, getResources().getString(R.string.category));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(arrayCategory, dataSet);
        pieChart.setData(data);
        pieChart.setDescription(getResources().getString(R.string.spendingByCategory));
    }

    // Send image from PieChart for Email
    public void sendByEmail() {
        UserDao userDao = new UserDao(this);
        User user = userDao.selectUser();
        String nameImage = "Spendings" + monthYear + ".jpg";
        pieChart.saveToGallery(nameImage, 100);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "myPocket " + user.getName() +
                "Spending for Category on Month: " + monthYear);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "myPocket");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/" + nameImage));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public String[] getCategory() {
        return arrayCategory;
    }
}
