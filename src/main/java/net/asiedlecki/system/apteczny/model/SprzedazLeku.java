package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SprzedazLeku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idRecepty; // moze byc bez recepty
    private int iloscOpakowan;
    private BigDecimal cenaCalosci;
    @ManyToOne
    private Lek lek;
    @ManyToMany
    private List<PracownikApteki> farmaceuci;
}
