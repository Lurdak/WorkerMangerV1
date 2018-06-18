package lurdak.try3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import lurdak.try3.DBControler.DBManger;
import lurdak.try3.View.CompanyEditManger;
import lurdak.try3.View.TessTry;
import lurdak.try3.View.WorkersEditManger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;

    private Uri outputFileDir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBManger.getInstance().openDataBase(this);

    }
    public void workerMangerBrn(View view)
    {
        Intent intent = new Intent(MainActivity.this, WorkersEditManger.class);
        startActivity(intent);
    }
    public void comapyMangerBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, CompanyEditManger.class);
        startActivity(intent);
    }
    public void worksOnMangetBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, TessTry.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        DBManger.getInstance().openDataBase(this);
        super.onResume();

    }

    @Override
    protected void onPause() {
        DBManger.getInstance().closeDataBase();
        super.onPause();
    }

}
