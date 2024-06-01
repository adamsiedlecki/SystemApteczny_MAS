package net.asiedlecki.system.apteczny.serwisy;

import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import net.asiedlecki.system.apteczny.model.Lek;
import net.asiedlecki.system.apteczny.model.SubstancjaCzynna;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DaneInicjalne {

    public static void zapiszInicjalneDane() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            truncate(session);

            SubstancjaCzynna paracetamol = new SubstancjaCzynna();
            paracetamol.setNazwaPolska("paracetamol");
            session.persist(paracetamol);

            SubstancjaCzynna tramadol = new SubstancjaCzynna();
            tramadol.setNazwaPolska("tramadol");
            tramadol.setOpisNarkotycznej("po tym mozna widzieć fraktale");
            session.persist(tramadol);

            Lek lekApap = new Lek();
            lekApap.setNazwaPolska("apap");
            lekApap.setCena(BigDecimal.TEN);
            lekApap.setSubstacjeCzynne(List.of(paracetamol));
            lekApap.setCzyWymagaRecepty(false);
            lekApap.setIloscOpakowanWmagazynie(23);

            Lek lekZaldiar  = new Lek();
            lekZaldiar .setNazwaPolska("zaldiar");
            lekZaldiar .setCena(BigDecimal.TEN);
            lekZaldiar .setSubstacjeCzynne(List.of(paracetamol, tramadol));
            lekZaldiar .setCzyWymagaRecepty(true);
            lekZaldiar .setIloscOpakowanWmagazynie(1);
            session.persist(lekZaldiar);

            session.persist(lekApap);
            session.getTransaction().commit();
        }
    }

    private static void truncate(Session s) {
        s.doWork(connection -> {
            Statement statement = connection.createStatement();

            statement.execute("DELETE FROM Lek_SubstancjaCzynna");
            statement.execute("DELETE FROM PracownikApteki_CzasPracy");
            statement.execute("DELETE FROM PracownikApteki_Raport");
            statement.execute("DELETE FROM PracownikApteki_SprzedazLeku");

            statement.execute("DELETE FROM SubstancjaCzynna");
            statement.execute("DELETE FROM Lek");
            statement.execute("DELETE FROM SprzedazLeku");
            statement.execute("DELETE FROM CzasPracy");
            statement.execute("DELETE FROM PracownikApteki");
            statement.execute("DELETE FROM Raport");

            statement.close();
        });
    }
}
