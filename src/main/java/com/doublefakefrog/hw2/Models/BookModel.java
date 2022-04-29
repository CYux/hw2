package com.doublefakefrog.hw2.Models;

public class BookModel {
    private int book_id;
    private String book_name;
    private int topic_id;
    private String topic_name;
    private String author_name;
    private Boolean available;

    public BookModel() {
    }

    public BookModel(int book_id, String book_name, int topic_id,String topic_name, String author_name, Boolean available) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.topic_id=topic_id;
        this.topic_name = topic_name;
        this.author_name = author_name;
        this.available = available;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public Boolean isAvailable() {
        return available;
    }

}
