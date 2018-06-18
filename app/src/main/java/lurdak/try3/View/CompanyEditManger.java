package lurdak.try3.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import lurdak.try3.DBControler.DBManger;
import lurdak.try3.R;

public class CompanyEditManger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_edit);
        DBManger.getInstance().openDataBase(this);

    }
    public void OpenNewWorker(View view)
    {
        Intent intent = new Intent(CompanyEditManger.this, NewCompanyActivity.class);
        startActivity(intent);
    }
    public void openWorkerList(View view)
    {
        Intent intent = new Intent(CompanyEditManger.this, CompanyListActivity.class);
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
