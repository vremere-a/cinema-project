package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.exeptions.DataProcessingException;
import com.dev.cinema.library.Dao;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.util.HibernateUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Log4j
@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            log.info("Entity " + ticket + "successful added from the DB");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
