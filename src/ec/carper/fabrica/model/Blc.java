package ec.carper.fabrica.model;

import java.math.BigDecimal;
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
@View(members=
    "fecha,numeroSemana;" +
    "s1{" + 
    "   canaDia;" +
    "   aguaMaceracion;" +
    "   jugoDiluido;" +
    "}" +
    "tiempos {" + 
    "}" +
    "s3{" + 
    "   p4{" +
            "s3.p4.pJugoDiluido,s3.p4.brixJDil;" +
    "   }" +
    "}" 
)
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

        // Locale currentLocale = Locale.getDefault();
        // System.out.println(currentLocale.getLanguage());
        // System.out.println(currentLocale.getCountry());

        LocalDate localDate = (LocalDate) fecha;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());           
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
        //System.out.println(weekNumber);
        return weekNumber;
    }

    //Datos dia
    @Getter @Setter
    private BigDecimal canaDia;
    
    @Getter @Setter
    private BigDecimal aguaMaceracion;
    
    @Getter @Setter
    private BigDecimal jugoDiluido;

    @Getter @Setter
    private BigDecimal pJugoDiluido;

    @Getter @Setter
    private BigDecimal brixJDil;

    
    //**********************************************************************
    // Cálculos y formúlas
    //**********************************************************************
    
    public void recalculateSemana(){
        setSemana(getNumeroSemana());
    }

    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************
    
    @PrePersist //Al grabar la primera vez
    private void onPersist(){
        recalculateSemana();
    }

    @PreUpdate //Cada vez que se modifica
    private void onUpdate(){
        recalculateSemana();
    }

    /*
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
    */
}
