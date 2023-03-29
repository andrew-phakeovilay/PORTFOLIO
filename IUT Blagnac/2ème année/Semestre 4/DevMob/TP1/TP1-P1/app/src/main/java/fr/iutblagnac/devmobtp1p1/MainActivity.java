package fr.iutblagnac.devmobtp1p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doCancel(View v){
        Button chaine = (Button) findViewById(R.id.btn_cancel);
        Toast.makeText(getApplicationContext(), chaine.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void doOK(View v){
        DatePicker dp = (DatePicker) findViewById(R.id.dp_date);
        EditText et = (EditText) findViewById(R.id.et_title);
        String chaine = "";
        if(!et.getText().equals("")){
            chaine += et.getText() + " ";
        }
        chaine +=  dp.getDayOfMonth() + "/" + dp.getMonth()+ "/" + dp.getYear();
        Toast.makeText(getApplicationContext(), chaine,
                Toast.LENGTH_SHORT).show();
    }
}