package net.asiedlecki.system.apteczny;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Dokument {

    @Getter
    private final String idDokumentu;

    public String utworzOpis() {
        return "Dokument o id: " + idDokumentu;
    }
}
