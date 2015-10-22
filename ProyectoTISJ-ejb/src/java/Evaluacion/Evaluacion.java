
package Evaluacion;

import Curso.Curso;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEvaluacion;
    @Temporal(TemporalType.DATE)
    private Date FechaEvaluacion;
    
    @ManyToOne
    private Curso CursoEvaluacion;
    
    //  Constructores
    public Evaluacion() {}
    public Evaluacion(Date FechaEvaluacion, Curso CursoEvaluacion) {
        this.FechaEvaluacion = FechaEvaluacion;
        this.CursoEvaluacion = CursoEvaluacion;
    }
    
    //  Getters
    public int getIdEvaluacion() {return IdEvaluacion;}
    public Date getFechaEvaluacion() {return FechaEvaluacion;}
    public Curso getCursoEvaluacion() {return CursoEvaluacion;}
    
    //  Setters
    public void setIdEvaluacion(int IdEvaluacion) {this.IdEvaluacion = IdEvaluacion;}
    public void setFechaEvaluacion(Date FechaEvaluacion) {this.FechaEvaluacion = FechaEvaluacion;}
    public void setCursoEvaluacion(Curso CursoEvaluacion) {
        this.CursoEvaluacion = CursoEvaluacion;
        if (!CursoEvaluacion.getEvaluacionesCurso().contains(this)) {
            CursoEvaluacion.getEvaluacionesCurso().add(this);
        }
    }
    

}
