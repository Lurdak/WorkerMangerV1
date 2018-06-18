package lurdak.try3.DBControler;

/**
 * Created by Lurdak on 6/7/2018.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lurdak.try3.Model.Company;
import lurdak.try3.Model.Worker;

public class DBManger {

    private static DBManger instance = null;
    private Context context = null;
    private DataBaseSQLite db = null;
    private Worker selectedWorker = null;


    public static DBManger getInstance() {
        if (instance == null) {
            instance = new DBManger();
        }
        return instance;
    }

    public static void releaseInstance() {
        if (instance != null) {
            instance.clean();
            instance = null;
        }
    }

    private void clean() {

    }


    public Context getContext() {
        return context;

    }

    public void openDataBase(Context context) {
        this.context = context;
        if (context != null) {
            db = new DataBaseSQLite(context);
            db.open();
        }
    }
    public void closeDataBase() {
        if(db!=null){
            db.close();
        }
    }

    public void createWorker(Worker item) {
        if (db != null) {
            db.createWorker(item);
        }
    }

    public Worker readWorker(String id) {
        Worker result = null;
        if (db != null) {
            result = db.readWorker(id);
        }
        return result;
    }


    public List<Worker> getAllWorkers() {
        List<Worker> result = new ArrayList<Worker>();
        if (db != null) {
            result = db.getAllWorkers();
        }
        return result;
    }

    public void updateWorker(Worker item) {
        if (db != null && item != null) {
            db.updateWorker(item);
        }
    }


    public void deleteWorker(Worker item) {
        if (db != null) {
            db.deleteWorker(item);
        }
    }

    public Worker getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(Worker selectedItem) {
        this.selectedWorker = selectedItem;
    }
//****************************Company**************************************//
public void createCompany(Company item) {
    if (db != null) {
        db.createCompany(item);
    }
}

    public Company readCompany(String id) {
        Company result = null;
        if (db != null) {
            result = db.readComapny(id);
        }
        return result;
    }


    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<Company>();
        if (db != null) {
            result = db.getAllCompanies();
        }
        return result;
    }

//    public void updateCompany(Company item) {
//        if (db != null && item != null) {
//            db.updateCompany(item);
//        }
//    }


//    public void deleteWorker(Worker item) {
//        if (db != null) {
//            db.deleteWorker(item);
//        }
//    }

//    public Company getSelectedCompany() {
//        return selectedItem;
//    }
//
//    public void setSelectedWorker(Worker selectedItem) {
//        this.selectedItem = selectedItem;
//    }

}
