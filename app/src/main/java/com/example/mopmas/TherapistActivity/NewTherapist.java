package com.example.mopmas.TherapistActivity;

public class NewTherapist {
    private String email,username,licenseNo,password;
    public NewTherapist() {
    }

    public NewTherapist(String email, String username, String licenseNo, String password) {
        this.email = email;
        this.username = username;
        this.licenseNo = licenseNo;
        this.password = password;
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

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
