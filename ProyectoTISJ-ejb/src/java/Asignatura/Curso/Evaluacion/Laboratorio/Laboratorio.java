
package Asignatura.Curso.Evaluacion.Laboratorio;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Evaluacion;
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
