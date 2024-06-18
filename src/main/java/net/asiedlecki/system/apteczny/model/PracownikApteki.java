package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.asiedlecki.system.apteczny.model.enumy.TypPracownikaEnum;
import net.asiedlecki.system.apteczny.model.enumy.WyksztalcenieEnum;

import java.util.EnumSet;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PracownikApteki {

    public static WyksztalcenieEnum MINIMALNE_WYKSZTALCENIE = WyksztalcenieEnum.SREDNIE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imie;
    private String nazwisko;
    private EnumSet<TypPracownikaEnum> typPracownikaEnum;
    private Integer lataPracy; // dotyczy farmaceuty
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CzasPracy> czasyPracy;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Raport> utworzoneRaporty; // dotyczy kierownika
    @ManyToMany(fetch = FetchType.EAGER)
    private List<SprzedazLeku> sprzedaze; // dotyczy farmaceuty
}
