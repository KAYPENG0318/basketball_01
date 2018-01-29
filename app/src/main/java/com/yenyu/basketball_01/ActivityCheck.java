package com.yenyu.basketball_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yenyu.basketball_01.dao.PlayerDAO;
import com.yenyu.basketball_01.dao.Player;

import java.util.ArrayList;

public class ActivityCheck extends AppCompatActivity {

    String pid="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
    }

    public void clickOK(View v)
    {
        ArrayList<Player> list=new ArrayList<>();
        PlayerDAO dp=new PlayerDAO(ActivityCheck.this);
        LinearLayout layout=(LinearLayout) findViewById(R.id.linearLayout);
        //Toast.makeText(ActivityCheck.this,String.valueOf(layout.getChildCount()),Toast.LENGTH_SHORT).show();
        ArrayList<EditText> textArrayList=new ArrayList<>();
        for(int i=0;i<layout.getChildCount();i++)
        {
            if(layout.getChildAt(i) instanceof LinearLayout)
            {
                LinearLayout layout1=(LinearLayout) layout.getChildAt(i);
                for(int j=0;j<layout1.getChildCount();j++)
                {
                    if(layout1.getChildAt(j) instanceof EditText)
                    {
                        textArrayList.add((EditText) layout1.getChildAt(j));
                    }
                }
            }
        }
        int count=0;
        String number,name;
        for(int i=0;i<textArrayList.size();i+=2)
        {
            number=textArrayList.get(i).getText().toString();
            name=textArrayList.get(i+1).getText().toString();
            Log.d("add","number : "+number+ "name : "+name);
            if(number.length()>0 && name.length()>0)
            {
                list.add(new Player(pid,number,name));
                count++;
            }
        }
        if(count>=5)
        {
//        dp.insertPlayers(list);       //測試,故標記
            Intent it = new Intent(ActivityCheck.this,Pick5Activity.class);
            startActivity(it);
        }
        else
        {
            Toast.makeText(ActivityCheck.this,"請至少輸入五人",Toast.LENGTH_SHORT).show();
        }

    }

}
