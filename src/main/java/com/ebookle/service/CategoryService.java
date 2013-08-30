package com.ebookle.service;

import com.ebookle.dao.CategoryDAO;
import com.ebookle.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 25.08.13
 * Time: 5:51
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public void saveOrUpdate (Category category) {
        categoryDAO.saveOrUpdate(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll () {
        return categoryDAO.findAll();
    }

    @Transactional
    public boolean delete (int id) {
        return categoryDAO.delete(id);
    }

    @Transactional(readOnly = true)
    public Category findById (int id) {
        return categoryDAO.findById(id);
    }

}
