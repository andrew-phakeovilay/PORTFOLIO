package fr.iutblagnac.devmobtp1p2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.iutblagnac.devmobtp1p2.model.Todo;
import fr.iutblagnac.devmobtp1p2.model.TodoConstants;

public class MainActivity extends AppCompatActivity {

    /**
     * 1. Comment est mis en œuvre l’id des Todo ?
     * A chaque création d'un objet Todo, l'id est Todo.lastId+1 et le lastId est incrémenté de 1 à chaque création
     */

    private ArrayAdapter<Todo> tlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tlAdapter = new ArrayAdapter<Todo>(this, android.R.layout.simple_list_item_1, createListTodoTest());
        ListView lv_todos = (ListView) findViewById(R.id.lv_todos);
        lv_todos.setAdapter(tlAdapter);
        this.registerForContextMenu(lv_todos);
    }

    private List<Todo> createListTodoTest() {
        ArrayList<Todo> testListTodo;
        testListTodo = new ArrayList<>();
        testListTodo.add(new Todo("devoir 1",
                "Deposer le TP1 de CDAM deja en retard"));
        testListTodo.add(new Todo("devoir 2",
                "Deposer le TP2 de CDAM deja en retard"));
        testListTodo.add(new Todo("devoir 3",
                "Deposer le TP3 de CDAM, il est encore temps"));
        testListTodo.add(new Todo("Courses",
                "Du lait, du beurre et des epinards"));
        testListTodo.add(new Todo("Fete",
                "Organiser jeudi soir !! avant jeudi soir !!"));
        testListTodo.add(new Todo("devoir 4",
                "Deposer le TP4, il est encore temps", "My colleague", "", 23, 3, 2023));
        testListTodo.add(new Todo("PJT", "Preparer rendu pjt",
                "My colleagues", "Ya du taf a faire\nFaut pas trainer",
                24, 3, 2023));
        for (int i = 0; i < 10; i++) {
            testListTodo.add(new Todo("Titre " + i, "Todo " + i));
        }
        return testListTodo;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_todo_menu, menu);
        menu.setHeaderTitle("Choisir");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Intent intentETodo;
        intentETodo = new Intent(this, EditeurTodo.class);
        intentETodo.putExtra(TodoConstants.TODO_ID, tlAdapter.getItem(info.position).getId());
        intentETodo.putExtra(TodoConstants.TODO_COMMENT, tlAdapter.getItem(info.position).getComment());
        intentETodo.putExtra(TodoConstants.TODO_DUEDAY, tlAdapter.getItem(info.position).getDayDue());
        intentETodo.putExtra(TodoConstants.TODO_DUEMONTH, tlAdapter.getItem(info.position).getMonthDue());
        intentETodo.putExtra(TodoConstants.TODO_DUEYEAR, tlAdapter.getItem(info.position).getYearDue());
        intentETodo.putExtra(TodoConstants.TODO_TITLE, tlAdapter.getItem(info.position).getTitle());
        intentETodo.putExtra(TodoConstants.TODO_WHITHWHO, tlAdapter.getItem(info.position).getWithWho());
        intentETodo.putExtra(TodoConstants.TODO_TODO, tlAdapter.getItem(info.position).getTodo());

        if (item.getItemId() == R.id.listview_menu_show_todo_id) {
            intentETodo.putExtra(TodoConstants.EDITEUR_MODE, TodoConstants.SHOW_TODO);
            arl.launch(intentETodo);
        }
        if (item.getItemId() == R.id.listview_menu_modify_todo_id) {
            intentETodo.putExtra(TodoConstants.EDITEUR_MODE, TodoConstants.MODIFY_TODO);
            intentETodo.putExtra("position", info.position);
            arl.launch(intentETodo);
        }
        if (item.getItemId() == R.id.listview_menu_delete_todo_id) {
            deleteAlert(tlAdapter.getItem(info.position));
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_ajouter_todo_id) {
            Toast.makeText(this, "Ajouter un todo", Toast.LENGTH_SHORT).show();
            Intent intentETodo;
            intentETodo = new Intent(this, EditeurTodo.class);
            intentETodo.putExtra(TodoConstants.EDITEUR_MODE, TodoConstants.ADD_TODO);
            arl.launch(intentETodo);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAlert(Todo _todo) {
        // On construit une FABRIQUE
        AlertDialog.Builder quitDBuilder = new AlertDialog.Builder(this); // this == Activity
        // On paramètre la FABRIQUE
        quitDBuilder.setTitle("Supprimer vraiment ?");
        quitDBuilder.setMessage("Voulez vous quitter l'activité en cours ?");
        quitDBuilder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tlAdapter.remove(_todo);
                        tlAdapter.notifyDataSetChanged();
                    }
                }
        );
        String but_cancel_lib = this.getResources().getString(R.string.libTextCancel);
        quitDBuilder.setNeutralButton(
                but_cancel_lib,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Ferme ce dialogue
                    }
                }
        );
        quitDBuilder.setCancelable(false); // Empêche le bouton “back” de “canceler” le dialogue
        // On CREE un DIALOGUE à partir de la FABRIQUE et on l’affiche
        // On pourrait réutiliserla la fabrique pour créer le même dialogue
        AlertDialog quiDialog = quitDBuilder.create();
        quiDialog.show();
    }

    ActivityResultLauncher<Intent> arl = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data.getIntExtra("position", -1) != -1){
                            tlAdapter.getItem(data.getIntExtra("position", -1)).setValues(
                                    data.getStringExtra(TodoConstants.TODO_TITLE),
                                    data.getStringExtra(TodoConstants.TODO_TODO),
                                    data.getStringExtra(TodoConstants.TODO_WHITHWHO),
                                    data.getStringExtra(TodoConstants.TODO_COMMENT),
                                    data.getIntExtra(TodoConstants.TODO_DUEDAY, 1),
                                    data.getIntExtra(TodoConstants.TODO_DUEMONTH, 1),
                                    data.getIntExtra(TodoConstants.TODO_DUEYEAR, 2023)
                            );
                        }else{
                            Todo newTodo = new Todo(data.getStringExtra(TodoConstants.TODO_TITLE), data.getStringExtra(TodoConstants.TODO_TODO), data.getStringExtra(TodoConstants.TODO_WHITHWHO), data.getStringExtra(TodoConstants.TODO_COMMENT), data.getIntExtra(TodoConstants.TODO_DUEDAY, 1), data.getIntExtra(TodoConstants.TODO_DUEMONTH, 1), data.getIntExtra(TodoConstants.TODO_DUEYEAR, 2023));
                            tlAdapter.add(newTodo);
                        }
                        tlAdapter.notifyDataSetChanged();
                    }
                }
            });
}

