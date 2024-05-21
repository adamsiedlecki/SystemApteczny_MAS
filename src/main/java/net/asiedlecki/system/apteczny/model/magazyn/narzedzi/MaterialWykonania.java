package net.asiedlecki.system.apteczny.model.magazyn.narzedzi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MaterialWykonania {
    TYTAN(1000),
    CERAMIKA(10),
    STAL_NIERDZEWNA(200);


    @Getter
    private final int iloscUzycPoKtorymNastepujeZuzycie;
}