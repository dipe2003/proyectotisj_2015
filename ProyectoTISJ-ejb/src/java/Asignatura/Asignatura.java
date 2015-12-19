
package Asignatura;

import Asignatura.Curso.Curso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Asignatura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdAsignatura;
    private String NombreAsignatura;
    private int CreditosAsignatura;
    @OneToMany(mappedBy = "AsignaturaCurso")
    private List<Curso> CursosAsignatura;
    
    //  Constructores
    public Asignatura() {}

    public Asignatura(String NombreAsignatura, int CreditosAsignatura) {
        this.NombreAsignatura = NombreAsignatura;
        this.CreditosAsignatura = CreditosAsignatura;
        CursosAsignatura = new ArrayList<>();
    }

    //  Getters
    public int getIdAsignatura() {return IdAsignatura;}
    public String getNombreAsignatura() {return NombreAsignatura;}
    public int getCreditosAsignatura() {return CreditosAsignatura;}
    public List<Curso> getCursosAsignatura() {return CursosAsignatura;}
    
    //  Setters
    public void setIdAsignatura(int IdAsignatura) {this.IdAsignatura = IdAsignatura;}
    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}
    public void setCursosAsignatura(List<Curso> CursosAsignatura) {this.CursosAsignatura = CursosAsignatura;}
    
    public void addCursoAsignatura(Curso CursoAsignatura){
        CursosAsignatura.add(CursoAsignatura);
        if(CursoAsignatura.getAsignaturaCurso()==null || !CursoAsignatura.getAsignaturaCurso().equals(this)){
            CursoAsignatura.setAsignaturaCurso(this);
        }
    }
    
}
