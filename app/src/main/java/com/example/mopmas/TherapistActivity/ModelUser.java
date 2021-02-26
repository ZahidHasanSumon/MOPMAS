package com.example.mopmas.TherapistActivity;

public class ModelUser {
    String username,email,imageUrl,uid;

    public ModelUser() {
    }

    public ModelUser(String username, String email, String imageUrl, String uid) {
        this.username = username;
        this.email = email;
        this.imageUrl = imageUrl;
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
