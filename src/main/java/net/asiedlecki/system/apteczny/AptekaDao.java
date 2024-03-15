package net.asiedlecki.system.apteczny;

import jakarta.persistence.NoResultException;
import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AptekaDao {

    public static void zapiszProduktLeczniczy(ProduktLeczniczy produktLeczniczy) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (SubstancjaCzynna substancjaCzynna: produktLeczniczy.getSubstancjeCzynne()) {
            session.persist(substancjaCzynna);
        }
        session.persist(produktLeczniczy);
        session.getTransaction().commit();
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczeWBazieDanychApteki() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<ProduktLeczniczy> q = session.createQuery("From ProduktLeczniczy ", ProduktLeczniczy.class);
            return q.getResultList();
        }
    }

    public static ProduktLeczniczy pobierzProduktLeczniczyPoGtin(String gtin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<ProduktLeczniczy> q = session.createQuery("FROM ProduktLeczniczy WHERE gtin=:gtin", ProduktLeczniczy.class);
            q.setParameter("gtin", gtin);
            return q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
