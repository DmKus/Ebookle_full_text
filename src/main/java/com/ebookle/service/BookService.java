package com.ebookle.service;

import com.ebookle.dao.BookDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Transactional
    public void saveOrUpdate (Book book) {
        bookDAO.saveOrUpdate(book);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll () {
        return bookDAO.findAll();
    }

    @Transactional
    public boolean delete (int id) {
        return bookDAO.delete(id);
    }

    @Transactional(readOnly = true)
    public Book findById (int id) {
        return bookDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public Book findByIdWithAuthor (int id) {
        return bookDAO.findByIdWithAuthor(id);
    }

    @Transactional
    public List<Book> findAllWithAuthors () {
        return bookDAO.findAllWithAuthors();
    }


    @Transactional(readOnly = true)
    public Book findByTitleAndUserIdWithChapters (String title, User user) {
        return bookDAO.findByTitleAndUserIdWithChapters(title, user);
    }

    @Transactional(readOnly = true)
    public Book findByTitleAndUserId (String title, User user) {
        return bookDAO.findByTitleAndUserId(title, user);
    }
    @Transactional
    public List<Book> searchByTitle (String title) {
        return bookDAO.searchByTitle(title);
    }

}
