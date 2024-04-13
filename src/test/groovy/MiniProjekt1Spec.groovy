import net.asiedlecki.system.apteczny.AptekaDao
import net.asiedlecki.system.apteczny.dokumenty.Dokument
import net.asiedlecki.system.apteczny.ProduktLeczniczy
import net.asiedlecki.system.apteczny.dokumenty.Recepta
import net.asiedlecki.system.apteczny.SubstancjaCzynna
import net.asiedlecki.system.apteczny.serwisy.RealizacjaReceptyService
import spock.lang.Specification

import java.time.LocalDateTime

class MiniProjekt1Spec extends Specification {

    def "powinien mieć ekstensję"() {
        when:
        ProduktLeczniczy pl1 = new ProduktLeczniczy("1", List.of())
        ProduktLeczniczy pl2 = new ProduktLeczniczy("2", List.of())

        then:
        ProduktLeczniczy.pobierzProduktyLeczniczePrzetworzoneWApteceWOstatnimCzasie().size() == 2
    }

    def "powinien mieć TRWAŁĄ ekstensję"() {
        when:
        def gtin = "zupelnieNieRandomowyGtin"
        new ProduktLeczniczy(gtin, List.of())

        then:
        AptekaDao.pobierzProduktLeczniczyPoGtin(gtin) != null
    }

    def "powinien mieć atrybut złożony - recepta ma produkt leczniczy"() {
        expect:
        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy("gtin", List.of())
        Recepta recepta = new Recepta("id", produktLeczniczy)
    }

    def "powinien mieć atrybut opcjonalny - recepta ma datę wydania od"() {
        when:
        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy(UUID.randomUUID().toString(), List.of())
        RealizacjaReceptyService realizacjaReceptyService = new RealizacjaReceptyService()
        Recepta receptaBezDatyRealizacjiOd = new Recepta("id", produktLeczniczy)
        Recepta receptaZDataRealizacjiOd = new Recepta("id", produktLeczniczy, LocalDateTime.of(2077, 1,1, 1, 1))

        then:
        !realizacjaReceptyService.zrealizuj(receptaZDataRealizacjiOd)
        realizacjaReceptyService.zrealizuj(receptaBezDatyRealizacjiOd)
    }

    def "powinien mieć atrybut powtarzalny - produkt leczniczy może mieć 2 substancje czynne"() {
        expect:
        SubstancjaCzynna substancjaCzynna1 = new SubstancjaCzynna("psylocybina")
        SubstancjaCzynna substancjaCzynna2 = new SubstancjaCzynna("kofeina")

        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy(UUID.randomUUID().toString(), List.of(substancjaCzynna1, substancjaCzynna2))
    }

    def "powinien mieć atrybut klasowy - wszystkie recepty mają wspólny maksymalny czas kuracji"() {
        expect:
        Recepta.MAKSYMALNY_CZAS_KURACJI != null
    }

    //TODO atrybyt pochodny

    def "powinien mieć metodę klasową - produkt leczniczy ma metodę do pobierania produktów przetworzonych w aptece"() {
        when:
        SubstancjaCzynna substancjaCzynna1 = new SubstancjaCzynna("mentol")

        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy(UUID.randomUUID().toString(), List.of(substancjaCzynna1))

        then:
        ProduktLeczniczy.pobierzOstatnieProduktyLeczniczeZSubstancja("mentol").size() != 0
    }

    def "powinien mieć przeciążenie - przesłaniany jest konstruktor w recepcie, można ją utworzyć z datą jak i bez"() {
        expect:
        Recepta recepta1 = new Recepta("id", null)

        Recepta recepta2 = new Recepta("id", null, LocalDateTime.now())
    }

    def "powinien mieć przesłonięcie - opis dokumentów"() {
        when:
        Dokument recepta = new Recepta("id", null)
        Dokument dokument = new Dokument("id")

        then:
        recepta.utworzOpis() == "Recepta o id: id"
        dokument.utworzOpis() == "Dokument o id: id"
    }

}
