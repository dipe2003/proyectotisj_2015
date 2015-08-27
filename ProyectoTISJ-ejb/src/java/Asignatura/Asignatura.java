
package Asignatura;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Asignatura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdAsignatura;
    private String NombreAsignatura;
    private int CreditosAsignatura;
    
    public Asignatura() {}

    public Asignatura(String NombreAsignatura, int CreditosAsignatura) {
        this.NombreAsignatura = NombreAsignatura;
        this.CreditosAsignatura = CreditosAsignatura;
    }

    public int getIdAsignatura() {return IdAsignatura;}

    public void setIdAsignatura(int IdAsignatura) {this.IdAsignatura = IdAsignatura;}

    public String getNombreAsignatura() {return NombreAsignatura;}

    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}

    public int getCreditosAsignatura() {return CreditosAsignatura;}

    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}

    
}
