package net.asiedlecki.system.apteczny.model.dokumenty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Dokument {

    @Getter
    private final String idDokumentu;

    public abstract String utworzOpis();
}
