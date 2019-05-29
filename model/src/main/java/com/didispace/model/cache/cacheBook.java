package com.didispace.model.cache;

public class cacheBook {

    private String isbn;
    private String title;
    public String concent;
    
    public cacheBook(String isbn, String title,String concent) {
        this.isbn = isbn;
        this.title = title;
        this.concent = concent;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" + "isbna='" + isbn + '\'' + ", titlew='" + title + '\'' + ", content='" + concent + '\'' +  '}';
    }
}
