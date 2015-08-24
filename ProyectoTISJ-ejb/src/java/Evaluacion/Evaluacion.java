
package Evaluacion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEvaluacion;
    @Temporal(TemporalType.DATE)
    private Date FechaEvaluacion;
    private int IdAsignatura;

    public Evaluacion() {
    }

    public Evaluacion(Date FechaEvaluacion, int IdAsignatura) {
        this.FechaEvaluacion = FechaEvaluacion;
        this.IdAsignatura = IdAsignatura;
    }

    public int getIdEvaluacion() {
        return IdEvaluacion;
    }

    public void setIdEvaluacion(int IdEvaluacion) {
        this.IdEvaluacion = IdEvaluacion;
    }

    public Date getFechaEvaluacion() {
        return FechaEvaluacion;
    }

    public void setFechaEvaluacion(Date FechaEvaluacion) {
        this.FechaEvaluacion = FechaEvaluacion;
    }

    public int getIdAsignatura() {
        return IdAsignatura;
    }

    public void setIdAsignatura(int IdAsignatura) {
        this.IdAsignatura = IdAsignatura;
    }
    
    
}
