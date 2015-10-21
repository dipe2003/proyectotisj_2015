
package Estudiante;

import Curso.Clase.Clase;
import Curso.Curso;
import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.estudios.Estudio;
import Evaluacion.Evaluacion;
import Respuesta.Respuesta;
import Usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Estudiante extends Usuario {
    private String FormInscripcion;
    private int GeneracionAnioEstudiante;
    
    @OneToMany
    private List<Evaluacion> EvaluacionesEstudiante;
    
    @OneToMany
    private List<Respuesta> RespuestasEstudiante;
    
    @OneToMany
    private List<Estudio> EstudiosCursadosEstudiante;
    
    @ManyToMany
    @JoinTable(
      name="estudiante_curso",
      joinColumns={@JoinColumn(name="estudiante_id", referencedColumnName="IdUsuario")},
      inverseJoinColumns={@JoinColumn(name="curso_id", referencedColumnName="IdCurso")})
    private List<Curso> CursosEstudiante;
    
    @ManyToMany
    @JoinTable(
      name="estudiante_clase",
      joinColumns={@JoinColumn(name="estudiante_id", referencedColumnName="IdUsuario")},
      inverseJoinColumns={@JoinColumn(name="clase_id", referencedColumnName="IdClase")})
    private List<Clase> ClasesEstudiante;
    

    //  Constructores
    public Estudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, 
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, 
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, 
            String LugarNacimientoUsuario, EnumSexo SexoUsuario, int GeneracionAnioEstudiante) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario, 
                DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, 
                SexoUsuario);
        this.FormInscripcion = FormInscripcion;
        this.GeneracionAnioEstudiante = GeneracionAnioEstudiante;
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.EstudiosCursadosEstudiante = new ArrayList<>();    
        this.CursosEstudiante = new ArrayList<>();
        this.ClasesEstudiante = new ArrayList<>();
    }

    public Estudiante() {}
    
    //  Getters
    public String getFormInscripcion() {return FormInscripcion;}
    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}
    public List<Respuesta> getRespuestasEstudiante() {return RespuestasEstudiante;}
    public List<Estudio> getEstudiosCursadosEstudiante() {return EstudiosCursadosEstudiante;}    
    public int getGeneracionAnioEstudiante() {return GeneracionAnioEstudiante;}
    public List<Curso> getCursosEstudiante() {return CursosEstudiante;}
    public List<Clase> getClasesEstudiante() {return ClasesEstudiante;}
    
    
    //  Setters
    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}
    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}
    public void setRespuestasEstudiante(List<Respuesta> RespuestasEstudiante) {this.RespuestasEstudiante = RespuestasEstudiante;}
    public void setEstudiosCursadosEstudiante(List<Estudio> EstudiosCursadosEstudiante) {this.EstudiosCursadosEstudiante = EstudiosCursadosEstudiante;}
    public void setGeneracionAnioEstudiante(int GeneracionAnioEstudiante) {this.GeneracionAnioEstudiante = GeneracionAnioEstudiante;}
    public void setCursosEstudiante(List<Curso> CursosEstudiante) {this.CursosEstudiante = CursosEstudiante;}
    public void setClasesEstudiante(List<Clase> ClasesEstudiante) {this.ClasesEstudiante = ClasesEstudiante;}
    
    //  Respuestas
    public void addRespuestaEvaluacion(Respuesta RespuestaEvaluacion){this.RespuestasEstudiante.add(RespuestaEvaluacion);}    
    public void removeRespuestaEvaluacion(Respuesta RespuestaEvaluacion){this.RespuestasEstudiante.remove(RespuestaEvaluacion);}
    //  Estudios Cursados
    public void addEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.add(EstudioCursado);}    
    public void removeEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.remove(EstudioCursado);}
    //  Evaluacion Estudiante
    public void addEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.add(EvaluacionEstudiante);}
    public void removeEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.remove(EvaluacionEstudiante);}
    
    //Cursos
    public void addCursoEstudiante(Curso CursoEstudiante){
        this.CursosEstudiante.add(CursoEstudiante);
        if (!CursoEstudiante.getEstudiantesCurso().contains(this)) {
            CursoEstudiante.getEstudiantesCurso().add(this);
        }
    }
    public void removeCursoEstudiante(Curso CursoEstudiante){this.CursosEstudiante.remove(CursoEstudiante);}

    //  Clases
    public void addClaseEstudiante(Clase ClaseEstudiante){
        this.ClasesEstudiante.add(ClaseEstudiante);
        if (!ClaseEstudiante.getEstudiantesClase().contains(this)) {
            ClaseEstudiante.getEstudiantesClase().add(this);
        }
    }
}
