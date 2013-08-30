package com.ebookle.dao;

import com.ebookle.entity.Book;
import com.ebookle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:10
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO extends AbstractDAO<User, Integer>{

    public List<User> findAllWithDetails();
    public List<User> findAllByRole (String role);
    public User findByLogin (String login);
    public List<User> findAllNotActivatedByKey (String key);
}
