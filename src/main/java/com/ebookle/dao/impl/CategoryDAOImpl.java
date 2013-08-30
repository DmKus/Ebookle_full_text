package com.ebookle.dao.impl;

import com.ebookle.dao.CategoryDAO;
import com.ebookle.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class CategoryDAOImpl extends AbstractDAOImpl<Category, Integer> implements CategoryDAO {

    protected CategoryDAOImpl () {
        super(Category.class);
    }
}
