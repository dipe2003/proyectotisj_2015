
package interfaz.Encuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ResultadoEncuesta implements Serializable{
    
    @EJB
    private FacadeEncuesta fEnc;
    
    private Encuesta EncuestaSeleccionada;
    
    //  Getters
    public Encuesta getEncuestaSeleccionada() {return EncuestaSeleccionada;}
    
    
    //  Setters
    public void setEncuestaSeleccionada(Encuesta EncuestaSeleccionada) {this.EncuestaSeleccionada = EncuestaSeleccionada;}
    public String[] getTextoPreguntasDocente(){
        Map<String, Float> res = EncuestaSeleccionada.getResultadosPreguntasDocente();
        String[] texto = new String[res.size()];
        for(int i=0; i < res.size(); i++){
            texto[i] = (String)res.keySet().toArray()[i];
        }
        return texto;
    }
    public float[] getPromediosPreguntasDocente(){
        Map<String, Float> res = EncuestaSeleccionada.getResultadosPreguntasDocente();
        float[] promedio = new float[res.size()];
        for(int i=0; i < res.size(); i++){
            promedio[i] = (float)res.keySet().toArray()[i];
        }
        return promedio;
    }
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            int IdEncuesta = (int) ((HttpServletRequest) context.getExternalContext().getRequest()).getAttribute("idEncuesta");
            EncuestaSeleccionada = fEnc.GetEncuestaPorId(IdEncuesta);
        }catch(NullPointerException | NumberFormatException ex){}
    }
    
    
}