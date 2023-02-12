package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES where userId = #{id}")
    List<File> getFileByUserId(Integer id);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, fileData, userId) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer uploadFile(File file);

    @Delete("DELETE FROM FILES where fileId = #{id}")
    int deleteNote(Integer id);

    @Select("SELECT * FROM FILES where fileName = #{filename}")
    File getFileByFileName(String filename);

    @Select("SELECT * FROM FILES where fileId = #{id}")
    File getFileById(Integer id);
}
