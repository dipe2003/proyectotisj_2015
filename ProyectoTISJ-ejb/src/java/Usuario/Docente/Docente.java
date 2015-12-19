
package Usuario.Docente;

import Asignatura.Curso.Curso;
import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.EnumSexo;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Docente extends Usuario implements Serializable{

    @OneToMany(mappedBy = "DocenteCurso")
    private List<Curso> CursosDocente;
    public Docente() {}

    public Docente(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, 
            String DepartamentoUsuario, String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, 
            EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, EnumSexo SexoUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, SaltPassword, ImagenUsuario, CedulaUsuario, 
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, 
                CelularUsuario, EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
        CursosDocente = new ArrayList<>();
    }
    
    //  Getters
    public List<Curso> getCursosDocente() {return CursosDocente;}
    
    //  Setters

    public void setCursosDocente(List<Curso> CursosDocente) {
        this.CursosDocente = CursosDocente;
    }
    
    public void addCursoDocente(Curso CursoDocente){
        this.CursosDocente.add(CursoDocente);
        if(CursoDocente.getDocenteCurso()== null || !CursoDocente.getDocenteCurso().equals(this)){
            CursoDocente.setDocenteCurso(this);
        }
    }
    
    
    
}
