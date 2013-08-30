package com.ebookle.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
@Table(name = "User")
public class User implements Entity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<Book>();

    @Column(name = "isActivated")
    private Boolean isActivated;

    @Column(name = "role")
    private String role;

    /*

    @ManyToOne
    @JoinColumn
    private Prefer prefer;
*/
    @Column(name = "registeredKey")
    private String registeredKey;

    public User () {
    }

    public User (String login, String password, String email, String name, String surname, String registeredKey, String role, Boolean activated) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.registeredKey = registeredKey;
        this.role = role;
        isActivated = activated;
    }

    @Override
    public void setId (Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId () {
        return id;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String login) {
        this.login = login;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public Boolean getActivated () {
        return isActivated;
    }

    public void setActivated (Boolean activated) {
        isActivated = activated;
    }

    public Set<Book> getBooks () {
        return books;
    }

    public void setBooks (Set<Book> books) {
        this.books = books;
    }

    public String getRole () {
        return role;
    }

    public void setRole (String role) {
        this.role = role;
    }

    /*
        public Role getRole () {
            return role;
        }

        public void setRole (Role role) {
            this.role = role;
        }

        public Prefer getPrefer () {
            return prefer;
        }

        public void setPrefer (Prefer prefer) {
            this.prefer = prefer;
        }
    */
    public String getRegisteredKey () {
        return registeredKey;
    }

    public void setRegisteredKey (String registeredKey) {
        this.registeredKey = registeredKey;
    }
}
