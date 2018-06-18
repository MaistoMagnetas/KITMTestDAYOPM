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

public class MainActivity extends AppCompatActivity {

    private EditText etVardas, etGimimoData, etTelNr;
    private Spinner spinner;
    private Button btnRegistruoti, btnVisiIrasai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //mETHOD FOR ALL INIT ITEMS
        initItems();

        btnVisiIrasai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        btnRegistruoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Validation.isValidCredentials((etVardas.getText().toString()))){
                    etVardas.requestFocus();
                    etVardas.setError("Blogi duomenys");
                }else if(!Validation.isValidYear(etGimimoData.getText().toString()) ||
                        Integer.parseInt(etGimimoData.getText().toString())<1900 || Integer.parseInt(etGimimoData.getText().toString())>=2018){
                    etGimimoData.requestFocus();
                    etGimimoData.setError("Bloga data");
                }else if(!Validation.isValidNumber(etTelNr.getText().toString())) {
                    etTelNr.requestFocus();
                    etTelNr.setError("Blogas Tel NR ");
                }else if(spinner.getSelectedItem().toString().equals("")){
                    spinner.requestFocus();
                    Toast.makeText(MainActivity.this, "Blogas kliento tipas", Toast.LENGTH_SHORT).show();
                }else{ //Validation complete
                    DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
                    User user = new User();
                    user.setVardasPavarde(etVardas.getText().toString());
                    user.setGimimoData(etGimimoData.getText().toString());
                    user.setTelNr(etTelNr.getText().toString());
                    user.setKlientoTipas(spinner.getSelectedItem().toString());
                    myDb.addUser(user);
                    Toast.makeText(MainActivity.this, "Vartotojas sekmingai registruotas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initItems(){
        btnRegistruoti = (Button) findViewById(R.id.btnRegistruoti);
        btnVisiIrasai = (Button) findViewById(R.id.btnVisiIrasai);
        spinner = (Spinner) findViewById(R.id.spinner);
        etVardas = (EditText) findViewById(R.id.etVardas);
        etGimimoData = (EditText) findViewById(R.id.etGimimoData);
        etTelNr = (EditText) findViewById(R.id.etTelNr);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Iprastas");
        spinnerArray.add("Lojalus");
        spinnerArray.add("Valdytojas");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);
    }
}
