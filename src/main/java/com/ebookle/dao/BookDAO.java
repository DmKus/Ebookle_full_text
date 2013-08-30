package com.ebookle.dao;

import com.ebookle.entity.Book;
import com.ebookle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public interface BookDAO extends AbstractDAO<Book, Integer> {

    public List<Book> findAllWithAuthors ();
    public Book findByIdWithAuthor (int id);
    public Book findByTitleAndUserIdWithChapters (String title, User user);
    public Book findByTitleAndUserId (String title, User user);
    public List<Book> searchByTitle (String title);
}