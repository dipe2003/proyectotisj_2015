
package Encuesta;

import Curso.Curso;
import java.io.Serializable;
import java.util.Date;
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
    
    //  Constructores
    public Encuesta() {}
    
    public Encuesta(Date FechaEncuesta) {this.FechaEncuesta = FechaEncuesta;}
    
    //  Getters
    public int getIdEncuesta() {return IdEncuesta;}
    public Date getFechaEncuesta() {return FechaEncuesta;}
    public Curso getCursoEncuesta() {return CursoEncuesta;}
    
    //  Setters
    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}
    public void setFechaEncuesta(Date FechaEncuesta) {this.FechaEncuesta = FechaEncuesta;}
    public void setCursoEncuesta(Curso CursoEncuesta) {this.CursoEncuesta = CursoEncuesta;}
    
}
