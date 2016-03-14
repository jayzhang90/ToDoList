package com.example.jayzhang.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnKeyListener {
    private ArrayList<String> toDoThings;
    private ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        toDoThings = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(                                //adapter is used for non-static list
                this,
                android.R.layout.simple_list_item_1,
                toDoThings);
        ListView list = (ListView) findViewById(R.id.toDoList);        // find the id of listView
        list.setAdapter(myAdapter);                                   // set up the adapter
        list.setOnItemLongClickListener(this);                          //make the item on listView clickable

        EditText thingsTodo = (EditText) findViewById(R.id.thingsToDo);
        thingsTodo.setOnKeyListener(this);                             // set up the key listener
    }

    public void add(View view) {
        EditText thingsTodo = (EditText) findViewById(R.id.thingsToDo);
        String thing = thingsTodo.getText().toString();
        toDoThings.add(thing);
        myAdapter.notifyDataSetChanged();                            //notify the adapter when the list changed
    }


    //make the item on listView clickable and long click remove the item
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int index, long id) {
        ListView list = (ListView) findViewById(R.id.toDoList);
        String thing_to_remove =list.getItemAtPosition(index).toString();
        toDoThings.remove(thing_to_remove);
        myAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        // If the event is a key-down event on the "enter" button
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            // Perform action on key press
            add(v);                                                    //call the add method
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("toDoThings", toDoThings);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        toDoThings=bundle.getStringArrayList("toDoThings");
        myAdapter = new ArrayAdapter<>(                                //adapter is used for non-static list
                this,
                android.R.layout.simple_list_item_1,
                toDoThings);
        myAdapter.notifyDataSetChanged();
    }
}
