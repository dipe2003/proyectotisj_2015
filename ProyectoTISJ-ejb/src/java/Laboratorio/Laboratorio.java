
package Laboratorio;

import Curso.Curso;
import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Laboratorio extends Evaluacion{
    private EnumResultadoLab ResultadoLaboratorio;

    //  Constructores
    public Laboratorio() {}
    public Laboratorio(Date FechaEvaluacion, Curso CursoLaboratorio) {
        super(FechaEvaluacion, CursoLaboratorio);
    }

    //  Getters
    public EnumResultadoLab getResultadoLaboratorio() {return ResultadoLaboratorio;}

    //  Setters
    public void setResultadoLaboratorio(EnumResultadoLab ResultadoLaboratorio) {this.ResultadoLaboratorio = ResultadoLaboratorio;}
    
}
