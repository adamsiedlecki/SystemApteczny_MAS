package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.*;
import net.asiedlecki.system.apteczny.model.enumy.StatusGodzinPracyEnum;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass(CzasPracy.KluczCzasuPracy.class)
public class CzasPracy {

    private static List<CzasPracy> czasPracyEkstensja = new ArrayList<>();

    @Id
    private YearMonth data;
    @Id
    @ManyToOne
    private PracownikApteki pracownikApteki;
    private int iloscGodzin;
    private StatusGodzinPracyEnum statusGodzinPracyEnum;

    @NoArgsConstructor
    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class KluczCzasuPracy implements Serializable {
        private YearMonth data;
        private PracownikApteki pracownikApteki;
    }

    public static void dodajCzasPracyDoEkstensji(CzasPracy czasPracy) {
        if (!czasPracyEkstensja.contains(czasPracy)) {
            czasPracyEkstensja.add(czasPracy);
            czasPracy.getPracownikApteki().dodajCzasPracy(czasPracy);
        }
    }
}


