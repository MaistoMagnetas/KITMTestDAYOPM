package com.example.moksleivis.kitmtestodienaopm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private Button btnUserUpdate, btnUserDelete, btnUserGrizti;
    private EditText userTelNr,userVardasPavarde, userGimimoData;
    private Spinner userSpinner;
    private DatabaseHelper myDb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initItems();

        final Intent intent = getIntent();
        final String vardas = intent.getStringExtra("vardas");
        final String gimimoData = intent.getStringExtra("gimimoData");
        final String telNr = intent.getStringExtra("telNr");
        final String klientoTipas = intent.getStringExtra("klientoTipas");

        userVardasPavarde.setText(vardas);
        userGimimoData.setText(gimimoData);
        userTelNr.setText(telNr);
        if(klientoTipas.equals("Iprastas")){
            userSpinner.setSelection(0);
        }else if(klientoTipas.equals("Lojalus")){
            userSpinner.setSelection(1);
        }else{
            userSpinner.setSelection(2);
        }

        btnUserUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Validation.isValidCredentials((userVardasPavarde.getText().toString()))){
                    userVardasPavarde.requestFocus();
                    userVardasPavarde.setError("Blogi duomenys");
                }else if(!Validation.isValidYear(userGimimoData.getText().toString()) ||
                        Integer.parseInt(userGimimoData.getText().toString())<1900 || Integer.parseInt(userGimimoData.getText().toString())>=2018){
                    userGimimoData.requestFocus();
                    userGimimoData.setError("Bloga data");
                }else if(!Validation.isValidNumber(userTelNr.getText().toString())) {
                    userTelNr.requestFocus();
                    userTelNr.setError("Blogas Tel NR ");
                }else if(userSpinner.getSelectedItem().toString().equals("")){
                    userSpinner.requestFocus();
                    Toast.makeText(UserActivity.this, "Blogas kliento tipas", Toast.LENGTH_SHORT).show();
                }else { //Validation complete
                    myDb.updateUserEntry(userVardasPavarde.getText().toString(), Integer.parseInt(userGimimoData.getText().toString()),
                            Integer.parseInt(userTelNr.getText().toString()), userSpinner.getSelectedItem().toString());
                    Toast.makeText(UserActivity.this, "Vartotojas sekmingai atnaujintas", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(UserActivity.this, SecondActivity.class);
                    startActivity(intent3);
                }
            }
        });

        btnUserDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteUserEntry(userVardasPavarde.getText().toString(),Integer.parseInt(userTelNr.getText().toString()));
                Toast.makeText(UserActivity.this, "Vartotojas sekmingai istrintas", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(UserActivity.this,SecondActivity.class);
                startActivity(intent2);
            }
        });

        btnUserGrizti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserActivity.this,SecondActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void initItems(){
        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        btnUserDelete = (Button) findViewById(R.id.btnUserDelete);
        btnUserGrizti = (Button) findViewById(R.id.btnUserGrizti);
        btnUserUpdate = (Button) findViewById(R.id.btnUserupdate);
        userTelNr = (EditText) findViewById(R.id.userTelNr);
        userVardasPavarde = (EditText) findViewById(R.id.userVardasPavarde);
        userGimimoData = (EditText) findViewById(R.id.userGimimoData);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Iprastas");
        spinnerArray.add("Lojalus");
        spinnerArray.add("Valdytojas");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.userSpinner);
        sItems.setAdapter(adapter);
    }

}
