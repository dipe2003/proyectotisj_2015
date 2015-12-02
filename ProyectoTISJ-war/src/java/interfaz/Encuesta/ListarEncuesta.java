
package interfaz.Encuesta;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.Estudiante;
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
    
    @EJB
    private FacadeCurso fCurso;
    
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
        List<Curso> CursosActuales = fCurso.GetCursosActuales();
        List<Integer> preguntasSeleccionadas = getIdPreguntasEncuesta();
        for(Curso curso: CursosActuales){
            int idEncuesta;
            if((idEncuesta = fEnc.CrearEncuesta(curso.getIdCurso()))!=-1){
                fEnc.AgregarPreguntasEncuesta(idEncuesta, preguntasSeleccionadas);
            }
        }
    }
    
    private Encuesta getEncuestaSeleccionada(){
        int idEncuesta = 0;
        for(Map.Entry item: listChecked.entrySet()){
            if((Boolean)item.getValue()== true){
                idEncuesta = (int)item.getKey();
            }
        }
        for(Encuesta item : encuestas){
            if (item.getIdEncuesta()==idEncuesta) return item;
        }
        return null;
    }
    
    private List<Integer> getIdPreguntasEncuesta(){
        List<Integer> idPreguntas = new ArrayList<>();
        Encuesta encuestaSeleccionada = getEncuestaSeleccionada();
        List <Pregunta> preguntas = encuestaSeleccionada.getPreguntasEncuesta();
        for (Pregunta item : preguntas){
            idPreguntas.add(item.getIdPregunta());
        }
        return idPreguntas;
    }
    
    public String getEstudiantesSinRespuesta(Encuesta encuesta){
        List<Estudiante> estudiantesSinRespuesta = fEnc.getEstudianteSinRespuesta(encuesta.getIdEncuesta());
        String nombreEstudiantes = "";
        if (estudiantesSinRespuesta.size()>0){
            for (Estudiante item : estudiantesSinRespuesta){
                nombreEstudiantes += item.getNombreCompleto() + " - ";
            }
            return nombreEstudiantes;
        }
        return "todos los estudiantes han respondido la encuesta";
    }
}