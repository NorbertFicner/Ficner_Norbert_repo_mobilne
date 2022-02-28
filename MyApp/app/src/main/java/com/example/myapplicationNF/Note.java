package com.example.myapplication;

public class Note {
    private String titleOfNote;
    private String contentOfNote;


    public Note(String titleOfNote, String contentOfNote) {
        this.titleOfNote = titleOfNote;
        this.contentOfNote = contentOfNote;
    }

    public String getTitleOfNote() {
        return titleOfNote;
    }

    public String getContentOfNote() {
        return contentOfNote;
    }

    @Override
    public String toString() {
        return titleOfNote;
    }
}
