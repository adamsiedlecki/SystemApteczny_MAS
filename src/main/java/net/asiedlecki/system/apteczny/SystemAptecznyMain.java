package net.asiedlecki.system.apteczny;

import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SystemAptecznyMain {
    public static void main(String[] args) {

    }

    public static void zapiszInicjalneProduktyLecznicze() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProduktLeczniczy produkt = new ProduktLeczniczy("gtin-1", List.of(new SubstancjaCzynna(null, "psylocybina grzybkowa")));
        for (SubstancjaCzynna substancjaCzynna: produkt.getSubstancjeCzynne()) {
            session.persist(substancjaCzynna);
        }
        session.persist(produkt);
        session.getTransaction().commit();
    }
}
