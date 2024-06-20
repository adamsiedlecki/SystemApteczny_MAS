package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.asiedlecki.system.apteczny.model.enumy.TypRaportuEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Raport {

    private static Set<Raport> raportyEkstensja = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataPowstania;
    private int year;
    private Integer month; // opcjonalnie
    private TypRaportuEnum typRaportuEnum;
    private String trescTekstowa;
    @ManyToOne
    private PracownikApteki kierownikAutor;

    private static Raport wygenerujRaportFinansowy(TypRaportuEnum typRaportuEnum, int rok, int miesiac) {
        Raport raport = new Raport();
        raport.setDataPowstania(LocalDate.now());
        raport.setYear(rok);
        raport.setMonth(miesiac);
        raport.setTypRaportuEnum(typRaportuEnum);
        return raport;
    }

    private static void dodajDoEkstensji(Raport raport) {
        if (!raportyEkstensja.contains(raport)) {
            raportyEkstensja.add(raport);
            raport.getKierownikAutor().getUtworzoneRaporty().add(raport);
        }
    }
}
