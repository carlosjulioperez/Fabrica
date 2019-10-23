package ec.carper.fabrica.model

import lombok.Getter
import lombok.Setter

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Unidad extends Identifiable{

    @Column(length=10) @Required @Getter @Setter
    String descripcion

}
