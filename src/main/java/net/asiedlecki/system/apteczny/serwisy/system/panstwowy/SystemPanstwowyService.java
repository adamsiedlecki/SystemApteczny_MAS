package net.asiedlecki.system.apteczny.serwisy.system.panstwowy;

import org.springframework.stereotype.Service;

@Service
public class SystemPanstwowyService {

    /**
     * Implementacja mocka systemu krajowego.
     *
     * @param idRecepty
     * @return odpowiedź
     */
    public static OdpowiedzSystemuPanstwowego pobierz(String idRecepty) {
        if (idRecepty.equals("2137")) {
            return new OdpowiedzSystemuPanstwowego(false, "zabronione jest szydzenie z papieza!");
        }
        return new OdpowiedzSystemuPanstwowego(true, "");
    }
}
