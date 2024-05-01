package net.asiedlecki.system.apteczny;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class JednostkaChorobowaSubstancjaCzynna {

    private JednostkaChorobowa jednostkaChorobowa;
    private SubstancjaCzynna substancjaCzynna;
    private DrogaPodaniaEnum drogaPodaniaEnum;

    public JednostkaChorobowaSubstancjaCzynna(JednostkaChorobowa jednostkaChorobowa, SubstancjaCzynna substancjaCzynna, DrogaPodaniaEnum drogaPodaniaEnum) {
        this.jednostkaChorobowa = jednostkaChorobowa;
        this.substancjaCzynna = substancjaCzynna;
        this.drogaPodaniaEnum = drogaPodaniaEnum;

        jednostkaChorobowa.dodajPomocnaSubstancjeCzynna(this);
        substancjaCzynna.dodajChorobyWKtorychMozeBycPomocna(this);
    }
}
