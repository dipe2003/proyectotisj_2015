
package Encuesta;

import Pregunta.Pregunta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Encuesta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEncuesta;
    @Temporal(TemporalType.DATE)
    private Date FechaEncuesta;
    private int IdAsignatura;
    
    @OneToMany
    private List<Pregunta> PreguntasEncuesta;

    public Encuesta() {
    }

    public Encuesta(Date FechaEncuesta, int IdAsignatura) {
        this.FechaEncuesta = FechaEncuesta;
        this.IdAsignatura = IdAsignatura;
    }

    public int getIdEncuesta() {
        return IdEncuesta;
    }

    public void setIdEncuesta(int IdEncuesta) {
        this.IdEncuesta = IdEncuesta;
    }

    public Date getFechaEncuesta() {
        return FechaEncuesta;
    }

    public void setFechaEncuesta(Date FechaEncuesta) {
        this.FechaEncuesta = FechaEncuesta;
    }

    public int getIdAsignatura() {
        return IdAsignatura;
    }

    public void setIdAsignatura(int IdAsignatura) {
        this.IdAsignatura = IdAsignatura;
    }

    public List<Pregunta> getPreguntas() {
        return PreguntasEncuesta;
    }

    public void setPreguntas(List<Pregunta> PreguntasEncuesta) {
        this.PreguntasEncuesta = PreguntasEncuesta;
    }
    
    public void addPreguntasEncuesta(Pregunta pregunta){PreguntasEncuesta.add(pregunta);}
    
    public void removePreguntasEncuesta(Pregunta pregunta){PreguntasEncuesta.remove(pregunta);}
    
}
