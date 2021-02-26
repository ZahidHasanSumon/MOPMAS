package com.example.mopmas;

import java.util.Date;

public class NewUser implements Comparable<NewUser> {
    private  String email,username,password,imageUrl,uid;
    private String exerciseName,date,duration,repetition;

    public NewUser() {
    }

    public NewUser(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public NewUser(String email, String username, String password, String uid, String imageUrl) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.imageUrl = imageUrl;
    }

    public NewUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public NewUser(String exerciseName, String date, String duration, String repetition) {
        this.exerciseName = exerciseName;
        this.date = date;
        this.duration = duration;
        this.repetition = repetition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
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

    @Override
    public int compareTo(NewUser o) {
        return 0;
    }
}
