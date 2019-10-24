package ec.carper.fabrica.model

import lombok.Getter
import lombok.Setter

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
import org.openxava.calculators.*
import java.time.LocalDate
import java.time.temporal.WeekFields 

@Entity
//Reporte de fábrica
class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required @Getter @Setter
    LocalDate fecha

    @Transient @Setter
    int semana
    int getSemana(){
        Object date = getView().getValue("fecha")
        LocalDate localDate = (LocalDate) fecha
        WeekFields weekFields = WeekFields.of(fecha)
        //int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return fecha.get(weekFields.weekOfWeekBasedYear())
    }

}

