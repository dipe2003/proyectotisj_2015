
package Examen;

import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Examen extends Evaluacion {
    private int ResultadoExamen;

    public Examen() {
    }

    public Examen(int ResultadoExamen, Date FechaEvaluacion, int IdAsignatura) {
        super(FechaEvaluacion, IdAsignatura);
        this.ResultadoExamen = ResultadoExamen;
    }

    public int getResultadoExamen() {
        return ResultadoExamen;
    }

    public void setResultadoExamen(int ResultadoExamen) {
        this.ResultadoExamen = ResultadoExamen;
    }
    
    
}
