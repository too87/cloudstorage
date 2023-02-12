package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public Integer getNoteId() {
        return noteId;
    }

    public Note setNoteId(Integer noteId) {
        this.noteId = noteId;
        return this;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public Note setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
        return this;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public Note setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Note setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
