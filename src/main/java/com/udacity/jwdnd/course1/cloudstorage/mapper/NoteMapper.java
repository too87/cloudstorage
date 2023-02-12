package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES where userId = #{id}")
    List<Note> getNoteByUserId(Integer id);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note Note);


    @Delete("DELETE FROM NOTES where noteId = #{id}")
    int deleteNote(Integer id);

    @Update("UPDATE NOTES set noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    int updateNote(Note Note);
}
