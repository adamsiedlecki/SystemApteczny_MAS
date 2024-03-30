package net.asiedlecki.system.apteczny.dokumenty;

import net.asiedlecki.system.apteczny.ProduktLeczniczy;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

public class Recepta extends Dokument {

    public static final Period MAKSYMALNY_CZAS_KURACJI = Period.ofDays(360);

    private final ProduktLeczniczy produktLeczniczy;
    private final Optional<LocalDateTime> dataRealizacjiOd;

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy, Optional<LocalDateTime> dataRealizacjiOd) {
        super(idDokumentu);
        this.produktLeczniczy = produktLeczniczy;
        this.dataRealizacjiOd = dataRealizacjiOd;
    }

    public Recepta(String idDokumentu, ProduktLeczniczy produktLeczniczy) {
        this(idDokumentu, produktLeczniczy, Optional.empty());
    }

    @Override
    public String utworzOpis() {
        return "Recepta o id: " + getIdDokumentu();
    }
}
