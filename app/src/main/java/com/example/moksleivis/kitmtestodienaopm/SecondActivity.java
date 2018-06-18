package com.example.moksleivis.kitmtestodienaopm;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        users = getAllEntriesToList();

        listView = (ListView) findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),users);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondActivity.this,UserActivity.class);
                intent.putExtra("value",true);
                intent.putExtra("vardas",users.get(position).getVardasPavarde().toString());
                intent.putExtra("gimimoData",users.get(position).getGimimoData().toString());
                intent.putExtra("telNr",users.get(position).getTelNr().toString());
                intent.putExtra("klientoTipas",users.get(position).getKlientoTipas().toString());
                startActivity(intent);
            }
        });
    }



    private ArrayList<User> getAllEntriesToList(){
        ArrayList<User> users = new ArrayList<User>();
        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
        Cursor res = myDb.getUserData();
        if(res.getCount() == 0){

        }else{
            while (res.moveToNext()){
                User user = new User();
                user.setVardasPavarde(res.getString(1));
                user.setGimimoData(res.getString(2));
                user.setTelNr(res.getString(3));
                user.setKlientoTipas(res.getString(4));
                users.add(user);
            }
        }
        return users
                ;
    }
}
