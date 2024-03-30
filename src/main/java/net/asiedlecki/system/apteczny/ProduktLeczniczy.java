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
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProduktLeczniczy {

    private static final List<ProduktLeczniczy> REJESTR_PRODUKTOW_LECZNICZYCH = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String gtin;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SubstancjaCzynna> substancjeCzynne;

    public ProduktLeczniczy(String gtin, List<SubstancjaCzynna> substancjeCzynne) {
        this.gtin = gtin;
        this.substancjeCzynne = substancjeCzynne;
        if (gtin == null) {
            throw new IllegalArgumentException("Lek omusi miec GTIN!");
        }
        REJESTR_PRODUKTOW_LECZNICZYCH.add(this);
        if (AptekaDao.pobierzProduktLeczniczyPoGtin(gtin) == null) {
            AptekaDao.zapiszProduktLeczniczy(this);
        }
    }

    public boolean czyProduktNarkotyczny() {
        return substancjeCzynne.stream()
                .anyMatch(s -> s.getNazwaPolska().contains("psylocybina"));
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczePrzetworzoneWApteceWOstatnimCzasie() {
        return Collections.unmodifiableList(REJESTR_PRODUKTOW_LECZNICZYCH);
    }

    public static List<ProduktLeczniczy> pobierzProduktyLeczniczeWBazieDanychApteki() {
        return AptekaDao.pobierzProduktyLeczniczeWBazieDanychApteki();
    }

    public String utworzOpis() {
        return "gtin: " + gtin + ", substacje: " + substancjeCzynne.stream().map(s -> s.getNazwaPolska()).collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {
        return utworzOpis();
    }
}
