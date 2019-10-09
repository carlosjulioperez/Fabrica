package ec.carper.fabrica.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
public class Unidad extends Identifiable {

    @Column(length=5) @Required @Getter @Setter
    private String descripcion;

}
