
package interfaz.asignatura;

import Asignatura.FacadeAsignatura;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DatosAsignaturaBean implements Serializable{
    
    private String NombreAsignatura;
    private int CreditosAsignatura;
    
    @EJB
    private FacadeAsignatura fAsig;
    
    public DatosAsignaturaBean() {}
    
    public String getNombreAsignatura() {return NombreAsignatura;}
    
    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
    
    public int getCreditosAsignatura() {return CreditosAsignatura;}
    
    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}
    
    
    public String registrarAsignatura(){
        if (fAsig.RegistrarAsignatura(NombreAsignatura, CreditosAsignatura)!=-1) {
            return "registrado";
        }
        return "";
    }
    
}

