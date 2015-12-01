
package interfaz.Encuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
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
    
    List<Encuesta> encuestas;
    
    private Map<Integer, Boolean> listChecked;

    public List<Encuesta> getEncuestas() {return encuestas;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setEncuestas(List<Encuesta> encuestas) {this.encuestas = encuestas;}
    
    @PostConstruct
    public void Init(){
        
        encuestas = new ArrayList<>();
        encuestas = fEnc.ListarEncuestas();
        
        listChecked = new HashMap<>();
        for (Encuesta item : encuestas) {
            listChecked.put(item.getIdEncuesta(), Boolean.FALSE);
        }
        
    }
    
    public void importarEncuesta(){
        
    }
    
}