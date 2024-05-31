package net.asiedlecki.system.apteczny.serwisy;

import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import net.asiedlecki.system.apteczny.model.Lek;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PobieranieLekuZBazyService {

    public static List<Lek> pobierzLeki() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Lek> q = session.createQuery("From Lek ", Lek.class);
            return q.getResultList();
        }
    }
}
