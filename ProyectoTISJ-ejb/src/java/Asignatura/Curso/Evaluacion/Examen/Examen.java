
package Asignatura.Curso.Evaluacion.Examen;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Examen extends Evaluacion {
    private int ResultadoExamen;

    //  Constructores
    public Examen() {}
    public Examen(Date FechaEvaluacion, Curso CursoExamen) {
        super(FechaEvaluacion, CursoExamen);
    }

    //  Getters
    public int getResultadoExamen() {return ResultadoExamen;}

    //Setters
    public void setResultadoExamen(int ResultadoExamen) {this.ResultadoExamen = ResultadoExamen;}
    
}
