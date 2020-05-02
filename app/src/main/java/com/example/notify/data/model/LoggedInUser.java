package com.example.notify.data.model;

import android.net.Uri;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private Uri photoUrl;

    public LoggedInUser(String userId, String displayName, Uri photoUrl) {
        this.userId = userId;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Uri getPhotoUrl(){
        return photoUrl;
    }
}
