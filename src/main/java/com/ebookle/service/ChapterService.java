package com.ebookle.service;

import com.ebookle.dao.ChapterDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.Chapter;
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
public class ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;

    @Transactional
    public void saveOrUpdate (Chapter chapter) {
        chapterDAO.saveOrUpdate(chapter);
    }

    @Transactional(readOnly = true)
    public List<Chapter> findAll () {
        return chapterDAO.findAll();
    }

    @Transactional
    public boolean delete (int id) {
        return chapterDAO.delete(id);
    }

    @Transactional(readOnly = true)
    public Chapter findById (int id) {
        return chapterDAO.findById(id);
    }
    @Transactional(readOnly = true)
    public Chapter findByBookAndChapterNumber(Book book, Integer chapterNumber) {
        return chapterDAO.findByBookAndChapterNumber(book, chapterNumber);
    }

}
