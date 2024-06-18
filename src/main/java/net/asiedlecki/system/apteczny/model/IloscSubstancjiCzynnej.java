package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IloscSubstancjiCzynnej {
    @Id
    @GeneratedValue
    private Long id;
    private String jednostka;
    private BigDecimal wartosc;
    @ManyToOne
    private SubstancjaCzynna substancjaCzynna;
}
