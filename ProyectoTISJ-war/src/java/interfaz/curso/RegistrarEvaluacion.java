
package interfaz.curso;

import Asignatura.Curso.Evaluacion.FacadeEvaluacion;
import Asignatura.Curso.Evaluacion.Resultado.FacadeResultado;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class RegistrarEvaluacion implements Serializable{
    
    @EJB
    private FacadeUsuario fUsr;
    @EJB
    private FacadeEvaluacion fEva;
    @EJB
    private FacadeResultado fRes;
    
    private int idCurso;
    
    private Date FechaEvaluacion;
    private String strFechaEvaluacion;
    private Map<Integer,Integer> Resultado;
    private Map<Integer,Boolean> Asistencia;
    private Map<Integer,String> Laboratorio;
    private List<Usuario> Alumnos;
    
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
    public Map<Integer, Integer> getResultado() {return Resultado;}
    public List<Usuario> getAlumnos() {return Alumnos;}
    public Map<Integer, Boolean> getAsistencia() {return Asistencia;}
    public Map<Integer, String> getLaboratorio() {return Laboratorio;}
    
    //  Setters
    public void setFechaEvaluacion(Date FechaClase) {this.FechaEvaluacion = FechaClase;}
    public void setStrFechaEvaluacion(String strFechaClase) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaClase));
        }catch(ParseException ex){}
        this.strFechaEvaluacion = strFechaClase;
        this.FechaEvaluacion = cal.getTime();
    }
    public void setResultado(Map<Integer, Integer> Resultado) {this.Resultado = Resultado;}
    public void setAlumnos(List<Usuario> Alumnos) {this.Alumnos = Alumnos;}
    public void setAsistencia(Map<Integer, Boolean> Asistencia) {this.Asistencia = Asistencia;}
    public void setLaboratorio(Map<Integer, String> Laboratorio) {this.Laboratorio = Laboratorio;}
    
    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        idCurso = Integer.valueOf(request.getParameter("idCurso"));
        Alumnos = fUsr.listarUsuarioEstudianteCurso(idCurso);
        Resultado = new HashMap<>();
        for (Usuario Alumno : Alumnos){
            Resultado.put(Alumno.getIdUsuario(), 0);
        }
        Asistencia = new HashMap<>();
        for (Usuario Alumno : Alumnos){
            Asistencia.put(Alumno.getIdUsuario(), Boolean.TRUE);
        }
    }
    
    public void nuevaEvaluacion(String tipoEvaluacion) throws IOException{
        int IdEva = fEva.RegistrarEvaluacion(idCurso, FechaEvaluacion, tipoEvaluacion);
        if (IdEva != -1) {
            for (Usuario item: Alumnos) {
                if (Asistencia.get(item.getIdUsuario())){
                    
                    String result = String.valueOf(Resultado.get(item.getIdUsuario()));
                    int resultado = Integer.valueOf(result);
                    fRes.RegistrarResultadoEvaluacion(IdEva, item.getIdUsuario(), resultado);
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/Curso/ListarCursos.xhtml");
        }
        
    }
    
    
}

