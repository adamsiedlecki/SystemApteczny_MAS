package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Lek {
    @Id
    private String nazwaPolska;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SubstancjaCzynna> substacjeCzynne;
    private BigDecimal cena;
    private int iloscOpakowanWmagazynie;
    private boolean czyWymagaRecepty;

    @Override
    public String toString() {
        return nazwaPolska;
    }
}
