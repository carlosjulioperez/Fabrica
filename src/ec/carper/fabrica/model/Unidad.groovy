package ec.carper.fabrica.model

import lombok.Getter
import lombok.Setter

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Unidad {

	@Id @Column(length=3) @ReadOnly
	@GeneratedValue(strategy=GenerationType.IDENTITY) @Getter @Setter
	short id

    @Column(length=10) @Required @Getter @Setter
    String descripcion

}
