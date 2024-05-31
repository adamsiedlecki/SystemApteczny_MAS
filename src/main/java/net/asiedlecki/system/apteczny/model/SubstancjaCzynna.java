package net.asiedlecki.system.apteczny.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SubstancjaCzynna {
    @Id
    private String nazwaPolska;
    private String opisNarkotycznej;

    @Transient
    public boolean czyNarkotyczna() {
        return opisNarkotycznej != null;
    }
}
