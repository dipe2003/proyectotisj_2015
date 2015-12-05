
package interfaz.Encuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.FacadeCurso;
import java.io.Serializable;
import java.util.HashMap;
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
    
    @EJB
    private FacadeCurso fCur;
    
    private int IdEncuesta;
    
    private Encuesta EncuestaSeleccionada;
    private Map<String, Float> MapResultadoDocente;
    private Map<String, Float> MapResultadoCurso;
    private Map<Integer, String> MapTextoPreguntasDocente;
    private Map<Integer, String> MapTextoPreguntasCurso;
    
    //  Getters
    public Encuesta getEncuestaSeleccionada() {return EncuestaSeleccionada;}
    public int getIdEncuesta() {return IdEncuesta;}
    public Map<Integer, String> getMapTextoPreguntasDocente(){return this.MapTextoPreguntasDocente;}
    public Map<Integer, String> getMapTextoPreguntasCurso(){return this.MapTextoPreguntasCurso;}
    public Map<String, Float> getMapResultadoDocente() {return MapResultadoDocente;}
    public Map<String, Float> getMapResultadoCurso() {return MapResultadoCurso;}
    
    //  Setters
    public void setEncuestaSeleccionada(Encuesta EncuestaSeleccionada) {this.EncuestaSeleccionada = EncuestaSeleccionada;}
    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}
    public void setMapTextoPreguntasDocente(Map<Integer, String> TextoPreguntasDocente) {this.MapTextoPreguntasDocente = TextoPreguntasDocente;}
    public void setMapTextoPreguntasCurso(Map<Integer, String> TextoPreguntasCurso) {this.MapTextoPreguntasCurso = TextoPreguntasCurso;}
    public void setMapResultadoDocente(Map<String, Float> MapResultadoDocente) {this.MapResultadoDocente = MapResultadoDocente;}
    public void setMapResultadoCurso(Map<String, Float> MapResultadoCurso) {this.MapResultadoCurso = MapResultadoCurso;}
    
    /*
    *   PREGUNTAS SOBRE DOCENTE *
    */
    
    public String getTextoPreguntasDocente(){
        String texto = "[";
        for(int i=0; i < MapResultadoDocente.size(); i++){
            texto += "'";
            texto += (String)MapResultadoDocente.keySet().toArray()[i];
            texto += "'";
            if(i+1<MapResultadoDocente.size())texto+= ", ";
        }
        texto+= "]";
        return texto;
    }
    
    public String getTituloPreguntasDocente(){
        String texto = "[";
        for(int i=0; i < MapResultadoDocente.size(); i++){
            texto += "'";
            texto += "Pregunta "+ (i+1);
            texto += "'";
            if(i+1<MapResultadoDocente.size())texto+= ", ";
        }
        texto+= "]";
        return texto;
    }
    public String getPromediosPreguntasDocente(){
        String prom = "[";
        for(int i=0; i < MapResultadoDocente.size(); i++){
            prom +=  (float) MapResultadoDocente.values().toArray()[i];
            if(i+1<MapResultadoDocente.size())prom+=", ";
        }
        prom+="]";
        return prom;
    }
    
    /*
    *   PREGUNTAS SOBRE CURSO   *
    */
    public String getTextoPreguntasCurso(){
        String texto = "[";
        for(int i=0; i < MapResultadoCurso.size(); i++){
            texto += "'";
            texto += (String)MapResultadoCurso.keySet().toArray()[i];
            texto += "'";
            if(i+1<MapResultadoCurso.size())texto+= ", ";
        }
        texto+= "]";
        return texto;
    }
    public String getTituloPreguntasCurso(){
        String texto = "[";
        for(int i=0; i < MapResultadoCurso.size(); i++){
            texto += "'";
            texto += "Pregunta "+ (i+1);
            texto += "'";
            if(i+1<MapResultadoCurso.size())texto+= ", ";
        }
        texto+= "]";
        return texto;
    }
    public String getPromediosPreguntasCurso(){
        String prom = "[";
        for(int i=0; i < MapResultadoCurso.size(); i++){
            prom +=  (float) MapResultadoCurso.values().toArray()[i];
            if(i+1<MapResultadoCurso.size())prom+=", ";
        }
        prom+="]";
        return prom;
    }
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        MapTextoPreguntasCurso = new HashMap<>();
        MapTextoPreguntasDocente = new HashMap<>();
        MapResultadoDocente = new HashMap<>();
        MapResultadoCurso = new HashMap<>();
        try{
            int idCurso = Integer.parseInt((String) request.getParameter("id"));
            if (fCur.BuscarCurso(idCurso).getEncuestaCurso() != null ){
                IdEncuesta = fCur.BuscarCurso(idCurso).getEncuestaCurso().getIdEncuesta();
                EncuestaSeleccionada = fEnc.GetEncuestaPorId(IdEncuesta);
                MapResultadoCurso = EncuestaSeleccionada.getResultadosPreguntasCurso();
                MapResultadoDocente = EncuestaSeleccionada.getResultadosPreguntasDocente();
                for(int i=0; i < MapResultadoDocente.size(); i++){
                    MapTextoPreguntasDocente.put((i+1), (String)MapResultadoDocente.keySet().toArray()[i]);
                }
                for(int i=0; i < MapResultadoCurso.size(); i++){
                    MapTextoPreguntasCurso.put((i+1), (String)MapResultadoCurso.keySet().toArray()[i]);
                }
            }
        }catch(NullPointerException | NumberFormatException ex){}
    }
    
    
}