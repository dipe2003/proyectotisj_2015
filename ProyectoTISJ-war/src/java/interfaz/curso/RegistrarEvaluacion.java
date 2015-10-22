
package interfaz.curso;

import Asignatura.FacadeAsignatura;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class RegistrarEvaluacion implements Serializable{
    
    @EJB
    private FacadeUsuario fUsr;
    
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
        Date fecha = new Date(strFechaClase);
        this.strFechaEvaluacion = strFechaClase;
        this.FechaEvaluacion = fecha;
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
    
    public void nuevaEvaluacion(String tipoEvaluacion){
        //fEvaluacion.registrarEvaluacion(idCurso, FechaEvaluacion, tipoEvaluacion); //para hacer
    }
}

