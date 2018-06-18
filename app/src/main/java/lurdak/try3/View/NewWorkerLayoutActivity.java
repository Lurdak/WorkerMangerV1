package lurdak.try3.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lurdak.try3.DBControler.DBManger;
import lurdak.try3.Model.Worker;
import lurdak.try3.R;

public class NewWorkerLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_worker_layput);
        DBManger.getInstance().openDataBase(this);
    }
    public void SaveNewWorker(View view)
    {
        Worker worker=new Worker();
        //id
        EditText editText = (EditText)findViewById(R.id.idTxt);
        worker.setId(editText.getText().toString());
        //name
        editText = (EditText)findViewById(R.id.nametxt);
        worker.setName(editText.getText().toString());
        //family
        editText = (EditText)findViewById(R.id.familyTxt);
        worker.setFamily(editText.getText().toString());
        //phone
        editText = (EditText)findViewById(R.id.phone_text);
        worker.setPhoneNumber(editText.getText().toString());
        //startdate
        editText = (EditText)findViewById(R.id.start_date_text);
        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date newDate=new Date();
        try {
             newDate=formatter6.parse(editText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        worker.setStartDAte(newDate);
        //birthday
        editText = (EditText)findViewById(R.id.birthday);
         newDate=new Date();
        try {
            newDate=formatter6.parse(editText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        worker.setStartDAte(newDate);
        //phone
        editText = (EditText)findViewById(R.id.gender_txt);
        worker.setGender(editText.getText().toString());

        DBManger.getInstance().createWorker(worker);

        finish();
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
