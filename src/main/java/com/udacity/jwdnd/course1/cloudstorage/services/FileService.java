package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

@Service
public class FileService {
    private Logger logger = LoggerFactory.getLogger(FileService.class);
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isFileNameAvailable(String filename) {
        return fileMapper.getFileByFileName(filename) == null;
    }
    public List<File> getAllFiles(Integer userId) {
        return fileMapper.getFileByUserId(userId);
    }

    public String addFile(MultipartFile file, Integer userId) {

        try {
            byte[] bytes = file.getBytes();
            if(bytes.length == 0) {
                return "File cannot be empty";
            }
            File upload = new File(null, file.getOriginalFilename(), file.getContentType(),
                    String.valueOf(file.getSize()), userId, bytes);
            fileMapper.uploadFile(upload);
        } catch (Exception ex) {
            logger.warn("Error uploading file {}", ex.getMessage());
            return "Error uploading file";
        }
        return null;
    }

    public int deleteFile(Integer noteId) {
        return fileMapper.deleteNote(noteId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }
}
