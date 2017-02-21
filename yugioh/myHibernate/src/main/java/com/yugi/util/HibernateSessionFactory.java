package com.yugi.util;

/**
 * Created by Administrator on 2016/10/14.
 */

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 */
@SuppressWarnings("deprecation")
public class HibernateSessionFactory {
    /**
     * Location of hibernate.cfg.xml file. Location should be on the classpath
     * as Hibernate uses #resourceAsStream style lookup for its configuration
     * file. The default classpath location of the hibernate config file is in
     * the default package. Use #setConfigFile() to update the location of the
     * configuration file for the current session.
     */
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> transactionThreadLocal = new ThreadLocal<Transaction>();
    private static Configuration configuration = new Configuration();
    private static SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;
    private static Logger logger = Logger.getLogger(HibernateSessionFactory.class);

    static {
        try {
            //      Configuration configuration = new Configuration();
            //      configuration.configure();
            //      ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
            //      ServiceRegistry serviceRegistry = serviceRegistryBuilder.buildServiceRegistry();
            //      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (HibernateException e) {
            logger.error("buildSessionFactory");
            throw new HibernateException(e);
        }
    }

    private HibernateSessionFactory() {

    }

    public static SessionFactory getSessionFactory() {
        //      // Instead of a static variable, use JNDI:
        //      SessionFactory sessionFactory = null;
        //      try
        //      {
        //          Context context = new InitialContext();
        //          String jndiName = "java:hibernate/HibernateFactory";
        //          sessionFactory = (SessionFactory) context.lookup(jndiName);
        //      }
        //      catch (NamingException ex)
        //      {
        //          throw new InfrastructureException(ex);
        //      }
        //      return sessionFactory;

        return sessionFactory;
    }

    public static void rebuildSessionFactory() {
        synchronized (sessionFactory) {
            try {
                configuration.configure(configFile);
                sessionFactory = configuration.buildSessionFactory();
            }
            catch (HibernateException e) {
                logger.error("rebuildSessionFactory");
                throw new HibernateException(e);
            }
        }
    }

    public static Session getSession() {
        Session session = (Session) sessionThreadLocal.get();

        try {
            if (session == null || !session.isOpen()) {
                if (sessionFactory == null) {
                    rebuildSessionFactory();
                }

                session = (sessionFactory != null) ? sessionFactory.openSession() : null;
                sessionThreadLocal.set(session);
            }
        }
        catch (HibernateException e) {
            logger.error("openSession");
            throw new HibernateException(e);
        }

        return session;
    }

    public static void closeSession() {
        Session session = (Session) sessionThreadLocal.get();
        sessionThreadLocal.set(null);
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        catch (HibernateException e) {
            logger.error("close");
            throw new HibernateException(e);
        }
    }

    public static void beginTransaction() {
        Transaction transaction = (Transaction) transactionThreadLocal.get();
        try {
            if (transaction == null) {
                transaction = getSession().beginTransaction();
                transactionThreadLocal.set(transaction);
            }
        }
        catch (HibernateException e) {
            logger.error("beginTransaction");
            throw new HibernateException(e);
        }
    }

    public static void commitTransaction() {
        Transaction transaction = (Transaction) transactionThreadLocal.get();

        try {
            if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
                transaction.commit();
            }
            transactionThreadLocal.set(null);
        }
        catch (HibernateException e) {
            logger.error("commitTransaction");
            throw new HibernateException(e);
        }
    }

    public static void rollbackTransaction() {
        Transaction transaction = (Transaction) transactionThreadLocal.get();
        try {
            transactionThreadLocal.set(null);
            if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
                transaction.rollback();
            }
        }
        catch (HibernateException e) {
            logger.error("rollbackTransaction");
            throw new HibernateException(e);
        }
        finally {
            closeSession();
        }
    }

    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void add(Object entity) {
        try {
            beginTransaction();
            getSession().save(entity);
            commitTransaction();
            rollbackTransaction();
        }
        catch (HibernateException e) {
            logger.error("add");
            throw new HibernateException(e);
        }
    }

    public static void update(Object entity) {
        try {
            beginTransaction();
            getSession().update(entity);
            commitTransaction();
            rollbackTransaction();
        }
        catch (HibernateException e) {
            logger.error("update");
            throw new HibernateException(e);
        }
    }

    public static void delete(Object entity) {
        try {
            beginTransaction();
            getSession().delete(entity);
            commitTransaction();
            rollbackTransaction();
        }
        catch (HibernateException e) {
            logger.error("delete");
            throw new HibernateException(e);
        }
    }

    public static Object get(Class<?> clazz, Serializable id) {
        Object object = null;
        try {
            object = getSession().get(clazz, id);
        }
        catch (HibernateException e) {
            logger.error("get");
            throw new HibernateException(e);
        }
        finally {
            closeSession();
        }
        return object;
    }
}