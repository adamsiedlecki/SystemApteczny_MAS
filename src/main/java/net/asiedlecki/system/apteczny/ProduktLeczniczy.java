package net.asiedlecki.system.apteczny;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.asiedlecki.system.apteczny.db.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProduktLeczniczy {

    private static final List<ProduktLeczniczy> REJESTR_PRODUKTOW_LECZNICZYCH = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gtin;
    @ManyToMany
    private List<SubstancjaCzynna> substancjeCzynne;

    public ProduktLeczniczy(String gtin, List<SubstancjaCzynna> substancjeCzynne) {
        this.gtin = gtin;
        this.substancjeCzynne = substancjeCzynne;
        if (gtin == null || REJESTR_PRODUKTOW_LECZNICZYCH.stream().anyMatch(p -> p.gtin.equals(gtin))) {
            throw new IllegalArgumentException("Lek o podanym GTIN istnieje juz w rejestrze!");
        }
        REJESTR_PRODUKTOW_LECZNICZYCH.add(this);
    }

    public boolean czyProduktNarkotyczny() {
        return substancjeCzynne.stream()
                .anyMatch(s -> s.getNazwaPolska().contains("psylocybina"));
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczePrzetworzoneWApteceWOstatnimCzasie() {
        return Collections.unmodifiableList(REJESTR_PRODUKTOW_LECZNICZYCH);
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczePrzetworzoneWAptece() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<ProduktLeczniczy> q = session.createQuery("From ProduktLeczniczy ", ProduktLeczniczy.class);
            return q.getResultList();
        }
    }
}
