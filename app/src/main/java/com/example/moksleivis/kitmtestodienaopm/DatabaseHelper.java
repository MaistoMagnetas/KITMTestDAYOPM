package com.example.moksleivis.kitmtestodienaopm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper implements UserDao{
    //DATABASE VARIABLE
    public static final String DATABASE_NAME = "user.db";
    //USER TABLE FOR DATABASE
    public static final String TABLE_USER_NAME = "user_table";
    private static final String COL_1_USER = "ID";
    private static final String COL_2_USER = "VARDAS_PAVARDE";
    private static final String COL_3_USER = "METAI";
    private static final String COL_4_USER = "TELNR";
    private static final String COL_5_USER = "KLIENTO_TIPAS";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATEs USER TABLE
        String sql = "CREATE TABLE "+TABLE_USER_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, VARDAS_PAVARDE TEXT," +
                " METAI INTEGER, TELNR INT, KLIENTO_TIPAS TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_NAME);
        onCreate(db);
    }


    //Method for user registration
    @Override
    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_USER,user.getVardasPavarde());
        contentValues.put(COL_3_USER,user.getGimimoData());
        contentValues.put(COL_4_USER,user.getTelNr());
        contentValues.put(COL_5_USER,user.getKlientoTipas());
        long result = db.insert(TABLE_USER_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //Method to get all registered users
    @Override
    public Cursor getUserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABLE_USER_NAME;
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

    @Override
    public void deleteUserEntry(String vardasPavarde, int telNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM "+TABLE_USER_NAME+" WHERE VARDAS_PAVARDE = '" +vardasPavarde+"' AND TELNR = '"+telNr+"'";
        db.execSQL(sql);
    }


    //Method to update JournalEntry
    @Override
    public boolean updateUserEntry(String vardasPavarde, int gimimoMetai,int telNr, String klientoTipas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_USER,vardasPavarde);
        contentValues.put(COL_3_USER,gimimoMetai);
        contentValues.put(COL_4_USER,telNr);
        contentValues.put(COL_5_USER,klientoTipas);
        db.update(TABLE_USER_NAME,contentValues, "VARDAS_PAVARDE = ?",new String[] {vardasPavarde});
        return true;
    }

}