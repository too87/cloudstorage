package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialRepresentation {
    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private String unencryptedPassword;

    public CredentialRepresentation(Integer credentialId,
                                    String url,
                                    String username,
                                    String password,
                                    String unencryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.unencryptedPassword = unencryptedPassword;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public CredentialRepresentation setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CredentialRepresentation setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialRepresentation setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialRepresentation setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUnencryptedPassword() {
        return unencryptedPassword;
    }

    public CredentialRepresentation setUnencryptedPassword(String unencryptedPassword) {
        this.unencryptedPassword = unencryptedPassword;
        return this;
    }
}
