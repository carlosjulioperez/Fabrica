package ec.carper.fabrica.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.openxava.actions.*;
import org.openxava.annotations.*;
import org.openxava.model.*;
import org.openxava.calculators.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

@Entity
//Reporte de fábrica
public class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required @Getter @Setter
    private LocalDate fecha;

    @Transient @Setter
    @Depends("fecha")
    private int semana;
    public int getSemana(){
        Object date = getView().getValue("fecha");
        LocalDate localDate = (LocalDate) date;
        WeekFields weekFields = WeekFields.of(date);
        //int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return fecha.get(weekFields.weekOfWeekBasedYear());
    }

}

