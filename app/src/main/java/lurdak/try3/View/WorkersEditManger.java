package lurdak.try3.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lurdak.try3.DBControler.DBManger;
import lurdak.try3.R;

public class WorkersEditManger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_edit_manger);
        DBManger.getInstance().openDataBase(this);

    }
    public void OpenNewWorker(View view)
    {
        Intent intent = new Intent(WorkersEditManger.this, NewWorkerLayoutActivity.class);
        startActivity(intent);
    }
    public void openWorkerList(View view)
    {
        Intent intent = new Intent(WorkersEditManger.this, WorkerListActivity.class);
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
