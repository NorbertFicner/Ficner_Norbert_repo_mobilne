package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class NoteFileOperations {

    Context context;
    private File path = context.getFilesDir();
    private File file = new File(path, "Notes.txt");
    public void saveToFile(ArrayList<Note> list) throws IOException {
        for(int i=0;i<list.size();i++){
            String saveContent = list.get(i).getTitleOfNote()+":"+list.get(i).getContentOfNote()+";";
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(saveContent.getBytes());
            } finally {
                stream.close();
            }
        }
    }
    public void loadFromFile(){

    }


}
