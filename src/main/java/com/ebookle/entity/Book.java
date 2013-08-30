package com.ebookle.entity;


import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "Book")
@Indexed
@AnalyzerDef(name = "ebookleAnalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
                params = {@Parameter(name = "language", value = "English")}),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
                params = @Parameter(name = "language", value = "Russian"))
        })
public class Book implements com.ebookle.entity.Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "title")
    @Field
    @Analyzer(definition="ebookleAnalyzer")
    private String title;

    @Column(name = "description")
    @Field
    @Analyzer(definition="ebookleAnalyzer")
    private String description;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<Chapter>();

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn
    private Prefer prefer;

    @ManyToMany
    @JoinTable
    private List<Tag> tags;
    */
    public Book () {
    }

    public Book (String title, String description, User user, Category category) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    @Override
    public void setId (Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId () {
        return id;
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

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public int getRating () {
        return rating;
    }

    public void setRating (int rating) {
        this.rating = rating;
    }

    public Category getCategory () {
        return category;
    }

    public void setCategory (Category category) {
        this.category = category;
    }

    public List<Chapter> getChapters () {
        return chapters;
    }

    public void setChapters (List<Chapter> chapters) {
        this.chapters = chapters;
    }

/*public Category getCategory () {
        return category;
    }

    public void setCategory (Category category) {
        this.category = category;
    }

    public Prefer getPrefer () {
        return prefer;
    }

    public void setPrefer (Prefer prefer) {
        this.prefer = prefer;
    }

    public List<Tag> getTags () {
        return tags;
    }

    public void setTags (List<Tag> tags) {
        this.tags = tags;
    }
    */
}
