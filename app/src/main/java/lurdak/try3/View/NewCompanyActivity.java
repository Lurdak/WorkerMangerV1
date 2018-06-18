package lurdak.try3.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import lurdak.try3.DBControler.DBManger;
import lurdak.try3.Model.Company;
import lurdak.try3.R;

public class NewCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_company);
        DBManger.getInstance().openDataBase(this);
    }
    public void SaveNewWorker(View view)
    {
        Company company=new Company();
        //id
        EditText editText = (EditText)findViewById(R.id.idTxt);
        company.setId(editText.getText().toString());
        //name
        editText = (EditText)findViewById(R.id.nametxt);
        company.setName(editText.getText().toString());
        editText = (EditText)findViewById(R.id.phone_text);
        company.setPhoneNumber(editText.getText().toString());


        DBManger.getInstance().createCompany(company);

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
