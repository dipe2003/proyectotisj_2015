
package Evaluacion.Resultado;

import Estudiante.Estudiante;
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
    
    //  Constructores
    public Resultado(){}
    public Resultado(int ResultadoEvaluacion, Estudiante EstudianteResultado){
        this.ResultadoEvaluacion = ResultadoEvaluacion;
        this.EstudianteResultado = EstudianteResultado;
    }
    
    //  Getters
    public int getIdResultado() {return IdResultado;}
    public int getResultadoEvaluacion() {return ResultadoEvaluacion;}
    public Estudiante getEstudianteResultado() {return EstudianteResultado;}
    
    //  Setters
    public void setIdResultado(int IdResultado) {this.IdResultado = IdResultado;}
    public void setResultadoEvaluacion(int ResultadoEvaluacion) {this.ResultadoEvaluacion = ResultadoEvaluacion;}
    public void setEstudianteResultado(Estudiante EstudianteResultado) {
        this.EstudianteResultado = EstudianteResultado;
        if (!EstudianteResultado.getResultadosEstudiante().contains(this)) {
            EstudianteResultado.getResultadosEstudiante().add(this);
        }
    }
    
}
