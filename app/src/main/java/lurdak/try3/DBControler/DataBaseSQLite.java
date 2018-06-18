package lurdak.try3.DBControler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lurdak.try3.Model.Company;
import lurdak.try3.Model.WorkOn;
import lurdak.try3.Model.Worker;

public class DataBaseSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WorkerM3";


    // Worker table
    private static final String Table_Worker_Name = "Workers";
    private static final String Worker_Column_Id = "id";
    private static final String Worker_Column_Name = "name";
    private static final String Worker_Column_Family = "family";
    private static final String Worker_Column_Birthday = "birthday";
    private static final String Worker_Column_Phone = "phone";
    private static final String Worker_Column_Img="image";
    private static final String Worker_Column_Gendrr="gender";
    private static final String Worker_Column_101="onehundredandone";
    private static final String Worker_Column_Id_Img="id_img";
    private static final String Worker_Column_STARTDATE="start_date";
    private static final String Worker_Column_Email="email";
    private static final String Worker_Column_WAGE="wage";
    private static final String Worker_Column_TRANSPORT="transport";
    private static final String[] TABLE_WORKER_COLUMNS = {Worker_Column_Id, Worker_Column_Name,Worker_Column_Family,Worker_Column_Gendrr,Worker_Column_Birthday,Worker_Column_Phone,
            Worker_Column_STARTDATE,Worker_Column_Email,Worker_Column_Id_Img,Worker_Column_101,Worker_Column_Img,Worker_Column_WAGE,Worker_Column_TRANSPORT};
    //    //unactiveWorker
//    private static final String Table_unactiveWorkers_Name = "unactiveWorkers";
//    private static final String[] TABLE_UnnactiveWORKER_COLUMNS = {Worker_Column_Id, Worker_Column_Name,Worker_Column_Family,Worker_Column_Gendrr,Worker_Column_Birthday,Worker_Column_Phone,
//            Worker_Column_STARTDATE,Worker_Column_Email,Worker_Column_Id_Img,Worker_Column_101,Worker_Column_Img};
    //company table
    private static final String Table_Company_Name = "Company";
    private static final String Company_Column_Id = "id";
    private static final String Company_Column_Name = "name";
    private static final String Company_Column_Phone = "phone";

    private static final String[] TABLE_Company_COLUMNS = {Company_Column_Id, Company_Column_Name,Company_Column_Phone};
    //workOn table
    private static final String Table_workOn_Name = "workOn";
    private static final String workOn_Column_Id = "id";
    private static final String workOn_Column_WorkerID = "worker_id";
    private static final String workOn_Column_companyID = "company_id";
    private static final String workOn_Column_start_date= "start_date";
    private static final String workOn_Column_end_date = "end_date";

    private static final String[] TABLE_workOn_COLUMNS = {workOn_Column_Id, workOn_Column_WorkerID,workOn_Column_companyID,workOn_Column_start_date,workOn_Column_end_date};

    //wage table
