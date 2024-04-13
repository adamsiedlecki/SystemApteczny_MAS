package net.asiedlecki.system.apteczny.serwisy;

import groovy.util.logging.Slf4j;
import net.asiedlecki.system.apteczny.dokumenty.Recepta;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RealizacjaReceptyService {

    public boolean zrealizuj(Recepta recepta) {
        if (recepta.getDataRealizacjiOd().isPresent()) {
            if (LocalDateTime.now().isBefore(recepta.getDataRealizacjiOd().get())) {
                System.out.println("Nie wolno realizowac recepty przed podana data!");
                return false;
            }
        }
        return true;
    }
}
