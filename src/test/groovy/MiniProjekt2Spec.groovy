import net.asiedlecki.system.apteczny.DrogaPodaniaEnum
import net.asiedlecki.system.apteczny.JednostkaChorobowa
import net.asiedlecki.system.apteczny.JednostkaChorobowaSubstancjaCzynna
import net.asiedlecki.system.apteczny.Pacjent
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
        // Substancja czynna może leczyć wiele chorób.
        // Choroba może być leczona wieloma substncjami czynnym.
        // Dodatkowym atrybutem jest "droga podania"  - może być różna w zależności od zastsowania
        given:
        def psylocybina = new SubstancjaCzynna("psylocybina")
        def kofeina     = new SubstancjaCzynna("kofeina")

        def migrena = new JednostkaChorobowa("G43", "Migrena", "Zaburzenia okresowe i napadowe")
        def zaburzeniaSnu = new JednostkaChorobowa("G47", "Zaburzenia snu", "Zaburzenia okresowe i napadowe")

        when:
        JednostkaChorobowaSubstancjaCzynna psylocybinaNaMigrene = new JednostkaChorobowaSubstancjaCzynna(migrena, psylocybina, DrogaPodaniaEnum.DOUSTNA)
        JednostkaChorobowaSubstancjaCzynna kofeinaNaMigrene = new JednostkaChorobowaSubstancjaCzynna(migrena, kofeina, DrogaPodaniaEnum.DOUSTNA)

        JednostkaChorobowaSubstancjaCzynna psylocybinaNaZaburzeniaSnu = new JednostkaChorobowaSubstancjaCzynna(zaburzeniaSnu, psylocybina, DrogaPodaniaEnum.WZIEWNA)

        then: "substancje wiedzą o swoich połączeniach z chorobami"
        psylocybina.getChorobyWKtorychSubstancjaJestPomocna() == [psylocybinaNaMigrene, psylocybinaNaZaburzeniaSnu] as Set
        kofeina.getChorobyWKtorychSubstancjaJestPomocna() == [kofeinaNaMigrene] as Set

        and: "istnieje połączenie zwrotne - choroby wiedzą o swoich połączeniach z substancjami"
        migrena.getSubstncjeKtoreSaPomocneWLeczeniu() == [psylocybinaNaMigrene, kofeinaNaMigrene] as Set
        zaburzeniaSnu.getSubstncjeKtoreSaPomocneWLeczeniu() == [psylocybinaNaZaburzeniaSnu] as Set

        and: "co najważniejsze - istnieje atrybut"
        psylocybinaNaMigrene.drogaPodaniaEnum == DrogaPodaniaEnum.DOUSTNA
    }

    def "powinien mieć asocjację kwalifikowaną "() {
        // pacjent może mieć wiele chorób. Chorobę może mieć wiele pacjentów ( * - * )
        // choroba może być jednoznacznie zidentyfikowana po kodzie ICD10 (Międzynarodowa Klasyfikacja Chorób i Problemów Zdrowotnych)
        // pacjent może byuć jednoznacznie zidentyfikowany po identyfikatorze
        given:
        Pacjent pacjentJanKowalski = new Pacjent(1, "Jan", "Kowalski")
        Pacjent pacjentSatoshiNakamoto = new Pacjent(2, "Satoshi", "Nakamoto")

        JednostkaChorobowa migrena = new JednostkaChorobowa("G43", "Migrena", "Zaburzenia okresowe i napadowe")
        JednostkaChorobowa zaburzeniaSnu = new JednostkaChorobowa("G47", "Zaburzenia snu", "Zaburzenia okresowe i napadowe")

        when:
        pacjentJanKowalski.dodajChorobe(migrena)
        pacjentJanKowalski.dodajChorobe(zaburzeniaSnu)

        pacjentSatoshiNakamoto.dodajChorobe(zaburzeniaSnu)

        then:
        pacjentJanKowalski.podajNazwyChorob() == "Migrena, Zaburzenia snu"
        pacjentSatoshiNakamoto.podajNazwyChorob() == "Zaburzenia snu"

        and: "istnieje połączenie zwrotne"
        migrena.pobierzPacjentowCierpiacychNaChorobe() == [pacjentJanKowalski] as Set
        zaburzeniaSnu.pobierzPacjentowCierpiacychNaChorobe() == [pacjentJanKowalski, pacjentSatoshiNakamoto] as Set

    }

    def "powinien mieć kompozycję"() {
    }

}
