package ec.carper.fabrica.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

import java.util.Locale;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.openxava.actions.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;

@Entity
@View(members="fecha,numeroSemana")
//Reporte de fábrica
public class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required @Getter @Setter
    private LocalDate fecha;

    @Getter @Setter
    private int semana;

    /**
     * https://sourceforge.net/p/openxava/discussion/437013/thread/83b3114767/?limit=25
     * https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8
     *
     */
    @Depends("fecha") 
    public int getNumeroSemana(){
        LocalDate localDate = (LocalDate) fecha;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());           
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber);
        return weekNumber;
    }

    public void recalculateSemana(){
        setSemana(getNumeroSemana());
    }

    @PrePersist //Al grabar la primera vez
    private void onPersist(){
        recalculateSemana();
    }

    @PreUpdate //Cada vez que se modifica
    private void onUpdate(){
        recalculateSemana();
    }

    @PreRemove //Al borrar el registro
    private void onRemove(){
        if (isRemoving()) return; //Añadimos esta línea para evitar excepciones
        recalculateSemana();
    }

    @Transient //No se almacena en la tabla en la base de datos
    private boolean removing = false; //Indica si JPA está borrando el documento ahora

    boolean isRemoving(){ //Acceso paquete, no es accesible desde fuera
        return removing;
    }

    @PreRemove //Cuando el registro va a ser borrado marcamos removing como true
    private void markRemoving(){ 
        this.removing = true;
    }

    @PostRemove //Cuando el registro ha sido borrado marcamos removing como false
    private void unmarkRemoving(){
        this.removing = false;
    }
}

