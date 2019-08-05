package com.example.minhasanotacoes;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etAnnotation;
    private static final String FILE_PREFERENCES = "FilePreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAnnotation = findViewById(R.id.etAnnotation);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences(FILE_PREFERENCES, 0);
                SharedPreferences.Editor editor = preferences.edit();
                if(etAnnotation.equals("")){
                    Toast.makeText(getApplicationContext(), "Campo vazio", Toast.LENGTH_LONG).show();
                }else{
                    String annotationData = etAnnotation.getText().toString();
                    editor.putString("annotationData", annotationData);
                    editor.commit();
                }

            }
        });

        SharedPreferences preferences = getSharedPreferences(FILE_PREFERENCES, 0);
        if(preferences.contains("annotationData")){
            String annotationData = preferences.getString("annotationData", "Sem anotações");
            etAnnotation.setText(annotationData);
        }else{
            Toast.makeText(getApplicationContext(), "Sem anotações encontradas ", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
