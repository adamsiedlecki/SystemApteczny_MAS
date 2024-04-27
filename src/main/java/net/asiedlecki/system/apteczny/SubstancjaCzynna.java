package net.asiedlecki.system.apteczny;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubstancjaCzynna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwaPolska;

    @Transient
    private List<ProduktLeczniczy> lekiZawierajaceSubstancje = new ArrayList<>();

    public SubstancjaCzynna(String nazwaPolska) {
        this.nazwaPolska = nazwaPolska;
    }

    public void dodajInforamcjeOProdukcieLeczniczymZawierajacymSubstancje(ProduktLeczniczy produktLeczniczy) {
        lekiZawierajaceSubstancje.add(produktLeczniczy);
    }

    public List<ProduktLeczniczy> produktyLeczniczeZawierajace() {
        return lekiZawierajaceSubstancje;
    }
}
