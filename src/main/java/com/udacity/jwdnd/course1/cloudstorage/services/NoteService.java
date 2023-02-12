package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(Integer userId) {
        return noteMapper.getNoteByUserId(userId);
    }

    public int addNote(Note note) {
        return noteMapper.addNote(note);
    }

    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }
}
