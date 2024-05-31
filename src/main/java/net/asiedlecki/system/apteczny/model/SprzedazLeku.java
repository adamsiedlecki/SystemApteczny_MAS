package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
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
}
