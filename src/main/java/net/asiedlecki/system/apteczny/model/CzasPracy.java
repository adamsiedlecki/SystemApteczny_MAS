package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;
import net.asiedlecki.system.apteczny.model.enumy.StatusGodzinPracyEnum;

import java.io.Serializable;
import java.time.YearMonth;

@Entity
@NoArgsConstructor
@Getter
@Setter
@IdClass(CzasPracy.KluczCzasuPracy.class)
public class CzasPracy {
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
}


