package com.company.mytodowhitfragment;
import android.net.Uri;


public class User {
    private String textViewname;
    private String textViewdescription;
    private Uri image;
    private  String importance;

    public User(String textViewname, String textViewdescription, Uri image,String importance) {
        this.textViewname = textViewname;
        this.textViewdescription = textViewdescription;
        this.image = image;
        this.importance=importance;
    }

    public String getTextViewname() {
        return textViewname;
    }

    public String getTextViewdescription() {
        return textViewdescription;
    }

    public Uri getImage() {
        return image;
    }

    public String getImportance() {
        return importance;
    }
}



