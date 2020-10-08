package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exeptions.DataProcessingException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shoppingCart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> shoppingCartCriteriaQuery =
                    criteriaBuilder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> shoppingCartRoot =
                    shoppingCartCriteriaQuery.from(ShoppingCart.class);
            shoppingCartRoot.fetch("tickets", JoinType.LEFT);
            Predicate predicate =
                    criteriaBuilder.equal(shoppingCartRoot.get("user"), user);
            shoppingCartCriteriaQuery.select(shoppingCartRoot).where(predicate);
            return session.createQuery(shoppingCartCriteriaQuery).getSingleResult();
        }
    }

    @Override

    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart entity with id " + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
