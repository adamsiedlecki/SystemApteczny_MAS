package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.asiedlecki.system.apteczny.model.enumy.TypRaportuEnum;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataPowstania;
    private int year;
    private int month; // opcjonalnie
    private TypRaportuEnum typRaportuEnum;
    private String trescTekstowa;
}
