package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;

    public Credential() {
    }

    public Credential(Integer credentialId,
                      String url,
                      String username,
                      String key,
                      String password,
                      Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public Credential setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Credential setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Credential setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Credential setKey(String key) {
        this.key = key;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Credential setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Credential setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
