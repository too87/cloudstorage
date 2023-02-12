package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialRepresentation;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper,
                             EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<CredentialRepresentation> getAllCredentials(Integer userId) {
        return credentialMapper.getCredentialByUserId(userId)
                .stream()
                .map(credential ->
                        new CredentialRepresentation(
                                credential.getCredentialId(),
                                credential.getUrl(),
                                credential.getUsername(),
                                credential.getPassword(),
                                encryptionService.decryptValue(credential.getPassword(), credential.getKey()))
                        )
                .collect(Collectors.toList());
    }

    public Integer addCredential(Credential credential, Integer userId) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        Credential newCredential = new Credential(null, credential.getUrl(), credential.getUsername(),
                encodedKey, encryptedPassword ,userId);
        return credentialMapper.addCredential(newCredential);
    }

    public Integer editCredential(Credential credential) {
        Optional<Credential> foundCredential = credentialMapper.getCredential(credential.getCredentialId());
        if (foundCredential.isPresent()) {
            String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), foundCredential.get().getKey());
            credential.setPassword(encryptedPassword);
            return credentialMapper.updateCredential(credential);
        }
        return 0;
    }

    public int deleteCredential(Integer noteId) {
        return credentialMapper.deleteCredential(noteId);
    }
}
