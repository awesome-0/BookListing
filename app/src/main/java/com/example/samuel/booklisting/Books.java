package com.example.samuel.booklisting;

/**
 * Created by Samuel on 30/05/2017.
 */

public class Books {
    private String Title,Publisher,Description,image_link,book_link;
    private  String  authors ;



    public Books(String mTitle, String mPublisher, String mDescription, String mauthor, String mimage_link,String mbook_link){
        Title = mTitle;
        Publisher = mPublisher;
        Description = mDescription;
        authors = mauthor;
        image_link = mimage_link;
        book_link = mbook_link;


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

    public String getImage_link() {
        return image_link;
    }

    public String getBook_link() {
        return book_link;
    }
}
