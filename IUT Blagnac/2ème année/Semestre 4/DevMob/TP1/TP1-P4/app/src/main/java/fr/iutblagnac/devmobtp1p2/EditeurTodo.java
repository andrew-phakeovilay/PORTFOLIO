package fr.iutblagnac.devmobtp1p2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import fr.iutblagnac.devmobtp1p2.model.TodoConstants;

public class EditeurTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeur_todo);
        // Récupérer l'intent requête
        Intent i;
        i = this.getIntent();
        if (i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 100 || i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200) {
            // Récupérer les datas sur lesquelles travailler
            // l'Id de Todo
            int taskid = i.getIntExtra(TodoConstants.TODO_ID, -1);
            if (taskid == -1) {
                // Si pas de Todo : Réponse de fin cancel
                Toast.makeText(getApplicationContext(), "Pas de tache", Toast.LENGTH_SHORT)
                        .show();
                Intent result = new Intent();
                setResult(Activity.RESULT_CANCELED, result);
                this.finish();
            }
            // Autres infos
            String comm = i.getStringExtra(TodoConstants.TODO_COMMENT);
            String todo = i.getStringExtra(TodoConstants.TODO_TODO);
            String title = i.getStringExtra(TodoConstants.TODO_TITLE);
            String ww = i.getStringExtra(TodoConstants.TODO_WHITHWHO);
            int day, month, year;
            day = i.getIntExtra(TodoConstants.TODO_DUEDAY, 1);
            month = i.getIntExtra(TodoConstants.TODO_DUEMONTH, 1);
            year = i.getIntExtra(TodoConstants.TODO_DUEYEAR, 1999);

            // Positionnement dans les composants du layout
            EditText et;

            et = (EditText) findViewById(R.id.et_todo);
            if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200){
                et.setEnabled(false);
            }
            et.setText(todo);

            et = (EditText) findViewById(R.id.et_linkwith);
            if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200){
                et.setEnabled(false);
            }
            et.setText(ww);

            et = (EditText) findViewById(R.id.et_comment);
            if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200){
                et.setEnabled(false);
            }
            et.setText(comm);

            DatePicker dp = (DatePicker) findViewById(R.id.dp_date);
            dp.init(year, month - 1, day, null);
            if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200){
                dp.setEnabled(false);
            }

            et = (EditText) findViewById(R.id.et_title);
            if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200){
                et.setEnabled(false);
            }
            et.setText(title);
        }
        if(i.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 200) {
            Button btnCancel = (Button) findViewById(R.id.btn_cancel);
            btnCancel.setEnabled(false);
        }
    }

    public void doCancel(View v) {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        this.finish();
    }

    public void doOK(View v) {
        Intent intent = this.getIntent();
        if (intent.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 100 || intent.getIntExtra(TodoConstants.EDITEUR_MODE, -1) == 300) {
            DatePicker dp = (DatePicker) findViewById(R.id.dp_date);
            EditText et_title = (EditText) findViewById(R.id.et_title);
            EditText et_todo = (EditText) findViewById(R.id.et_todo);
            EditText et_with = (EditText) findViewById(R.id.et_linkwith);
            EditText et_comment = (EditText) findViewById(R.id.et_comment);
            intent.putExtra(TodoConstants.TODO_TITLE, et_title.getText().toString());
            intent.putExtra(TodoConstants.TODO_TODO, et_todo.getText().toString());
            intent.putExtra(TodoConstants.TODO_COMMENT, et_comment.getText().toString());
            intent.putExtra(TodoConstants.TODO_WHITHWHO, et_with.getText().toString());
            intent.putExtra(TodoConstants.TODO_DUEDAY, dp.getDayOfMonth());
            intent.putExtra(TodoConstants.TODO_DUEMONTH, dp.getMonth());
            intent.putExtra(TodoConstants.TODO_DUEYEAR, dp.getYear());
            setResult(Activity.RESULT_OK, intent);
        }
        this.finish();
    }
}