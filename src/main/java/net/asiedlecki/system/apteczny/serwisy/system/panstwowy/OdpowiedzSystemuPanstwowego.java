package net.asiedlecki.system.apteczny.serwisy.system.panstwowy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OdpowiedzSystemuPanstwowego {
    private boolean czyPozytywna;
    private String wyjasnienie;
}
