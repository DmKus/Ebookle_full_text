package com.ebookle.dao.impl;

import com.ebookle.dao.UserDAO;
import com.ebookle.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAOImpl extends AbstractDAOImpl<User, Integer> implements UserDAO {

    protected UserDAOImpl () {
        super(User.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<User> findAllWithDetails () {
        return getSession().createCriteria(User.class)
                .setFetchMode("books", FetchMode.EAGER)
                .list();
    }

    @Override
    public List<User> findAllByRole (String role) {
        return getSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("role", role))
                .add(Restrictions.eq("isActivated", new Boolean(true)))
                .list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public User findByLogin (String login) {
        return (User) getSession()
                .createCriteria(User.class)
                .setFetchMode("books", FetchMode.EAGER)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
    }

    @Override
    public List<User> findAllNotActivatedByKey (String key) {
        return getSession().createCriteria(User.class)
                .add(Restrictions.eq("registeredKey", key))
                .add(Restrictions.eq("isActivated", new Boolean(false)))
                .list();
    }


}