//    private static final String Table_WAGE_Name = "wage";
//    private static final String workOn_Column_Id = "id";
//    private static final String workOn_Column_WorkerID = "worker_id";
//    private static final String workOn_Column_companyID = "company_id";
//    private static final String workOn_Column_start_date= "start_date";
//    private static final String workOn_Column_end_date = "end_date";
//
//    private static final String[] TABLE_workOn_COLUMNS = {workOn_Column_Id, workOn_Column_WorkerID,workOn_Column_companyID,workOn_Column_start_date,workOn_Column_end_date};



    private SQLiteDatabase db = null;

    public DataBaseSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //init database
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // SQL statement to create item table
            String CREATE_ITEM_TABLE = "create table if not exists " + Table_Worker_Name +" ( "
                    + Worker_Column_Id +" TEXT PRIMARY KEY , "
                    + Worker_Column_Name +" TEXT, "
                    + Worker_Column_Family + " TEXT, "
                    + Worker_Column_Gendrr + " TEXT, "
                    + Worker_Column_Birthday +" TEXT, "
                    + Worker_Column_Phone + " TEXT, "
                    + Worker_Column_STARTDATE + " TEXT, "
                    + Worker_Column_Email + " TEXT, "
                    + Worker_Column_Id_Img+ " TEXT, "
                    + Worker_Column_101 + " TEXT, "
                    + Worker_Column_Img + " BLOB,"
                    + Worker_Column_WAGE+" REAL ,"
                    +Worker_Column_TRANSPORT+" REAL )";



            db.execSQL(CREATE_ITEM_TABLE);

            String CREATE_Company_TABLE = "create table if not exists " + Table_Company_Name +" ( "
                    + Company_Column_Id +" TEXT PRIMARY KEY , "
                    + Company_Column_Name +" TEXT , "
                    + Company_Column_Phone +" TEXT ) ";
            db.execSQL(CREATE_Company_TABLE);
            String CREATE_WorkOn_TABLE = "create table if not exists " + Table_workOn_Name +" ( "
                    + workOn_Column_Id +" int PRIMARY KEY AUTOINCREMENT , "
                    + workOn_Column_WorkerID +" TEXT NOT NULL, "
                    + workOn_Column_companyID + " TEXT NOT NULL, "
                    + workOn_Column_start_date + " TEXT, "
                    + workOn_Column_end_date +" TEXT ," +
                    "FOREIGN KEY("+workOn_Column_WorkerID+") REFERENCES "+Table_Worker_Name+"("+Worker_Column_Id+") ON UPDATE CASCADE ," +
                    "FOREIGN KEY("+workOn_Column_companyID+") REFERENCES "+Table_Company_Name+"("+Company_Column_Id+") ON UPDATE CASCADE )";



            db.execSQL(CREATE_WorkOn_TABLE);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            // drop item table if already exists
            //db.execSQL("DROP TABLE IF EXISTS items");
            //db.execSQL("DROP TABLE IF EXISTS folders");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        //onCreate(db);
    }
    //addnewworker
    public void createWorker(Worker worker ) {

        try {
            // make values to be inserted
            ContentValues values = new ContentValues();
            //id
            values.put(Worker_Column_Id, worker.getId());
            //name
            if (worker.getName() != null) {
                values.put(Worker_Column_Name, worker.getName());
            }
            else{
                values.putNull(Worker_Column_Name);
            }
            //fam
            if (worker.getFamily() != null) {
                values.put(Worker_Column_Family,  worker.getFamily());
            }
            else{
                values.putNull(Worker_Column_Family);
            }
            //birthday
            if (worker.getBirthday() != null) {
                values.put(Worker_Column_Birthday,  worker.getBirthday().toString());
            }
            else{
                values.putNull(Worker_Column_Birthday);
            }
            //gender
            if (worker.getGender() != null) {
                values.put(Worker_Column_Gendrr,  worker.getGender());
            }
            else{
                values.putNull(Worker_Column_Gendrr);
            }
            //startDate
            if (worker.getStartDAte() != null) {
                values.put(Worker_Column_STARTDATE,  worker.getStartDAte().toString());
            }
            else{
                values.putNull(Worker_Column_STARTDATE);
            }
            //email
            if (worker.getEmail() != null) {
                values.put(Worker_Column_Email,  worker.getEmail());
            }
            else{
                values.putNull(Worker_Column_Email);
            }
            //phone
            if (worker.getPhoneNumber() != null) {
                values.put(Worker_Column_Phone,  worker.getPhoneNumber());
            }
            else{
                values.putNull(Worker_Column_Phone);
            }
            //
            // 101

            if (worker.getP101Url() != null) {
                values.put(Worker_Column_101,  worker.getP101Url());
            }
            else{
                values.putNull(Worker_Column_101);
            }
            //img id

            if (worker.getIdImgUrl() != null) {
                values.put(Worker_Column_Id_Img,  worker.getIdImgUrl());
            }
            else{
                values.putNull(Worker_Column_Id_Img);
            }
            //images
            Bitmap image1 = worker.getFaceImg();
            if (image1 != null) {
                byte[] data = getBitmapAsByteArray(image1);
                if (data != null && data.length > 0) {
                    values.put(Worker_Column_Img, data);
                }
            }
            else{
                values.putNull(Worker_Column_Img);
            }
            if (worker.getWage() >0) {
                values.put(Worker_Column_WAGE,  worker.getWage());
            }
            else{
                values.put(Worker_Column_WAGE,0);
            }
            if (worker.getTransport() >0) {
                values.put(Worker_Column_TRANSPORT,  worker.getTransport());
            }
            else{
                values.put(Worker_Column_TRANSPORT,0);
            }

            // insert item
            db.insert(Table_Worker_Name, null, values);


        } catch (Throwable t) {
            t.printStackTrace();
        }


    }
    // /------------------------------------------------------------------------------
