package chc.eletrica8.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;

public class ConnectionFactory {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("EletricaPU");
    private static final EntityManager entityManager = FACTORY.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static Connection getConnection() {
        try {
            EntityManagerImpl factory = (EntityManagerImpl) entityManager;
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) factory.getSession().getSessionFactory();
            return sessionFactoryImpl.getConnectionProvider().getConnection();
        } catch (SQLException e) {
            System.out.println("Erro no getConnection: " + e.getMessage());
        }
        return null;
    }
}
