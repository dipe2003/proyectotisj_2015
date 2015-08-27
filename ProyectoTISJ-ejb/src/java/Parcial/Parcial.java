
package Parcial;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Parcial extends Evaluacion implements Serializable{
    private int ResultadoParcial;

    public Parcial(Date FechaEvaluacion, Curso CursoParcial) {
        super(FechaEvaluacion, CursoParcial);
    }

    public Parcial() {}

    public int getResultadoParcial() {return ResultadoParcial;}

    public void setResultadoParcial(int ResultadoParcial) {this.ResultadoParcial = ResultadoParcial;}
    
    
}
