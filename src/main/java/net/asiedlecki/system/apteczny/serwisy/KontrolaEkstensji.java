package net.asiedlecki.system.apteczny.serwisy;

import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import net.asiedlecki.system.apteczny.model.Lek;
import net.asiedlecki.system.apteczny.model.PracownikApteki;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class KontrolaEkstensji {

    public static void zaladujBazeDanychDoEkstensji() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Query<PracownikApteki> pracownikAptekiQuery = session.createQuery("from PracownikApteki", PracownikApteki.class);
            PracownikApteki.getPracownicyEkstensja().addAll(pracownikAptekiQuery.list());

            Query<Lek> lekiQuery = session.createQuery("from Lek", Lek.class);
            Lek.getLekiEksensja().addAll(lekiQuery.list());

            session.getTransaction().commit();
        }
    }
}
