
package interfaz.Encuesta;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.FacadeCurso;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RegistrarEncuesta implements Serializable{
    
    @EJB
    private FacadeEncuesta fEnc;
    @EJB
    private FacadeCurso fCurso;
    
    List<Pregunta> preguntas;
    
    private Map<Integer, Boolean> listChecked;
    private List<String> TiposPregunta;
    
    //  Getters
    public List<Pregunta> getPreguntas() {return preguntas;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public List<String> getTiposPregunta() {return TiposPregunta;}
    
    //  Setters
    public void setPreguntas(List<Pregunta> preguntas) {this.preguntas = preguntas;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setTiposPregunta(List<String> TiposPregunta) {this.TiposPregunta = TiposPregunta;}
    
    @PostConstruct
    public void Init(){
        preguntas = new ArrayList<>();
        preguntas = fEnc.ListarPreguntas();
        
        listChecked = new HashMap<>();
        for (Pregunta item : preguntas) {
            listChecked.put(item.getIdPregunta(), Boolean.FALSE);
        }
        TiposPregunta = new ArrayList<>();
        for (int i = 0; i < EnumTipoPregunta.values().length; i++) {
            TiposPregunta.add(EnumTipoPregunta.values()[i].toString());
        }
    }
    
    public void crearEncuesta() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        List<Curso> CursosActuales = fCurso.GetCursosActuales();
        int idEncuesta = 0;
        List<Integer> preguntasSeleccionadas = getPreguntasSeleccionadas();
        if(preguntasSeleccionadas.isEmpty()){
            FacesMessage msj = new FacesMessage("No hay preguntas seleccionadas","No hay preguntas seleccionadas");
            context.addMessage("frmRegEncuesta:btnRegEncuesta", msj);
            context.renderResponse();
        }else{
            if(!CursosActuales.isEmpty()){
                for(Curso curso: CursosActuales){
                    if((idEncuesta = fEnc.CrearEncuesta(curso.getIdCurso()))!=-1){
                        fEnc.AgregarPreguntasEncuesta(idEncuesta, preguntasSeleccionadas);
                        context.getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/Encuesta/ListarEncuesta.xhtml?rol=Administrador");
                        context.responseComplete();
                    }else{
                        FacesMessage msj = new FacesMessage("No se pudo crear la encuesta","No se pudo crear la encuesta");
                        context.addMessage("frmRegEncuesta:btnRegEncuesta", msj);
                        context.renderResponse();
                    }
                }
            }else{
                FacesMessage msj = new FacesMessage("No hay cursos en el semestre sin encuestas","No hay cursos en el semestre sin encuestas");
                context.addMessage("frmRegEncuesta:btnRegEncuesta", msj);
                context.renderResponse();
            }
        }
    }
    
    private List<Integer> getPreguntasSeleccionadas(){
        List<Integer> idsPreguntas = new ArrayList<>();
        for(Map.Entry item: listChecked.entrySet()){
            if((Boolean)item.getValue()== true){
                idsPreguntas.add((int)item.getKey());
            }
        }
        return idsPreguntas;
    }
    public void registrarPregunta(String tipoPregunta, String contenidoPregunta){
        fEnc.CrearPregunta(contenidoPregunta, EnumTipoPregunta.valueOf(tipoPregunta));
    }
    
}

