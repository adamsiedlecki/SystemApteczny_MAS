package net.asiedlecki.system.apteczny.dokumenty;

import lombok.Getter;
import net.asiedlecki.system.apteczny.ProduktLeczniczy;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

public class Recepta extends Dokument {

    public static final Period MAKSYMALNY_CZAS_KURACJI = Period.ofDays(360);

    @Getter
    private final ProduktLeczniczy produktLeczniczy;
    private final LocalDateTime dataRealizacjiOd;
    private final LocalDateTime dataRealizacjiDo;

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy, LocalDateTime dataRealizacjiDo, LocalDateTime dataRealizacjiOd) {
        super(idDokumentu);
        this.produktLeczniczy = produktLeczniczy;
        this.dataRealizacjiDo = dataRealizacjiDo;
        this.dataRealizacjiOd = dataRealizacjiOd;
    }

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy, LocalDateTime dataRealizacjiDo) {
        this(idDokumentu, produktLeczniczy, dataRealizacjiDo, null);
    }

    public Optional<LocalDateTime> getDataRealizacjiOd() {
        return Optional.ofNullable(dataRealizacjiOd);
    }

    public boolean czyReceptaJestPrzeterminowana() {
        return LocalDateTime.now().isAfter(dataRealizacjiDo);
    }

    @Override
    public String utworzOpis() {
        return "Recepta o id: " + getIdDokumentu();
    }
}
