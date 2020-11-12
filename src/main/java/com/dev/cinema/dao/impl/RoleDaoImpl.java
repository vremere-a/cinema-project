package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exeptions.DataProcessingException;
import com.dev.cinema.model.Role;

import javax.persistence.criteria.*;

import com.dev.cinema.model.RoleName;
import com.dev.cinema.model.ShoppingCart;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            log.info("Entity " + role + "successful added from the DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> roleRoot = criteriaQuery.from(Role.class);
            Predicate predicate = criteriaBuilder.equal(roleRoot.get("roleName"), roleName);
            criteriaQuery.select(roleRoot).where(predicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        }
//        try (Session session = sessionFactory.openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Role> roleCriteriaQuery = criteriaBuilder.createQuery(Role.class);
//            Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
//            roleRoot.fetch("roleName", JoinType.LEFT);
//            Predicate predicate = criteriaBuilder.equal(roleRoot.get("user"), user);
//            roleCriteriaQuery.select(roleRoot).where(predicate);
//            return session.createQuery(roleCriteriaQuery).getSingleResult();
//        }
    }
}
