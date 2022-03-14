package com.example.myapplicationNF;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NoteFileOperations {

    public void saveToFile(ArrayList<Note> list,Context context) throws IOException {
        File path = context.getFilesDir();
        File file = new File(path, "Notes.txt");
        String saveContent = "";
        for(int i=0;i<list.size();i++){
            saveContent = saveContent+list.get(i).getTitleOfNote()+":"+list.get(i).getContentOfNote()+";";

            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(saveContent.getBytes());
            } finally {
                stream.close();
            }
            stream.close();
        }
    }
   public void loadFromFile(ArrayList<Note> list,Context context) throws IOException {
       File path = context.getFilesDir();
       File file = new File(path, "Notes.txt");
       int length = (int) file.length();

       byte[] bytes = new byte[length];

       FileInputStream in = new FileInputStream(file);
       try {
           in.read(bytes);
       } finally {
           in.close();
       }

       String contents = new String(bytes);
       String[] elements = contents.split(";");
           for (int i = 0; i < elements.length; i++) {
               String noteTab[] = elements[i].split(":");
               if (noteTab.length == 1) {
                   Note note = new Note(noteTab[0], "");
                   list.add(note);
               }
               else if (noteTab.length>1){
                   Note note = new Note(noteTab[0], noteTab[1]);
                   list.add(note);
               }

           }
       }







    public void deleteFile(Context context){
        File path = context.getFilesDir();
        File file = new File(path, "Notes.txt");
        boolean deleted = file.delete();
    }

}



