
package interfaz.Encuesta;

import Asignatura.Asignatura;
import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.FacadeCurso;
import Asignatura.FacadeAsignatura;
import Usuario.Estudiante.Estudiante;
import Usuario.Usuario;
import interfaz.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarEncuesta implements Serializable{
    
    @EJB
    private FacadeEncuesta fEnc;
    
    @EJB
    private FacadeCurso fCurso;
    
    @EJB
    private FacadeAsignatura fAsig;
    
    @Inject
    private Login login;
    
    List<Encuesta> encuestas;
    
    private Map<Integer, Boolean> listChecked;
    
    private List<String> AniosCursos;
    
    private Map<String, Integer> AsignaturasCursos;
    
    private String Rol;
    
    private String NumeroSemestre;
    
    // getters
    public String getRol() {return Rol;}
    public List<Encuesta> getEncuestas() {return encuestas;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public List<String> getAniosCursos() {return AniosCursos;}
    public Map<String, Integer> getAsignaturasCursos() {return AsignaturasCursos;}
    public String getNumeroSemestre() {return NumeroSemestre;}
    // setters
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setEncuestas(List<Encuesta> encuestas) {this.encuestas = encuestas;}
    public void setRol(String Rol) {this.Rol = Rol;}
    public void setAniosCursos(List<String> AniosCursos) {this.AniosCursos = AniosCursos;}
    public void setAsignaturasCursos(Map<String, Integer> AsignaturasCursos) {this.AsignaturasCursos = AsignaturasCursos;}
    public void setNumeroSemestre(String NumeroSemestre) {this.NumeroSemestre = NumeroSemestre;}
    
    private int idEstudiante;
    
    @PostConstruct
    public void Init(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Usuario usr = (Usuario) request.getSession().getAttribute("Usuario");
            idEstudiante = usr.getIdUsuario();
            Rol = request.getParameter("rol");
            if ((login.getRolSeleccionado().equalsIgnoreCase("Estudiante"))&& ((this.Rol.equalsIgnoreCase("Administrador"))||(this.Rol.equalsIgnoreCase("Administrativo")))){
                context.getExternalContext().redirect("./../Error/Error401.xhtml");
            }
            encuestas = new ArrayList<>();
            if ((Rol.equalsIgnoreCase("Administrador"))||(Rol.equalsIgnoreCase("Administrativo"))){
                encuestas = fEnc.ListarEncuestas();
            }else if (Rol.equalsIgnoreCase("Estudiante")){
                encuestas = fEnc.getEncuestasEstudianteSinResponder(usr.getIdUsuario());
            }else if (Rol.equalsIgnoreCase("Docente")){
                
            }
            
            listChecked = new HashMap<>();
            for (Encuesta item : encuestas) {
                listChecked.put(item.getIdEncuesta(), Boolean.FALSE);
            }
            this.AniosCursos = new ArrayList<>();
            this.AniosCursos = fCurso.getAniosCursos();
            this.AniosCursos.add("Todos");
            
            AsignaturasCursos = new HashMap<>();
            AsignaturasCursos.put("Todos",0);
            List<Asignatura> ListAsignaturaCurso =  fAsig.ListarAsignaturasCurso();
            for (int i = 0; i < ListAsignaturaCurso.size(); i++) {
                AsignaturasCursos.put(ListAsignaturaCurso.get(i).getNombreAsignatura(),ListAsignaturaCurso.get(i).getIdAsignatura());
            }
        } catch (IOException ex) {
            Logger.getLogger(ListarEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importarEncuesta() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        List<Curso> CursosActuales = fCurso.GetCursosActuales(Integer.valueOf(NumeroSemestre));
        List<Integer> preguntasSeleccionadas = getIdPreguntasEncuesta();
        if(!CursosActuales.isEmpty()){
            for(Curso curso: CursosActuales){
                int idEncuesta;
                if((idEncuesta = fEnc.CrearEncuesta(curso.getIdCurso()))!=-1){
                    fEnc.AgregarPreguntasEncuesta(idEncuesta, preguntasSeleccionadas);
                }
            }
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath()+"/Encuesta/ListarEncuesta.xhtml?rol=Administrador");
            context.responseComplete();
        }else{
            FacesMessage msj = new FacesMessage("No se pudo crear la encuesta, no hay cursos en el semestre seleccionado sin encuestas",
                    "No se pudo crear la encuesta, no hay cursos en el semestre seleccionado sin encuestas");
            context.addMessage("formulario:btnImpEncuesta", msj);
            context.renderResponse();
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
    
    public void registrarResultadoEncuesta(String test, String idEncuesta) throws IOException{
        String[] RespuestaPregunta = test.split(",");
        int Encuesta = Integer.valueOf(idEncuesta);
        for (int i = 0; i < RespuestaPregunta.length; i++) {
            int Respuesta = Integer.valueOf(RespuestaPregunta[i].split("-")[0]);
            int Pregunta = Integer.valueOf(RespuestaPregunta[i].split("-")[1]);
            fEnc.ResponderPreguntaEncuesta(Respuesta, Pregunta, Encuesta);
        }
        fEnc.AregarEstudianteEncuesta(idEstudiante, Encuesta);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("ListarEncuesta.xhtml?rol=Estudiante");
    }
    
    public void filtro( String anioFilter, int semestreFilter, int idAsignatura){
        int anio = 0;
        if (!anioFilter.equalsIgnoreCase("Todos")){
            anio = Integer.valueOf(anioFilter);
        }
        encuestas =  fEnc.filtrarEncuestas(anio, semestreFilter, idAsignatura);
    }
    
}