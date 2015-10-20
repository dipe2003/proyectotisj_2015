
package interfaz.curso;

import Asignatura.FacadeAsignatura;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class RegistrarEvaluacion implements Serializable{
    
    private int idCurso;
    
    private Date FechaEvaluacion;
    private String strFechaEvaluacion;
    
    //@EJB
    //FacadeEvaluacion fEvaluacion; //para hacer
    
    public RegistrarEvaluacion() {}
    
    //  Getters
    public Date getFechaEvaluacion() {return FechaEvaluacion;}
    public String getStrFechaEvaluacion() {
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEvaluacion == null) {
            return this.strFechaEvaluacion;
        }else{
            return fDate.format(FechaEvaluacion);
        }
    }
    
    //  Setters
    public void setFechaEvaluacion(Date FechaClase) {this.FechaEvaluacion = FechaClase;}
    public void setStrFechaEvaluacion(String strFechaClase) {
        Date fecha = new Date(strFechaClase);
        this.strFechaEvaluacion = strFechaClase;
        this.FechaEvaluacion = fecha;
    }
    
    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        idCurso = Integer.valueOf(request.getParameter("idCurso"));
    }
    
    public void nuevaEvaluacion(String tipoEvaluacion){
        //fEvaluacion.registrarEvaluacion(idCurso, FechaEvaluacion, tipoEvaluacion); //para hacer
    }
}

