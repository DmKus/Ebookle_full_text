package com.ebookle.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "Chapter")
public class Chapter implements Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "title")
    private String title;

    @Column(name = "text", columnDefinition="TEXT")
    private String text;

    @Column(name = "chapterNumber")
    private int chapterNumber;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Chapter() {
    }

    public Chapter (String title, String text, Book book, int chapterNumber) {
        this.title = title;
        this.text = text;
        this.book = book;
        this.chapterNumber = chapterNumber;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public int getChapterNumber () {
        return chapterNumber;
    }

    public void setChapterNumber (int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Integer getVersion () {
        return version;
    }

    public void setVersion (Integer version) {
        this.version = version;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public Book getBook () {
        return book;
    }

    public void setBook (Book book) {
        this.book = book;
    }
}
