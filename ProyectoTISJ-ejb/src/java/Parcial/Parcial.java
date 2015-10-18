
package Parcial;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Parcial extends Evaluacion implements Serializable{
    private int ResultadoParcial;

    //  Constructores
    public Parcial(Date FechaEvaluacion, Curso CursoParcial) {
        super(FechaEvaluacion, CursoParcial);
    }
    public Parcial() {}

    //  Getters
    public int getResultadoParcial() {return ResultadoParcial;}

    //  Setters
    public void setResultadoParcial(int ResultadoParcial) {this.ResultadoParcial = ResultadoParcial;}
    
}
