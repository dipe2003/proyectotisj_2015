
package Laboratorio;

import Evaluacion.Evaluacion;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Laboratorio extends Evaluacion{
    private EnumResultadoLab ResultadoLaboratorio;

    public Laboratorio() {
    }

    public Laboratorio(EnumResultadoLab ResultadoLaboratorio, Date FechaEvaluacion, int IdAsignatura) {
        super(FechaEvaluacion, IdAsignatura);
        this.ResultadoLaboratorio = ResultadoLaboratorio;
    }

    public EnumResultadoLab getResultadoLaboratorio() {
        return ResultadoLaboratorio;
    }

    public void setResultadoLaboratorio(EnumResultadoLab ResultadoLaboratorio) {
        this.ResultadoLaboratorio = ResultadoLaboratorio;
    }
    
    
}
