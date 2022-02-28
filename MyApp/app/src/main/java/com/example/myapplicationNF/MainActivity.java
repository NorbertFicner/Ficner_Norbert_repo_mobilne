package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    long arrayItemIndex;
    NoteFileOperations nfo = new NoteFileOperations();
    ArrayList<Note> list = new ArrayList<Note>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        ListView listView = (ListView) findViewById(R.id.listOfNotes);
        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitleOfNote);
        EditText editTextNoteContent = (EditText) findViewById(R.id.editTextNote);


        ArrayAdapter<Note> arrayAdapter = new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String noteContent  = editTextNoteContent.getText().toString();
            boolean hasDuplicate = false;

                for (int i = 0; i < list.size(); i++) {
                    if (title.equals(list.get(i).getTitleOfNote())) {
                        hasDuplicate = true;
                        Note note = new Note(title,noteContent);
                        list.set(i,note);
                        Toast.makeText(MainActivity.this,"Nadpisałeś notatkę",Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        hasDuplicate = false;
                    }
                }
            if(hasDuplicate == false){
                Note note = new Note(title,noteContent);
                list.add(note);
                arrayAdapter.notifyDataSetChanged();
            }

            try {
                nfo.saveToFile(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonDelete.setOnClickListener(v ->{
            list.remove(Integer.parseInt(String.valueOf(arrayItemIndex)));
            arrayAdapter.notifyDataSetChanged();
            editTextNoteContent.setText("");
            editTextTitle.setText("");
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                String title = arrayAdapter.getItem(position).toString();
                for(int i=0;i<list.size();i++){
                    if(title.equals(list.get(i).getTitleOfNote())){
                        arrayItemIndex = arrayAdapter.getItemId((int) id);
                        editTextTitle.setText(list.get(i).getTitleOfNote());
                        editTextNoteContent.setText(list.get(i).getContentOfNote());
                        break;
                    }
                }
            }
        });


    }
}