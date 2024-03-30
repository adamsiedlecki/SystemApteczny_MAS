package net.asiedlecki.system.apteczny.dokumenty;

import net.asiedlecki.system.apteczny.ProduktLeczniczy;

public class RealizacjaRecepty extends Dokument{

    private String idDokumentuRecepty;
    private ProduktLeczniczy produktLeczniczy;

    public RealizacjaRecepty(String idDokumentu, String idDokumentuRecepty, ProduktLeczniczy produktLeczniczy) {
        super(idDokumentu);
    }
}
