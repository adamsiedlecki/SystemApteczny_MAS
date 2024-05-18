import net.asiedlecki.system.apteczny.ProduktLeczniczy
import net.asiedlecki.system.apteczny.model.dokumenty.Dokument
import net.asiedlecki.system.apteczny.model.dokumenty.RealizacjaRecepty
import net.asiedlecki.system.apteczny.model.dokumenty.Recepta
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

    def "powinno być wielodziedziczenie wieloaspektowe"() {

    }

    def "powinno być wielodziedziczenie dynamiczne"() {

    }

}
