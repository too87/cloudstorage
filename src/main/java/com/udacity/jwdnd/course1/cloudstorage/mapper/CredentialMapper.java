package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS where userId = #{id}")
    List<Credential> getCredentialByUserId(Integer id);
    @Select("SELECT * FROM CREDENTIALS where credentialId = #{id}")
    Optional<Credential> getCredential(Integer id);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS set url = #{url}, username = #{username}, password = #{password} " +
            "WHERE credentialId = #{credentialId}")
    Integer updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS where credentialId = #{id}")
    int deleteCredential(Integer id);
}
