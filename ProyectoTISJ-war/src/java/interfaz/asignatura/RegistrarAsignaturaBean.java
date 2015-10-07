
package interfaz.asignatura;

import Asignatura.FacadeAsignatura;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegistrarAsignaturaBean implements Serializable{
    
    private String NombreAsignatura;
    private int CreditosAsignatura;
    
    @EJB
    private FacadeAsignatura fAsig;
    
    public RegistrarAsignaturaBean() {}
    
    //  Setters
    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}
    
    //  Getters
    public int getCreditosAsignatura() {return CreditosAsignatura;}
    public String getNombreAsignatura() {return NombreAsignatura;}
    
    
    /**
     * Registra la asignatura con los datos ingresados.
     * @return 
     */
    public String registrarAsignatura(){
        if (fAsig.RegistrarAsignatura(NombreAsignatura, CreditosAsignatura)!=-1) {
            return "registrado";
        }
        return "";
    }    
}

