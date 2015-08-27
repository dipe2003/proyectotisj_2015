
package Examen;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Examen extends Evaluacion {
    private int ResultadoExamen;

    public Examen() {}

    public Examen(Date FechaEvaluacion, Curso CursoExamen) {
        super(FechaEvaluacion, CursoExamen);
    }

    public int getResultadoExamen() {return ResultadoExamen;}

    public void setResultadoExamen(int ResultadoExamen) {this.ResultadoExamen = ResultadoExamen;}
    
    
}
