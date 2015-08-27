
package Encuesta;

import Curso.Curso;
import Pregunta.Pregunta;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Encuesta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEncuesta;
    @Temporal(TemporalType.DATE)
    private Date FechaEncuesta;
    
    @OneToOne
    private Curso CursoEncuesta;

    public Encuesta() {}

    public Encuesta(Date FechaEncuesta) {
        this.FechaEncuesta = FechaEncuesta;
    }

    public int getIdEncuesta() {return IdEncuesta;}

    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}

    public Date getFechaEncuesta() {return FechaEncuesta;}

    public void setFechaEncuesta(Date FechaEncuesta) {this.FechaEncuesta = FechaEncuesta;}

    public Curso getCursoEncuesta() {return CursoEncuesta;}

    public void setCursoEncuesta(Curso CursoEncuesta) {this.CursoEncuesta = CursoEncuesta;}
    
}
