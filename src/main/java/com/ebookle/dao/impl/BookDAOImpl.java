package com.ebookle.dao.impl;

import com.ebookle.dao.BookDAO;
import com.ebookle.entity.Book;
import com.ebookle.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

//import org.apache.lucene.search.Query;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 24.08.13
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BookDAOImpl extends AbstractDAOImpl<Book, Integer> implements BookDAO{

    public BookDAOImpl () {
        super(Book.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Book> findAllWithAuthors () {
        return getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book findByIdWithAuthor (int id) {
        return (Book) getSession().createCriteria(Book.class).setFetchMode("user", FetchMode.EAGER)
                .add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book findByTitleAndUserIdWithChapters (String title, User user) {
        return (Book) getSession().createCriteria(Book.class).setFetchMode("chapters", FetchMode.EAGER)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("title", title))
                .uniqueResult();
    }

    @Override
    public Book findByTitleAndUserId (String title, User user) {
        return (Book) getSession().createCriteria(Book.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("title", title))
                .uniqueResult();
    }

    @Override
    public List<Book> searchByTitle(String title) {
        FullTextSession fullTextSession = Search.getFullTextSession(getSession());
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Book.class ).get();
        org.apache.lucene.search.Query query =
        qb.keyword().onFields("title","description").matching(title).createQuery();
        org.hibernate.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Book.class);
        return hibQuery.list();
    }

}
