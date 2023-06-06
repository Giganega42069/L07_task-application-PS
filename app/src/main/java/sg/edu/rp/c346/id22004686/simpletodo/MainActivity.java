package sg.edu.rp.c346.id22004686.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner addremove;
    EditText input;
    ListView listtask;
    Button addz,clear,remove;
    ArrayList<String> tasks;
    ArrayAdapter<String> showing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.editText);
        addremove = findViewById(R.id.spinner);
        listtask = findViewById(R.id.ListDisplay);
        addz = findViewById(R.id.addition);
        clear = findViewById(R.id.clear);
        remove = findViewById(R.id.remove);

        tasks = new ArrayList<>();

        showing = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);

        listtask.setAdapter(showing);

        addremove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        remove.setEnabled(false);
                        addz.setEnabled(true);
                        input.setHint("Enter new task");

                        break;
                    case 1:
                        addz.setEnabled(false);
                        remove.setEnabled(true);
                        input.setHint("Enter index of task to remove");

                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"MAKE AN INPUT BRUH",Toast.LENGTH_SHORT).show();
                } else {
                    tasks.add(input.getText().toString());
                    showing.notifyDataSetChanged();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"BRO ARE YOU BLIND THERE IS NO TASKS",Toast.LENGTH_SHORT).show();
                } else {
                    tasks.clear();
                    showing.notifyDataSetChanged();
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasks.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                } else{
                    int pos = Integer.parseInt(input.getText().toString());
                    if (pos >= 0 && pos <tasks.size()) {
                        tasks.remove(pos - 1);
                        showing.notifyDataSetChanged();
                    }else{
                        Toast.makeText(MainActivity.this,"Wrong Index number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}