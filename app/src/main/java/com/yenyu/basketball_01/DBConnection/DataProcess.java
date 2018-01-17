package com.yenyu.basketball_01.DBConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yenyu.basketball_01.datatable.Player;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/16.
 */

public class DataProcess {

    Context context;
    public DataProcess(Context context)
    {
        this.context=context;
    }
    public int insertPlayers(ArrayList<Player> myData)
    {
        int count=0;
        MyDBHelper helper=new MyDBHelper(context);
        SQLiteDatabase database=helper.getDatabase();
        ContentValues values;
        for(int i=0;i<myData.size();i++)
        {
            values=new ContentValues();
            values.put("pid",myData.get(i).getPid());
            values.put("number",myData.get(i).getNumber());
            values.put("name",myData.get(i).getName());
            long id=database.insert("players",null,values);
            Log.d("ADD","id "+id+" pid "+myData.get(i).getPid()+
                    " number "+myData.get(i).getNumber()+" name "+myData.get(i).getName());
            count++;
        }
        database.close();
        helper.close();
        return count;
    }

    public Cursor getPlayers(String pid)
    {
        Cursor c=null;
        MyDBHelper helper=new MyDBHelper(context);
        SQLiteDatabase database=helper.getDatabase();
        c=database.rawQuery("select * from players where pid="+pid,null);
        return c;
    }

}
