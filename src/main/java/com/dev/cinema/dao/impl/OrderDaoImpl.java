package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exeptions.DataProcessingException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Log4j
@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            log.info("Entity " + order + "successful added from the DB");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add order entity:"
                    + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> orderCriteriaQuery =
                    criteriaBuilder.createQuery(Order.class);
            Root<Order> orderRoot =
                    orderCriteriaQuery.from(Order.class);
            orderRoot.fetch("tickets");
            Predicate predicate =
                    criteriaBuilder.equal(orderRoot.get("user"), user);
            orderCriteriaQuery.select(orderRoot).where(predicate);
            return session.createQuery(orderCriteriaQuery.distinct(true)).getResultList();
        }
    }
}
