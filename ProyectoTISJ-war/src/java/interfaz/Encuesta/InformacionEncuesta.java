
package interfaz.Encuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.RespuestaEncuesta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class InformacionEncuesta implements Serializable{
    
    @EJB
    private FacadeEncuesta fEnc;
    
    private List<Encuesta> Encuestas;
    
    private int Anio;
    
    private int Semestre;
    
    private List<String> TextoPreguntas;
    
    public int getAnio() {return Anio;}
    public int getSemestre() {return Semestre;}
    public List<Encuesta> getEncuestas() {return Encuestas;}
    public List<String> getTextoPreguntas() {return TextoPreguntas;}
    
    public void setAnio(int Anio) {this.Anio = Anio;}
    public void setSemestre(int Semestre) {this.Semestre = Semestre;}
    public void setEncuestas(List<Encuesta> Encuestas) {this.Encuestas = Encuestas;}
      public void setTextoPreguntas(List<String> TextoPreguntas) {this.TextoPreguntas = TextoPreguntas;}
    
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        int idEncuesta = Integer.parseInt((String) request.getParameter("id"));
        Encuesta EncuestaSeleccionada = fEnc.BuscarEncuesta(idEncuesta);
        
        TextoPreguntas = new ArrayList<>();
        
        for (RespuestaEncuesta item : EncuestaSeleccionada.getRespuestasEncuesta()){
            TextoPreguntas.add(item.getPreguntaRespuestasEncuesta().getTextoPregunta());
        }
        
        Anio = EncuestaSeleccionada.getCursoEncuesta().getAnioCurso();
        Semestre = EncuestaSeleccionada.getCursoEncuesta().getSemestreCurso();
        
        Encuestas = fEnc.getEncuestaPorAnioYSemestre(Anio,Semestre);
        
    }
}