//add new company
    public void createCompany(Company company ) {

        try {
            // make values to be inserted
            ContentValues values = new ContentValues();
            //id
            values.put(Company_Column_Id, company.getId());
            //name
            if (company.getName() != null) {
                values.put(Company_Column_Name, company.getName());
            }
            else{
                values.putNull(Company_Column_Name);
            }

            if (company.getPhoneNumber() != null) {
                values.put(Company_Column_Phone, company.getPhoneNumber());
            }
            else{
                values.putNull(Company_Column_Phone);
            }


            // insert item
            db.insert(Table_Company_Name, null, values);


        } catch (Throwable t) {
            t.printStackTrace();
        }


    }
    /*********************************************************************************************/
    //add works on
    public void createWorksOn(WorkOn workon ) {

        try {
            // make values to be inserted
            ContentValues values = new ContentValues();
            //id
            values.put(workOn_Column_WorkerID, workon.getWorkerId());
            values.put(workOn_Column_companyID, workon.getCompanyId());


            //startDate
            if (workon.getStartDate() != null) {
                values.put(workOn_Column_start_date, workon.getStartDate().toString());
            }
            else{
                values.putNull(workOn_Column_start_date);
            }
            if (workon.getStartDate() != null) {
                values.put(workOn_Column_end_date, workon.getEndDate().toString());
            }
            else{
                values.putNull(workOn_Column_end_date);
            }


            // insert item
            db.insert(Table_workOn_Name, null, values);


        } catch (Throwable t) {
            t.printStackTrace();
        }


    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

//    public void createFolder(InfoFolder folder) {
//
//
//        try {
//            // make values to be inserted
//            ContentValues values = new ContentValues();
//            values.put(FOLDER_COLUMN_TITLE, folder.getTitle());
//            // insert folder
//            db.insert(TABLE_FOLDER_NAME, null, values);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//    }

    public Worker readWorker(String id) {
        Worker worker = null;
        Cursor cursor = null;
        try {
            /// get reference of the itemDB database

            // get  query
            cursor = db
                    .query(Table_Worker_Name,
                            TABLE_WORKER_COLUMNS, Worker_Column_Id+ " = ?",
                            new String[] { String.valueOf(id) }, null, null,
                            null, null);



            // if results !=null, parse the first one
            if(cursor!=null && cursor.getCount()>0){

                cursor.moveToFirst();

                worker = new Worker(id);
                worker.setName(cursor.getString(1));
                worker.setFamily(cursor.getString(2));
                worker.setGender(cursor.getString(3));
                SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                Date newDate=formatter6.parse(cursor.getString(4));
                worker.setBirthday(newDate);
                worker.setPhoneNumber(cursor.getString(5));
                newDate= formatter6.parse(cursor.getString(6));
                worker.setEmail(cursor.getString(7));

                worker.setStartDAte(newDate);
                worker.setIdImgUrl(cursor.getString(8));
                worker.setP101Url(cursor.getString(9));



                //images
                byte[] img1Byte = cursor.getBlob(10);
                if (img1Byte != null && img1Byte.length > 0) {
                    Bitmap image1 = BitmapFactory.decodeByteArray(img1Byte, 0, img1Byte.length);
                    if (image1 != null) {
                        worker.setFaceImg(image1);
                    }
                }

            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return worker;
    }
    /********************************************/
//read company

    public Company readComapny(String id) {
        Company company = null;
        Cursor cursor = null;
        try {
            /// get reference of the itemDB database

            // get  query
            cursor = db
                    .query(Table_Company_Name,
                            TABLE_Company_COLUMNS, Company_Column_Id+ " = ?",
                            new String[] { String.valueOf(id) }, null, null,
                            null, null);



            // if results !=null, parse the first one
            if(cursor!=null && cursor.getCount()>0){

                cursor.moveToFirst();

                company = new Company(id);
                company.setName(cursor.getString(1));
                company.setPhoneNumber(cursor.getString(2));



            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return company;
    }
    /****************************************************************/
//readWorksOn
    public WorkOn readWorkOn(int id) {
        WorkOn worker = null;
        Cursor cursor = null;
        try {
            /// get reference of the itemDB database

            // get  query
            cursor = db
                    .query(Table_workOn_Name,
                            TABLE_workOn_COLUMNS, workOn_Column_Id+ " = ?",
                            new String[] { String.valueOf(id) }, null, null,
                            null, null);



            // if results !=null, parse the first one
            if(cursor!=null && cursor.getCount()>0){

                cursor.moveToFirst();

                worker = new WorkOn(id);
                worker.setWorkerId(cursor.getString(1));
                worker.setCompanyId(cursor.getString(2));
                SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                Date newDate=formatter6.parse(cursor.getString(3));
                worker.setStartDate(newDate);
                newDate= formatter6.parse(cursor.getString(4));
                worker.setEndDate(newDate);






            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return worker;
    }

//    public InfoFolder readFolder(int id) {
//        InfoFolder folder = null;
//        Cursor cursor = null;
//        try {
//            // get reference of the folderDB database
//
//            // get  query
//            cursor = db
//                    .query(TABLE_FOLDER_NAME, // a. table
//                            TABLE_FOLDER_COLUMNS, FOLDER_COLUMN_ID + " = ?",
//                            new String[] { String.valueOf(id) }, null, null,
//                            null, null);
//
//            // if results !=null, parse the first one
//            if (cursor != null)
//                cursor.moveToFirst();
//
//            folder = new InfoFolder();
//            folder.setId(Integer.parseInt(cursor.getString(0)));
//            folder.setTitle(cursor.getString(1));
//
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        finally {
//            if(cursor!=null){
//                cursor.close();
//            }
//        }
//        return folder;
//    }

    public List<Worker> getAllWorkers() {
        List<Worker> result = new ArrayList<Worker>();
        Cursor cursor = null;
        try {
            cursor = db.query(Table_Worker_Name,TABLE_WORKER_COLUMNS, null, null,
                    null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Worker worker = cursorToWorker(cursor);
                result.add(worker);
                cursor.moveToNext();
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            // make sure to close the cursor
            if(cursor!=null){
                cursor.close();
            }
        }

        return result;
    }

    private Worker cursorToWorker(Cursor cursor) {
        Worker worker = new Worker();
        try {

            worker = new Worker(cursor.getString(0));
            worker.setName(cursor.getString(1));
            worker.setFamily(cursor.getString(2));
            worker.setGender(cursor.getString(3));
            SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date newDate=formatter6.parse(cursor.getString(4));
            worker.setBirthday(newDate);
            worker.setPhoneNumber(cursor.getString(5));
            newDate= formatter6.parse(cursor.getString(6));
            worker.setEmail(cursor.getString(7));

            worker.setStartDAte(newDate);
            worker.setIdImgUrl(cursor.getString(8));
            worker.setP101Url(cursor.getString(9));
            worker.setWage(Double.parseDouble(cursor.getString(11)));
            worker.setTransport(Double.parseDouble(cursor.getString(12)));


            //images
            byte[] img1Byte = cursor.getBlob(10);
            if (img1Byte != null && img1Byte.length > 0) {
                Bitmap image1 = BitmapFactory.decodeByteArray(img1Byte, 0, img1Byte.length);
                if (image1 != null) {
                    worker.setFaceImg(image1);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return worker;
    }
    /*******************************************************************/
//get all companies
    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<Company>();
        Cursor cursor = null;
        try {
            cursor = db.query(Table_Company_Name,TABLE_Company_COLUMNS, null, null,
                    null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Company worker = cursorToCompany(cursor);
                result.add(worker);
                cursor.moveToNext();
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            // make sure to close the cursor
            if(cursor!=null){
                cursor.close();
            }
        }

        return result;
    }

    private Company cursorToCompany(Cursor cursor) {
        Company worker = new Company();
        try {

            worker = new Company(cursor.getString(0));
            worker.setName(cursor.getString(1));
            worker.setPhoneNumber(cursor.getString(2));





        } catch (Throwable t) {
            t.printStackTrace();
        }
        return worker;
    }
    /************************************************/
    //get all works on
    public List<WorkOn> getAllWorkOn() {
        List<WorkOn> result = new ArrayList<WorkOn>();
        Cursor cursor = null;
        try {
            cursor = db.query(Table_workOn_Name,TABLE_workOn_COLUMNS, null, null,
                    null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                WorkOn worker = cursorToWorkOn(cursor);
                result.add(worker);
                cursor.moveToNext();
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            // make sure to close the cursor
            if(cursor!=null){
                cursor.close();
            }
        }

        return result;
    }

    private WorkOn cursorToWorkOn(Cursor cursor) {
        WorkOn worker = new WorkOn();
        try {

            worker = new WorkOn(Integer.parseInt(cursor.getString(0)));
            worker.setWorkerId(cursor.getString(1));
            worker.setCompanyId(cursor.getString(2));

            SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date newDate=formatter6.parse(cursor.getString(3));
            worker.setStartDate(newDate);

            newDate= formatter6.parse(cursor.getString(4));


            worker.setEndDate(newDate);




        } catch (Throwable t) {
            t.printStackTrace();
        }
        return worker;
    }
//    public List<InfoFolder> getAllFolders() {
//        List<InfoFolder> result = new ArrayList<InfoFolder>();
//        Cursor cursor = null;
//        try {
//            cursor = db.query(TABLE_FOLDER_NAME, TABLE_FOLDER_COLUMNS, null, null,
//                    null, null, null);
//
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                InfoFolder folder = cursorToFolder(cursor);
//                result.add(folder);
//                cursor.moveToNext();
//            }
//
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        finally {
//            // make sure to close the cursor
//            if(cursor!=null){
//                cursor.close();
//            }
//        }
//        return result;
//    }
//
//    private InfoFolder cursorToFolder(Cursor cursor) {
//        InfoFolder result = new InfoFolder();
//        try {
//            result.setId(Integer.parseInt(cursor.getString(0)));
//            result.setTitle(cursor.getString(1));
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        return result;
//    }

    public int updateWorker(Worker worker) {
        int cnt = 0;
        try {

            // make values to be inserted
            ContentValues values = new ContentValues();
            values.put(Worker_Column_Id, worker.getId());
            values.put(Worker_Column_Name, worker.getName());
            values.put(Worker_Column_Family, worker.getFamily());
            values.put(Worker_Column_Birthday, worker.getBirthday().toString());
            values.put(Worker_Column_Phone, worker.getPhoneNumber());
            values.put(Worker_Column_Gendrr, worker.getGender());
            values.put(Worker_Column_101, worker.getP101Url());
            values.put(Worker_Column_Id_Img, worker.getIdImgUrl());
            values.put(Worker_Column_STARTDATE, worker.getStartDAte().toString());

            //images
            Bitmap image1 = worker.getFaceImg();
            if (image1 != null) {
                byte[] data = getBitmapAsByteArray(image1);
                if (data != null && data.length > 0) {
                    values.put(Worker_Column_Img, data);
                }
            }
            else{
                values.putNull(Worker_Column_Img);
            }            // update
            cnt = db.update(Table_Worker_Name, values, Worker_Column_Id + " = ?",
                    new String[] { String.valueOf(worker.getId()) });
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return cnt;
    }
//
//    public int updateFolder(InfoFolder folder) {
//        int i = 0;
//        try {
//
//
//            // make values to be inserted
//            ContentValues values = new ContentValues();
//            values.put(FOLDER_COLUMN_TITLE, folder.getTitle());
//
//            // update
//            i = db.update(TABLE_FOLDER_NAME, values, FOLDER_COLUMN_ID + " = ?",
//                    new String[] { String.valueOf(folder.getId()) });
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//        return i;
//    }

    public void deleteWorker(Worker worker) {

        try {

            // delete item
            db.delete(Table_Worker_Name, Worker_Column_Id + " = ?",
                    new String[] { String.valueOf(worker.getId()) });
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
//
//    public void deleteFolder(InfoFolder folder) {
//        boolean succeded = false;
//        try {
//
//            // delete folder
//            int rowAffected = db.delete(TABLE_FOLDER_NAME, FOLDER_COLUMN_ID + " = ?",
//                    new String[] { String.valueOf(folder.getId()) });
//            if(rowAffected>0) {
//                succeded = true;
//            }
//
//        } catch (Throwable t) {
//            succeded = false;
//            t.printStackTrace();
//        } finally {
//            if(succeded){
//                deleteFolderItems(folder);
//            }
//        }
//
//    }
//
//    private void deleteFolderItems(InfoFolder folder) {
//
//        try {
//
//            // delete items
//            db.delete(TABLE_ITEMS_NAME, ITEM_COLUMN_FOLDERID + " = ?",
//                    new String[] { String.valueOf(folder.getId()) });
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//    }

    private boolean isTableExist(String name, SQLiteDatabase db) {

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+ name + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


//    public List<InfoItem> getAllItemsOfFolder(InfoFolder folder) {
//        List<InfoItem> result = new ArrayList<InfoItem>();
//        Cursor cursor = null;
//        try {
//            int floderId = folder.getId();
//            cursor = db.query(TABLE_ITEMS_NAME, TABLE_ITEM_COLUMNS, ITEM_COLUMN_FOLDERID +" = ?",
//                    new String[] { String.valueOf(floderId) }, null, null,
//                    null, null);
//
//            if(cursor!=null && cursor.getCount()>0){
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    InfoItem item = cursorToItem(cursor);
//                    result.add(item);
//                    cursor.moveToNext();
//                }
//            }
//
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//        finally {
//            if(cursor!=null) {
//                // make sure to close the cursor
//                cursor.close();
//            }
//        }
//        return result;
//    }

    public void open() {
        try {
            db = getWritableDatabase();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void close() {
        try {
            db.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
