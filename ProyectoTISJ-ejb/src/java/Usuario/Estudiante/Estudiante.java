
package Usuario.Estudiante;

import Asignatura.Curso.Clase.Clase;
import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.Encuesta;
import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.Estudios.Estudio;
import Asignatura.Curso.Evaluacion.Evaluacion;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
import Usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Estudiante extends Usuario {
    private String FormInscripcion;
    private int GeneracionAnioEstudiante;
    
    @OneToMany(mappedBy = "EstudianteEvaluacion")
    private List<Evaluacion> EvaluacionesEstudiante;

    @OneToMany(mappedBy = "EstudianteEstudioCursado")
    @LazyCollection(LazyCollectionOption.FALSE)
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
    
    @OneToMany(mappedBy = "EstudianteResultado")
    private List<Resultado> ResultadosEstudiante;
    
    @ManyToMany
    @JoinTable(
            name="estudiante_encuesta",
            joinColumns={@JoinColumn(name="estudiante_id", referencedColumnName="IdUsuario")},
            inverseJoinColumns={@JoinColumn(name="encuesta_id", referencedColumnName="IdEncuesta")})
    private List<Encuesta> EncuestasEstudiante;
    
    //  Constructores
    public Estudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario,
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario,
            String LugarNacimientoUsuario, EnumSexo SexoUsuario, int GeneracionAnioEstudiante) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, SaltPassword, ImagenUsuario, CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario,
                DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario,
                SexoUsuario);
        this.FormInscripcion = FormInscripcion;
        this.GeneracionAnioEstudiante = GeneracionAnioEstudiante;
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.EstudiosCursadosEstudiante = new ArrayList<>();
        this.CursosEstudiante = new ArrayList<>();
        this.ClasesEstudiante = new ArrayList<>();
        this.ResultadosEstudiante = new ArrayList<>();
        this.EncuestasEstudiante = new ArrayList<>();
    }
    
    public Estudiante() {}
    
    //  Getters
    public String getFormInscripcion() {return FormInscripcion;}
    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}
    public List<Estudio> getEstudiosCursadosEstudiante() {return EstudiosCursadosEstudiante;}
    public int getGeneracionAnioEstudiante() {return GeneracionAnioEstudiante;}
    public List<Curso> getCursosEstudiante() {return CursosEstudiante;}
    public List<Clase> getClasesEstudiante() {return ClasesEstudiante;}
    public List<Resultado> getResultadosEstudiante() {return ResultadosEstudiante;}
    public List<Encuesta> getEncuestasEstudiante() {return EncuestasEstudiante;}
    
    //  Setters
    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}
    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}
    public void setEstudiosCursadosEstudiante(List<Estudio> EstudiosCursadosEstudiante) {this.EstudiosCursadosEstudiante = EstudiosCursadosEstudiante;}
    public void setGeneracionAnioEstudiante(int GeneracionAnioEstudiante) {this.GeneracionAnioEstudiante = GeneracionAnioEstudiante;}
    public void setCursosEstudiante(List<Curso> CursosEstudiante) {this.CursosEstudiante = CursosEstudiante;}
    public void setClasesEstudiante(List<Clase> ClasesEstudiante) {this.ClasesEstudiante = ClasesEstudiante;}
    public void setResultadosEstudiante(List<Resultado> ResultadosEstudiante) {this.ResultadosEstudiante = ResultadosEstudiante;}
    public void setEncuestasEstudiante(List<Encuesta> EncuestasEstudiante) {this.EncuestasEstudiante = EncuestasEstudiante;}
    
    //  Estudios Cursados
    public void addEstudioCursado(Estudio EstudioCursado){
        this.EstudiosCursadosEstudiante.add(EstudioCursado);
        if(EstudioCursado.getEstudianteEstudioCursado()==null || !EstudioCursado.getEstudianteEstudioCursado().equals(this)){
            EstudioCursado.setEstudianteEstudioCursado(this);
        }
    }
    public void removeEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.remove(EstudioCursado);}
    //  Evaluacion Estudiante
    public void addEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){
        this.EvaluacionesEstudiante.add(EvaluacionEstudiante);
        if(EvaluacionEstudiante.getEstudianteEvaluacion()==null || !EvaluacionEstudiante.getEstudianteEvaluacion().equals(this)){
            EvaluacionEstudiante.setEstudianteEvaluacion(this);
        }
    }
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
    
    //  Resultados
    public void addResultadoEstudiante(Resultado ResultadoEstudiante){
        this.ResultadosEstudiante.add(ResultadoEstudiante);
        if (ResultadoEstudiante.getEstudianteResultado()==null || !ResultadoEstudiante.getEstudianteResultado().equals(this)) {
            ResultadoEstudiante.setEstudianteResultado(this);
        }
    }
    
    //  Encuestas
    public void addEncuestaEstudiante(Encuesta EncuestaEstudiante){
        this.EncuestasEstudiante.add(EncuestaEstudiante);
        if (!EncuestaEstudiante.getEstudiantesEncuesta().contains(this)) {
            EncuestaEstudiante.getEstudiantesEncuesta().add(this);
        }
    }
    
}
