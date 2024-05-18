package net.asiedlecki.system.apteczny.serwisy;

import net.asiedlecki.system.apteczny.ProduktLeczniczy;
import net.asiedlecki.system.apteczny.SubstancjaCzynna;
import net.asiedlecki.system.apteczny.model.dokumenty.Recepta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PobieranieReceptZKrajowegoSystemuService {

    /**
     * Implementacja mocka systemu krajowego.
     *
     * @param idDokumentuRecepty
     * @return
     */
    public Recepta pobierz(String idDokumentuRecepty) {
        if (idDokumentuRecepty.equals("1")) {
            return new Recepta(idDokumentuRecepty, new ProduktLeczniczy(idDokumentuRecepty, List.of(new SubstancjaCzynna("kofeina"))), LocalDateTime.now().plusMonths(1));
        }
        return null;
    }
}
