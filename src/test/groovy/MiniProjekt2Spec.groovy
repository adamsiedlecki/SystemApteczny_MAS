import net.asiedlecki.system.apteczny.ProduktLeczniczy
import net.asiedlecki.system.apteczny.SubstancjaCzynna
import spock.lang.Specification

/**
 *
 w każdym przypadku: liczności
 1-* lub *-* oraz automatyczne
 tworzenie poł. zwrotnego)
 */

class MiniProjekt2Spec extends Specification {

    def "powinien mieć asocjację 'zwykłą' "() {
        given:  "wiele do wielu -" +
                " lek może mieć wiele substancji czynnych" +
                " substancja czynna może być składnikiem wielu leków"

        def psylocybina = new SubstancjaCzynna("psylocybina")
        def kofeina     = new SubstancjaCzynna("kofeina")

        ProduktLeczniczy lek = new ProduktLeczniczy("gtin", List.of(
                psylocybina,
                kofeina
        ))

        expect: "lek posiada substancje czynne"
        lek.substancjeCzynne.size() == 2 // lek posiada substancje czynne

        and: "istnieje połączenie zwrotne"
        psylocybina.produktyLeczniczeZawierajace().get(0) == lek
        kofeina.getLekiZawierajaceSubstancje().get(0) == lek
    }

    def "powinien mieć asocjację z atrybutem "() {
    }

    def "powinien mieć asocjację kwalifikowaną "() {
    }

    def "powinien mieć kompozycję"() {
    }

}
