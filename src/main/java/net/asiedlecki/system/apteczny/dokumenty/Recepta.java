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

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy, LocalDateTime dataRealizacjiOd) {
        super(idDokumentu);
        this.produktLeczniczy = produktLeczniczy;
        this.dataRealizacjiOd = dataRealizacjiOd;
    }

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy) {
        this(idDokumentu, produktLeczniczy, null);
    }

    public Optional<LocalDateTime> getDataRealizacjiOd() {
        return Optional.ofNullable(dataRealizacjiOd);
    }

    @Override
    public String utworzOpis() {
        return "Recepta o id: " + getIdDokumentu();
    }
}
