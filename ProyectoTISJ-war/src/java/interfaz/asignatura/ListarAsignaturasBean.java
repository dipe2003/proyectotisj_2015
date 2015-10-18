
package interfaz.asignatura;

import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarAsignaturasBean implements Serializable{
    @EJB
    private FacadeAsignatura fAsig;
    
    private List<Asignatura> Asignaturas;
    private Asignatura AsignaturaSeleccionada;
    private String Opt;
    
    //  Constructores    
    public ListarAsignaturasBean(){}
    
     //  Getters
    public List<Asignatura> getAsignaturas() {return Asignaturas;}
    public Asignatura getAsignaturaSeleccionada() {return AsignaturaSeleccionada;}
    public String getOpt(){return this.Opt;}
    
    //  Setters
    public void setAsignaturas(List<Asignatura> Asignaturas) {this.Asignaturas = Asignaturas;}
    public void setAsignaturaSeleccionada(Asignatura AsignaturaSeleccionada) {this.AsignaturaSeleccionada = AsignaturaSeleccionada;}
    public void setOpt(String Opt){this.Opt = Opt;}    
    
    @PostConstruct
    public void Init(){
        this.Asignaturas = fAsig.ListarAsignaturas();
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            this.Opt = request.getParameter("opt");
        }catch(NullPointerException ex){}
        
        if (this.Opt==null) {
            this.Opt = "no";
        }
    }
    
    
}
