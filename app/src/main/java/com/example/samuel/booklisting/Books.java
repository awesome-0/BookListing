package com.example.samuel.booklisting;

/**
 * Created by Samuel on 30/05/2017.
 */

public class Books {
    private String Title,Publisher,Description;
    private  String  authors ;

    public Books(String mTitle,String mPublisher,String mDescription,String mauthor){
        Title = mTitle;
        Publisher = mPublisher;
        Description = mDescription;
        authors = mauthor;

    }

    public String getTitle() {
        return Title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public String getDescription() {
        return Description;
    }

    public String getAuthors() {
        return authors;
    }
}
