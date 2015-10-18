
package interfaz.asignatura;

import Asignatura.FacadeAsignatura;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RegistrarAsignaturaBean implements Serializable{
    
    private String NombreAsignatura;
    private int CreditosAsignatura;
    
    @EJB
    private FacadeAsignatura fAsig;
    
    public RegistrarAsignaturaBean() {}
    
    //  Getters
    public int getCreditosAsignatura() {return CreditosAsignatura;}
    public String getNombreAsignatura() {return NombreAsignatura;}
    
    //  Setters
    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}    
    
    /**
     * Registra la asignatura con los datos ingresados. 
     * @throws java.io.IOException
     */
    public void registrarAsignatura() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if (fAsig.RegistrarAsignatura(NombreAsignatura, CreditosAsignatura)!=-1) {
               FacesContext.getCurrentInstance().getExternalContext().redirect("../Asignatura/ListarAsignaturas.xhtml");
        }
          FacesContext.getCurrentInstance().getExternalContext().redirect("../Asignatura/RegistrarAsignatura.xhtml");
    }    
}

