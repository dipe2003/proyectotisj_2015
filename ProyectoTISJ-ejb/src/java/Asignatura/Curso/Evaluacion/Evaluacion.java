
package Asignatura.Curso.Evaluacion;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
abstract public class Evaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEvaluacion;
    @Temporal(TemporalType.DATE)
    private Date FechaEvaluacion;
    @ManyToOne
    private Curso CursoEvaluacion;
    @OneToMany(mappedBy = "EvaluacionResultado")
    private List<Resultado> ResultadosEvaluacion;
    
    //  Constructores
    public Evaluacion() {}
    public Evaluacion(Date FechaEvaluacion, Curso CursoEvaluacion) {
        this.FechaEvaluacion = FechaEvaluacion;
        this.CursoEvaluacion = CursoEvaluacion;
        this.ResultadosEvaluacion = new ArrayList<>();
    }
    
    //  Getters
    public int getIdEvaluacion() {return IdEvaluacion;}
    public Date getFechaEvaluacion() {return FechaEvaluacion;}
    public Curso getCursoEvaluacion() {return CursoEvaluacion;}
    public List<Resultado> getResultadosEvaluacion() {return ResultadosEvaluacion;}
    
    //  Setters
    public void setIdEvaluacion(int IdEvaluacion) {this.IdEvaluacion = IdEvaluacion;}
    public void setFechaEvaluacion(Date FechaEvaluacion) {this.FechaEvaluacion = FechaEvaluacion;}
    public void setCursoEvaluacion(Curso CursoEvaluacion) {
        this.CursoEvaluacion = CursoEvaluacion;
        if (!CursoEvaluacion.getEvaluacionesCurso().contains(this)) {
            CursoEvaluacion.getEvaluacionesCurso().add(this);
        }
    }
    public void setResultadosEvaluacion(List<Resultado> ResultadosEvaluacion) {this.ResultadosEvaluacion = ResultadosEvaluacion;}
    
    //  Resultados
    private void addResultadoEvaluacion(Resultado ResultadoEvaluacion){
        this.ResultadosEvaluacion.add(ResultadoEvaluacion);
        if(!ResultadoEvaluacion.getEvaluacionResultado().equals(this)){
            ResultadoEvaluacion.setEvaluacionResultado(this);
        }
    }

}
