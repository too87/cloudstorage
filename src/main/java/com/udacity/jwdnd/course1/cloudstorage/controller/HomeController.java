package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;

    private final FileService fileService;
    private final CredentialService credentialService;
    private static final String NOTES = "notes";
    private static final String FILES = "files";
    private static final String CREDENTIALS = "credentials";
    private static final String HOMEPAGE = "home";
    private static final String LOGIN_PAGE = "login";
    private static final String RESULT_PAGE = "result";

    public HomeController(NoteService noteService,
                          UserService userService,
                          FileService fileService,
                          CredentialService credentialService) {

        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;

        model.addAttribute(NOTES, noteService.getAllNotes(userId));
        model.addAttribute(FILES, fileService.getAllFiles(userId));
        model.addAttribute(CREDENTIALS, credentialService.getAllCredentials(userId));

        return HOMEPAGE;
    }

    // note
    @GetMapping("/deleteNote")
    public String deleteNote(Authentication authentication, @RequestParam(name="id") int id, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;

        int rowsUpdated = noteService.deleteNote(id);
        model.addAttribute(NOTES, noteService.getAllNotes(userId));
        if(rowsUpdated > 0) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return RESULT_PAGE;
    }
    @PostMapping("/note")
    public String addNote(Authentication authentication, Note note, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;

        note.setUserId(userId);
        int rowsUpdated;
        if (note.getNoteId() != null) {
            rowsUpdated = noteService.updateNote(note);
        } else {
            rowsUpdated = noteService.addNote(note);
        }
        if(rowsUpdated > 0) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return RESULT_PAGE;
    }

    // file
    @GetMapping("/deleteFile")
    public String deleteFile(Authentication authentication, @RequestParam(name="id") int id, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;
        int rowsUpdated = fileService.deleteFile(id);
        model.addAttribute(FILES, fileService.getAllFiles(userId));

        if(rowsUpdated > 0) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return RESULT_PAGE;
    }

    @PostMapping("/file")
    public String addFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile file, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;

        String errorMessage;
        if (!fileService.isFileNameAvailable(file.getOriginalFilename())) {
            errorMessage = "The filename already exists.";
            model.addAttribute("errorMessage", errorMessage);
            return RESULT_PAGE;
        }

        errorMessage = fileService.addFile(file, userId);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return RESULT_PAGE;
        }

        model.addAttribute("success", true);
        return RESULT_PAGE;
    }

    @GetMapping("/file")
    public void downloadFile(@Param("id") Integer id,
                             HttpServletResponse response) throws IOException {
        File file = fileService.getFile(id);
        if (file != null) {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+file.getFileName();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(file.getFileData());
            outputStream.close();
        }
    }

    //credential
    @PostMapping("/credential")
    public String addCredential(Authentication authentication, Credential credential, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;
        int rowsUpdated;
        if (credential.getCredentialId() != null) {
            rowsUpdated = credentialService.editCredential(credential);
        } else {
            rowsUpdated = credentialService.addCredential(credential, userId);
        }

        if(rowsUpdated > 0) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return RESULT_PAGE;
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(Authentication authentication, @RequestParam(name="id") int id, Model model) {
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        if(userId == null) return LOGIN_PAGE;

        int rowsUpdated = credentialService.deleteCredential(id);
        model.addAttribute(CREDENTIALS, credentialService.getAllCredentials(userId));

        if(rowsUpdated > 0) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return RESULT_PAGE;
    }
}

