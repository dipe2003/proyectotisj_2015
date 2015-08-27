
package Laboratorio;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Laboratorio extends Evaluacion{
    private EnumResultadoLab ResultadoLaboratorio;

    public Laboratorio() {}

    public Laboratorio(Date FechaEvaluacion, Curso CursoLaboratorio) {
        super(FechaEvaluacion, CursoLaboratorio);
    }

    public EnumResultadoLab getResultadoLaboratorio() {return ResultadoLaboratorio;}

    public void setResultadoLaboratorio(EnumResultadoLab ResultadoLaboratorio) {this.ResultadoLaboratorio = ResultadoLaboratorio;}
    
    
}
