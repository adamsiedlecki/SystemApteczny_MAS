package net.asiedlecki.system.apteczny;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubstancjaCzynna {

    private Set<JednostkaChorobowaSubstancjaCzynna> chorobyWKtorychSubstancjaJestPomocna = new HashSet<>();

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

    public void dodajChorobyWKtorychMozeBycPomocna(JednostkaChorobowaSubstancjaCzynna jednostkaChorobowaSubstancjaCzynna) {
        chorobyWKtorychSubstancjaJestPomocna.add(jednostkaChorobowaSubstancjaCzynna);
    }
}
