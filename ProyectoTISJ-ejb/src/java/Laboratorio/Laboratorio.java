
package Laboratorio;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Laboratorio extends Evaluacion{
    //  Constructores
    public Laboratorio() {}
    public Laboratorio(Date FechaEvaluacion, Curso CursoLaboratorio) {
        super(FechaEvaluacion, CursoLaboratorio);
    }    
}
