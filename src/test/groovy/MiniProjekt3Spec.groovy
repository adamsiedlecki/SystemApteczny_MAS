import net.asiedlecki.system.apteczny.ProduktLeczniczy
import net.asiedlecki.system.apteczny.SubstancjaCzynna
import net.asiedlecki.system.apteczny.model.dokumenty.Dokument
import net.asiedlecki.system.apteczny.model.dokumenty.RealizacjaRecepty
import net.asiedlecki.system.apteczny.model.dokumenty.Recepta
import net.asiedlecki.system.apteczny.model.magazyn.narzedzi.MaterialWykonania
import net.asiedlecki.system.apteczny.model.magazyn.narzedzi.NozDoKrojeniaTabletek
import net.asiedlecki.system.apteczny.model.magazyn.narzedzi.NozDoPizzy
import net.asiedlecki.system.apteczny.model.osoby.Pacjent
import net.asiedlecki.system.apteczny.model.osoby.PacjentBedacyPracownikiemApteki
import net.asiedlecki.system.apteczny.model.osoby.PacjentInterface
import net.asiedlecki.system.apteczny.model.osoby.PracownikAptekiInterface
import net.asiedlecki.system.apteczny.model.punkty.sprzedazy.PlacowkaApteki
import spock.lang.Specification

import java.time.LocalDateTime
// wykład: https://users.pja.edu.pl/~mtrzaska/Files/MAS/MAS-07.pdf
class MiniProjekt3Spec extends Specification {

    def "powinna być klasa abstrakcyjna / polimorfizm"() {
        given: "Dokument jest klasą abstrakcyjną"
        def idRecepty = "id-recepty"
        def lek = new ProduktLeczniczy();

        Dokument recepta = new Recepta(idRecepty, lek, LocalDateTime.now().plusMonths(1))
        Dokument realizacjaRecepty = new RealizacjaRecepty("id-realizacji", idRecepty, lek)

        when:
        String opisRecepty = recepta.utworzOpis()
        String opisRealizacji = realizacjaRecepty.utworzOpis()

        then: "polimorfizm - obiekty różnych klas mogą być traktowane jak klasa bazowa, a jednak zachowują swoje właściwości"
        opisRecepty.startsWith("Recepta o id:")
        opisRealizacji.startsWith("Realizacja o id:")
    }

    def "powinien być overlapping - przenikanie się klas"() {
        given: "pracowników można zatrundiać z różnym zakresem obowiązków, które mogą się przenikać"
        PlacowkaApteki placowkaApteki = new PlacowkaApteki(1)
        PlacowkaApteki.PracownikApteki pracownikAptekiFarmaceuta = placowkaApteki.dodajPracownika("Jan", "Kowalski")

        PlacowkaApteki.PracownikApteki pracownikAptekiSprzataczFarmaceuta = placowkaApteki.dodajPracownika("Jan", "Nowak")
        pracownikAptekiSprzataczFarmaceuta.dodajTypPracownika(PlacowkaApteki.PracownikApteki.PracownikAptekiType.SPRZATACZ)

        PlacowkaApteki.PracownikApteki pracownikAptekiSprzatacz = placowkaApteki.dodajPracownika("Jan", "Zielony")
        pracownikAptekiSprzatacz.dodajTypPracownika(PlacowkaApteki.PracownikApteki.PracownikAptekiType.SPRZATACZ)
        pracownikAptekiSprzatacz.usunTypPracownikaa(PlacowkaApteki.PracownikApteki.PracownikAptekiType.FARMACEUTA)

        ProduktLeczniczy produktLeczniczy = new ProduktLeczniczy("dasdasdsa", List.of(new SubstancjaCzynna("kofeina")))

        when:
        String porada1 = pracownikAptekiFarmaceuta.udzielPoradyFarmaceutycznej(produktLeczniczy)
        String porada2 = pracownikAptekiSprzataczFarmaceuta.udzielPoradyFarmaceutycznej(produktLeczniczy)

        String sprzatanie1 = pracownikAptekiSprzatacz.sprzatajLokal()
        String sprzatanie2 = pracownikAptekiSprzataczFarmaceuta.sprzatajLokal()

        then:
        noExceptionThrown()
        porada1 == porada2
        sprzatanie1 == sprzatanie2

        when:
        pracownikAptekiSprzatacz.udzielPoradyFarmaceutycznej(produktLeczniczy)

        then: "sprzątacz nie może udzielać porad farmaceutycznych!"
        thrown(IllegalStateException)
    }

    def "powinno być wielodziedziczenie"() {
        given: "pacjent może być pracownikiem apteki i odwrotnie"
        Pacjent pacjent = new Pacjent(1, "Jan", "Kowalski")
        PlacowkaApteki placowkaApteki = new PlacowkaApteki(1)
        PlacowkaApteki.PracownikApteki pracownik = placowkaApteki.dodajPracownika("Jan", "Kowalski")

        when:
        PacjentBedacyPracownikiemApteki pacjentBedacyPracownikiemApteki = new PacjentBedacyPracownikiemApteki(pacjent, pracownik)

        then: "można go potraktować jako pacjenta"
        pacjentBedacyPracownikiemApteki.podajNazwyChorob() != null // ponieważ:
        PacjentInterface pacjentZPracownika = pacjentBedacyPracownikiemApteki

        and: "można go traktować jak pracownika"
        pacjentBedacyPracownikiemApteki.pobierzPlacowke() != null // ponieważ:
        PracownikAptekiInterface pracownikZPacjenta = pacjentBedacyPracownikiemApteki
    }

    // wykład 7 str 46 Jedną z hierarchii
    //zastępujemy
    //kompozycją
    def "powinno być wielodziedziczenie wieloaspektowe"() {
        given: "noże dzielą się na takie profesjonalne do tabletek i takie do pizzy. Te do pizzy zużywają się 10 razy wolniej"
                "Noże dzielą się także ze względu na materiał. Każdy materiał zużywa się w innym tempie"
        NozDoKrojeniaTabletek tytanowyNozDoKrojeniaTabletek = new NozDoKrojeniaTabletek(MaterialWykonania.TYTAN) // zużywa się po 1000 użyciach

        NozDoPizzy tytanowyNozDoPizzy = new NozDoPizzy(MaterialWykonania.TYTAN)

        when:
        tytanowyNozDoKrojeniaTabletek.zarejestrujUzycie(1000)
        tytanowyNozDoPizzy.zarejestrujUzycie(1000)

        then: "ten do pizzy nie zdążył się zużyć mimo takiego samego materiału"
        tytanowyNozDoKrojeniaTabletek.czyJestZuzyty()
        !tytanowyNozDoPizzy.czyJestZuzyty()

        and: "ceramika zużywa się szybciej niż tytan"
        NozDoKrojeniaTabletek tytanowyNozDoKrojeniaTabletek2 = new NozDoKrojeniaTabletek(MaterialWykonania.TYTAN)
        NozDoKrojeniaTabletek ceramicznyNozDoKrojeniaTabletek = new NozDoKrojeniaTabletek(MaterialWykonania.CERAMIKA)

        tytanowyNozDoKrojeniaTabletek2.zarejestrujUzycie(10)
        ceramicznyNozDoKrojeniaTabletek.zarejestrujUzycie(10)

        then:
        ceramicznyNozDoKrojeniaTabletek.czyJestZuzyty()
        !tytanowyNozDoKrojeniaTabletek2.czyJestZuzyty()
    }

    def "powinno być wielodziedziczenie dynamiczne"() {

    }

}
