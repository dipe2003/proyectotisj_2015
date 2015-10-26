
package Evaluacion.Resultado;

import Estudiante.Estudiante;
import Evaluacion.Evaluacion;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resultado implements Serializable {
    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    private int IdResultado;
    private int ResultadoEvaluacion;
    @ManyToOne
    private Estudiante EstudianteResultado;
    @ManyToOne
    private Evaluacion EvaluacionResultado;
    
    //  Constructores
    public Resultado(){}
    public Resultado(int ResultadoEvaluacion, Estudiante EstudianteResultado, Evaluacion EvaluacionResultado){
        this.ResultadoEvaluacion = ResultadoEvaluacion;
        this.EstudianteResultado = EstudianteResultado;
        this.EvaluacionResultado = EvaluacionResultado;
    }
    
    //  Getters
    public int getIdResultado() {return IdResultado;}
    public int getResultadoEvaluacion() {return ResultadoEvaluacion;}
    public Estudiante getEstudianteResultado() {return EstudianteResultado;}
    public Evaluacion getEvaluacionResultado() {return EvaluacionResultado;}
    
    //  Setters
    public void setIdResultado(int IdResultado) {this.IdResultado = IdResultado;}
    public void setResultadoEvaluacion(int ResultadoEvaluacion) {this.ResultadoEvaluacion = ResultadoEvaluacion;}
    public void setEstudianteResultado(Estudiante EstudianteResultado) {
        this.EstudianteResultado = EstudianteResultado;
        if (!EstudianteResultado.getResultadosEstudiante().contains(this)) {
            EstudianteResultado.getResultadosEstudiante().add(this);
        }
    }
    public void setEvaluacionResultado(Evaluacion EvaluacionResultado) {
        this.EvaluacionResultado = EvaluacionResultado;
        if (!EvaluacionResultado.getResultadosEvaluacion().contains(this)) {
            EvaluacionResultado.getResultadosEvaluacion().add(this);
        }
    }
    
}
