package com.example.jeonjin_il.mysecondapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 12. 27..
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE WATERLIST ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT ,capacity INTEGER, percentage INTEGER );");
        db.execSQL("CREATE TABLE DAY_HISTORY ( id INTEGER PRIMARY KEY AUTOINCREMENT , water_id INTEGER , day TEXT , time TEXT);");
        db.execSQL("CREATE TABLE TOTAL_HISTORY ( id INTEGER PRIMARY KEY AUTOINCREMENT , day TEXT , now_water INTEGER  , total_water INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void init(){
        Log.d("TAG","HELLO");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WATERLIST ", null);
        if( !cursor.moveToNext()){
            db.execSQL("INSERT INTO WATERLIST VALUES(null,'나의물잔' ,200 , 100);");
            db.execSQL("INSERT INTO WATERLIST VALUES(null,'생수한통' ,500 , 100);");
            db.execSQL("INSERT INTO WATERLIST VALUES(null,'커피' ,500 , -50);");
            db.execSQL("INSERT INTO WATERLIST VALUES(null,'주스' ,300 , 80);");
            db.execSQL("INSERT INTO WATERLIST VALUES(null,'우유' ,200 , 80);");

            db.close();
        }
    }

    public void insert_history(int water_id,String day,String time){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO DAY_HISTORY VALUES(null,"+water_id+" ,'" + day + "' , '"+time+"');");

        int temp1 =0 , temp2=0;
        Cursor cursor1 = db.rawQuery("SELECT * FROM WATERLIST WHERE id ='"+water_id+"' ", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM TOTAL_HISTORY WHERE day ='"+day+"' ", null);

        if(cursor1.getCount() != 0) {
            cursor1.moveToNext();
            temp1 = (int)(cursor1.getInt(2) * (float)(cursor1.getInt(3)/100));
        }

        if(cursor2.getCount() == 0) {
            db.execSQL("INSERT INTO TOTAL_HISTORY VALUES(null, '"+day+"','"+temp1+"' , 2000 );");
        }else{
            cursor2.moveToNext();
            temp2 = cursor2.getInt(2);
            db.execSQL("UPDATE TOTAL_HISTORY  SET now_water = '"+ (temp1 + temp2) +" '");
        }


        db.close();
    }
    public void delete_history(int id,int water_id,String day){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM DAY_HISTORY WHERE id='"+id+"';");

        int temp1 =0 , temp2=0;
        Cursor cursor1 = db.rawQuery("SELECT * FROM WATERLIST WHERE id ='"+water_id+"' ", null);
        Cursor cursor2 = db.rawQuery("SELECT * FROM TOTAL_HISTORY WHERE day ='"+day+"' ", null);

        if(cursor1.getCount() != 0) {
            cursor1.moveToNext();
            temp1 = (int)(cursor1.getInt(2) * (float)(cursor1.getInt(3)/100));
        }
        if(cursor2.getCount() != 0) {
            cursor2.moveToNext();
            temp2 = cursor2.getInt(2);
            db.execSQL("UPDATE TOTAL_HISTORY  SET now_water = '"+ (temp2 - temp1) +" '");
        }

        db.close();
    }

    public ArrayList<Integer> getTotal_History(String day){
        ArrayList<Integer> ret  = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Log.d("TAG", String.valueOf(1));
        Cursor cursor = db.rawQuery("SELECT * FROM TOTAL_HISTORY WHERE day ='"+day+"' ", null);

        if(cursor.getCount() == 0){
            ret.add(0);
            ret.add(100);
        }
        else {
            cursor.moveToNext();
            ret.add(cursor.getInt(2));
            ret.add(cursor.getInt(3));
        }
        return ret;
    }

    public void changeWater(int id , String name,int num){
        //id 는 1번부터 시작임
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE WATERLIST  SET name = '"+name+"', capacity = '"+num+"' WHERE id='"+id+"'");

    }

    public ArrayList<WaterList> getDatas(String day){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT W.id,W.name,W.capacity,W.percentage,H.day,H.time ,H.id" +
                " FROM DAY_HISTORY AS H, WATERLIST AS W WHERE H.water_id = W.id AND H.day='" + day+"' ", null);

        ArrayList<WaterList> ret = new ArrayList<WaterList>();
        while(cursor.moveToNext()){
            WaterList temp = new WaterList();
            temp.setWater_id(cursor.getInt(0));
            temp.setName(cursor.getString(1));
            temp.setCapacity(cursor.getInt(2));
            temp.setPercentage(cursor.getInt(3));
            temp.setDay(cursor.getString(4));
            temp.setTime(cursor.getString(5));
            temp.setId(cursor.getInt(6));
            ret.add(temp);

        }
        return ret;
    }

    public String getWaterListString(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM WATERLIST WHERE id ='"+id+"' ", null);

        cursor.moveToNext();
        String ret = cursor.getString(0);
        db.close();

        return ret;
    }

    public int getWaterListInt(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT capacity FROM WATERLIST WHERE id ='"+id+"' ", null);

        cursor.moveToNext();
        int ret = cursor.getInt(0);
        db.close();

        return ret;
    }
}