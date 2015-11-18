
package interfaz.Encuesta;

import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ListarEncuesta implements Serializable{
    
    @EJB
    private FacadeEncuesta fEnc;
    
    List<Pregunta> preguntas;
    
    private Map<Integer, Boolean> listChecked;
    
    public List<Pregunta> getPreguntas() {return preguntas;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    
    public void setPreguntas(List<Pregunta> preguntas) {this.preguntas = preguntas;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    
    @PostConstruct
    public void Init(){
        
        preguntas = new ArrayList<>();
        preguntas = fEnc.ListarPreguntas();
        
        listChecked = new HashMap<>();
        for (Pregunta item : preguntas) {
            listChecked.put(item.getIdPregunta(), Boolean.FALSE);
        }
        
    }
    
    public void crearEncuesta(){
        
    }
    
    public void registrarPregunta(String tipoPregunta, String contenidoPregunta){
        fEnc.CrearPregunta(contenidoPregunta, EnumTipoPregunta.valueOf(tipoPregunta));
    }
    
}

