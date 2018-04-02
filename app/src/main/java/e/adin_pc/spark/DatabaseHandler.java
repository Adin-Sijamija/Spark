package e.adin_pc.spark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adin-PC on 3/30/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "Spark.db";
    private static final int VERSION = 1;

    //Tabela1
    private static final String table1_name="Users";
    private static final String Users_id = "Users_id";
    private static final String user_name = "user_name";
    private static final String password = "password";
    private static final String email = "email";
    private static final String birthday = "birthday";
    private static final String is_admin = "is_admin";
    //Table 2
    private static final String table2_name="Flights";
    private static final String Flights_id = "Flights_id";
    private static final String start_destination = "start_destination";
    private static final String end_destination = "end_destination";
    private static final String flight_start_time = "flight_start_time";
    private static final String Flight_end_time = "Flight_end_time";
    private static final String Price = "Price";
    //Table 3
    private static final String table3_name="User_flights";
    private static final String User_flights_id = "User_flights_id";
    private static final String User_fk = "User_fk";
    private static final String Flight_fk = "Flight_fk";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String Tb1Sql="CREATE TABLE "+table1_name+" ("+
                Users_id+" integer primary key , "+
                user_name+" text not null,"+
                password+" text not null,"+
                email+" text not null,"+
                birthday+" text not null,"+
                is_admin+" text not null"+" )";

        String Tb2Sql="CREATE TABLE "+table2_name+" ("+
                Flights_id+" integer primary key , "+
                start_destination+" text not null,"+
                end_destination+" text not null,"+
                flight_start_time+" text not null,"+
                Flight_end_time+" text not null,"+
                Price+" text not null"+" )";

        String Tb3Sql="CREATE TABLE "+table3_name+" ("+
                User_flights_id+" integer primary key , "+
                User_fk+" integer not null, "+
                "FOREIGN KEY ("+User_fk+") REFERENCES "+table1_name+"("+Users_id+"), "+
                Flight_fk+" integer not null, "+
                "FOREIGN KEY ("+Flight_fk+") REFERENCES "+table2_name+"("+Flights_id+")"+
                " )";

        db.setForeignKeyConstraintsEnabled(true); //TODO FIX THIS
       // db.execSQL("PRAGMA foreign_keys");
        db.execSQL(Tb1Sql);
        db.execSQL(Tb2Sql);
        db.execSQL(Tb3Sql);



    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table3_name);
        db.execSQL("DROP TABLE IF EXISTS " + table1_name);
        db.execSQL("DROP TABLE IF EXISTS " + table2_name);
        onCreate(db);
    }

//*---------------------------USER COMMANDS-------------------------------------------------------
 public boolean InsertUser(Users New_User){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Users_id,New_User.getId());
        values.put(user_name,New_User.getUserName());
        values.put(password,New_User.getPassword());
        values.put(email,New_User.getEmail());
        values.put(birthday,New_User.getBirthday());
        values.put(is_admin,New_User.getIs_Admin());

        long result=db.insert(table1_name,null,values);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

public Users GetUser(String UserName, String Password){

     SQLiteDatabase db=this.getReadableDatabase();

     String Querry="SELECT * FROM "+ table1_name+" WHERE "+user_name+" = "+UserName+" AND "+
             password+" = "+Password;



    Cursor c = db.rawQuery(Querry, null);
    if (c != null)
        c.moveToFirst();

    Users user= new Users();
    user.setId(c.getInt(c.getColumnIndex(Users_id)));
    user.setUserName(c.getString(c.getColumnIndex(user_name)));
    user.setPassword(c.getString(c.getColumnIndex(password)));
    user.setEmail(c.getString(c.getColumnIndex(email)));
    user.setBirthday(c.getString(c.getColumnIndex(birthday)));

   String IsAdmin=c.getString(c.getColumnIndex(is_admin));
   String validation;
   if (IsAdmin=="true")
       user.setIs_Admin(true);
   else
       user.setIs_Admin(false);
    db.close();

   return user;




}










//*---------------------------FLIGTS COMMANDS-------------------------------------------------------

    public boolean InsertFlights(Flights New_flight){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(start_destination,New_flight.getStart_destination());
        values.put(end_destination,New_flight.getEnd_destination());
        values.put(flight_start_time,New_flight.getFlight_start_time());
        values.put(Flight_end_time,New_flight.getFlight_end_time());
        values.put(Price,New_flight.getPrice());

        long result=db.insert(table2_name,null,values);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /*
 * getting all flights under single user
 * */
    public List<Flights> getAllUSersFlights(long user_id) {
        List<Flights> flights = new ArrayList<Flights>();

        String selectQuery = "SELECT  * FROM " + table2_name +" INNER JOIN "+ table3_name+" ON "
                + table2_name+"."+Flights_id+" = "+table3_name+"."+Flight_fk+" WHERE "+table3_name+"."+User_fk+"="+user_id;



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Flights td = new Flights();
                td.setId(c.getInt((c.getColumnIndex(Flights_id))));
                td.setStart_destination(c.getString(c.getColumnIndex(start_destination)));
                td.setEnd_destination(c.getString(c.getColumnIndex(end_destination)));
                td.setFlight_start_time(c.getString(c.getColumnIndex(flight_start_time)));
                td.setFlight_end_time(c.getString(c.getColumnIndex(Flight_end_time)));
                td.setPrice(c.getFloat(c.getColumnIndex(Price)));



                flights.add(td);
            } while (c.moveToNext());
        }
        db.close();

        return flights;
    }



    //*---------------------------USerFlights COMMANDS-------------------------------------------------------


    public boolean InsertUSerFlights(long user_id,long flight_id ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(User_fk, user_id);
        values.put(Flight_fk, flight_id);


        long result=db.insert(table3_name,null,values);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }





}
