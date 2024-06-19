package net.asiedlecki.system.apteczny.serwisy;

import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import net.asiedlecki.system.apteczny.model.IloscSubstancjiCzynnej;
import net.asiedlecki.system.apteczny.model.Lek;
import net.asiedlecki.system.apteczny.model.PracownikApteki;
import net.asiedlecki.system.apteczny.model.SubstancjaCzynna;
import net.asiedlecki.system.apteczny.model.enumy.TypPracownikaEnum;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.Statement;
import java.util.EnumSet;
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
            IloscSubstancjiCzynnej iloscParacetamol = new IloscSubstancjiCzynnej(null, "g", BigDecimal.TEN, paracetamol);
            session.persist(iloscParacetamol);

            lekApap.setIlosciSubstancjiCzynnej(List.of(iloscParacetamol));
            lekApap.setCzyWymagaRecepty(false);
            lekApap.setIloscOpakowanWmagazynie(23);

            Lek lekZaldiar  = new Lek();
            lekZaldiar .setNazwaPolska("zaldiar");
            lekZaldiar .setCena(BigDecimal.TEN);
            IloscSubstancjiCzynnej iloscParacetamol2 = new IloscSubstancjiCzynnej(null, "g", BigDecimal.TEN, paracetamol);
            IloscSubstancjiCzynnej iloscTramadol = new IloscSubstancjiCzynnej(null, "g", BigDecimal.ONE, tramadol);
            session.persist(iloscParacetamol2);
            session.persist(iloscTramadol);

            lekZaldiar .setIlosciSubstancjiCzynnej(List.of(iloscParacetamol2, iloscTramadol));
            lekZaldiar .setCzyWymagaRecepty(true);
            lekZaldiar .setIloscOpakowanWmagazynie(10);
            session.persist(lekZaldiar);

            session.persist(lekApap);


            PracownikApteki pracownikApteki1 = new PracownikApteki();
            pracownikApteki1.setImie("Jan");
            pracownikApteki1.setNazwisko("Kowalski");
            pracownikApteki1.setTypPracownikaEnum(EnumSet.of(TypPracownikaEnum.FARMACEUTA));
            session.persist(pracownikApteki1);

            PracownikApteki pracownikApteki2 = new PracownikApteki();
            pracownikApteki2.setImie("Satoshi");
            pracownikApteki2.setNazwisko("Nakamoto");
            pracownikApteki2.setTypPracownikaEnum(EnumSet.of(TypPracownikaEnum.FARMACEUTA));
            session.persist(pracownikApteki2);

            session.getTransaction().commit();
        }
    }

    private static void truncate(Session s) {
        s.doWork(connection -> {
            Statement statement = connection.createStatement();

            statement.execute("DELETE FROM Lek_IloscSubstancjiCzynnej");
            statement.execute("DELETE FROM IloscSubstancjiCzynnej");
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
