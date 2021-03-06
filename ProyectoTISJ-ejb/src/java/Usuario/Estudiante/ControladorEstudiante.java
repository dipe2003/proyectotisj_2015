
package Usuario.Estudiante;

import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEstudiante {
    @EJB
            ManejadorEstudiante mEst;
    
    /**
     * Crea un estudiante y los persiste.
     * @param FormInscripcion
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param SaltPassword
     * @param ImagenUsuario
     * @param CedulaUsuario del tipo 12345672 (sin puntos ni guiones)
     * @param CredencialCivicaUsuario
     * @param DomicilioUsuario
     * @param DepartamentoUsuario
     * @param LocalidadUsuario
     * @param TelefonoUsuario
     * @param CelularUsuario
     * @param EstadoCivilUsuario
     * @param FechaNacimientoUsuario
     * @param LugarNacimientoUsuario
     * @param SexoUsuario
     * @param GeneracionAnioEstudiante
     * @return null si no se creo el estudiante
     */
    public Estudiante CrearEstudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario,
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario,
            String LugarNacimientoUsuario, EnumSexo SexoUsuario, int GeneracionAnioEstudiante) {
        Estudiante est = new Estudiante(FormInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, SaltPassword, ImagenUsuario, CedulaUsuario,
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario, GeneracionAnioEstudiante);
        if (mEst.CrearEstudiante(est)!=-1) {
            return est;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Estudiante en la base de datos.
     * @param estudiante
     * @param IdEstudiante
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEstudiante(Usuario estudiante, int IdEstudiante){
        Estudiante est = mEst.BuscarEstudiante(IdEstudiante);
        est.setPasswordUsuario(estudiante.getPasswordUsuario());
        est.setSaltPasswordUsuario(estudiante.getSaltPasswordUsuario());
        est.setApellidoUsuario(estudiante.getApellidoUsuario());
        est.setCedulaUsuario(estudiante.getCedulaUsuario());
        est.setCelularUsuario(estudiante.getCelularUsuario());
        est.setCorreoUsuario(estudiante.getCorreoUsuario());
        est.setCredencialCivicaUsuario(estudiante.getCredencialCivicaUsuario());
        est.setDepartamentoUsuario(estudiante.getDepartamentoUsuario());
        est.setDomicilioUsuario(estudiante.getDomicilioUsuario());
        est.setEstadoCivilUsuario(estudiante.getEstadoCivilUsuario());
        est.setFechaNacimientoUsuario(estudiante.getFechaNacimientoUsuario());
        est.setImagenUsuario(estudiante.getImagenUsuario());
        est.setLocalidadUsuario(estudiante.getLocalidadUsuario());
        est.setLugarNacimientoUsuario(estudiante.getLugarNacimientoUsuario());
        est.setNombreUsuario(estudiante.getNombreUsuario());
        est.setSexoUsuario(estudiante.getSexoUsuario());
        est.setTelefonoUsuario(estudiante.getTelefonoUsuario());
        est.setGeneracionAnioEstudiante(((Estudiante)estudiante).getGeneracionAnioEstudiante());
        //est.setEstudiosCursadosEstudiante(((Estudiante)estudiante).getEstudiosCursadosEstudiante());
        return mEst.ModificarEstudiante(est);
    }
    
    /**
     * Actualiza todos los datos del estudiante en la base de datos
     * @param estudiante
     * @return 
     */
    public int ModificarInstEstudiante(Estudiante estudiante){
        return mEst.ModificarEstudiante(estudiante);
    }
    
    /**
     * Borra un Estudiante de la base de datos.
     * @param estudiante
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEstudiante(Estudiante estudiante){
        return mEst.BorrarEstudiante(estudiante);
    }
    
    /**
     * Busca un Estudiante en la base de datos.
     * @param id
     * @return Devuelve null si el Estudiante no se pudo encontrar.
     */
    public Estudiante BuscarEstudiante(int id){
        return mEst.BuscarEstudiante(id);
    }
    
    /**
     * Devuelve una lista de Estudiantes desde la base de datos.
     * @return
     */
    public List<Estudiante> ListarEstudiantes(){
        return mEst.ListarEstudiantes();
    }
    /**
     * Devuelve una lista de Estudiantes desde la base de datos segun sus ids.
     * @param IdsEstudiantes
     * @return
     */
    public List<Estudiante> ListarEstudiantes(List<Integer> IdsEstudiantes){
        return mEst.ListarEstudiantes(IdsEstudiantes);
    }
    /**
     * Devuelve una lista de Estudiantes que pertenezcan al curso indicado por su id.
     * @param IdCurso
     * @return
     */
    public List<Estudiante> ListarEstudiantesCurso(int IdCurso){
        return mEst.ListarEstudiantesCurso(IdCurso);
    }
    /**
     * Devuelve una lista de Estudiantes que no pertenezcan al curso indicado por su id.
     * @param IdCurso
     * @return
     */
    public List<Estudiante> ListarEstudiantesSinCurso(int IdCurso){
        return mEst.ListarEstudiantesSinCurso(IdCurso);
    }
    
    /**
     * Devuelve una lista de ids de estudios cursados del estudiante.
     * @param IdEstudiante
     * @return
     */
    public List<Integer> ListarEstudiosOrientacionCursados(int IdEstudiante){
        return mEst.ListarEstudios(IdEstudiante);
    }
    
    /**
     * Lista los usuarios estudiantes que estan cursando la asignatura indicada, en el año y el semestre especificado.
     * @param SemestreCurso si = 0 se devuelven todos los semestres
     * @param AnioCurso si = 0 se devuelven todos los años
     * @param IdAsignatura si = 0 se devuelven todas las asignaturas
     * @return
     */
    public List<Estudiante> ListarEstudiantesCurso(int SemestreCurso, int AnioCurso, int IdAsignatura){
        //  semestre  = 0 | anio  = 0 | asignatura  = 0
        if (SemestreCurso == 0 && AnioCurso == 0 && IdAsignatura == 0) {
            return mEst.ListarEstudiantes();
        }
        
        //  semestre  = 0 | anio  = 0 | asingatura != 0
        if (SemestreCurso == 0 && AnioCurso == 0 && IdAsignatura != 0) {
            return mEst.ListarEstudiantesCursoAsignatura(IdAsignatura);
        }
        
        //  semestre  = 0 | anio != 0 | asignatura  = 0
        if (SemestreCurso == 0 && AnioCurso != 0 && IdAsignatura == 0) {
            return mEst.ListarEstudiantesCursoAnio(AnioCurso);
        }
        
        //  semestre  = 0 | anio != 0 | asignatura != 0
        if (SemestreCurso == 0 && AnioCurso != 0 && IdAsignatura != 0) {
            return mEst.ListarEstudiantesCursoAsignaturaAnio(AnioCurso, IdAsignatura);
        }
        
        //  semestre != 0 | anio  = 0 | asignatura  = 0
        if (SemestreCurso != 0 && AnioCurso == 0 && IdAsignatura == 0) {
            return mEst.ListarEstudiantesCursoSemestre(SemestreCurso);
        }
        
        //  semestre != 0 | anio  = 0 | asingatura != 0
        if (SemestreCurso != 0 && AnioCurso == 0 && IdAsignatura != 0) {
            return mEst.ListarEstudiantesCursoAsignaturaSemestre(AnioCurso, IdAsignatura);
        }
        
        //  semestre != 0 | anio != 0 | asignatura  = 0
        if (SemestreCurso != 0 && AnioCurso != 0 && IdAsignatura == 0) {
            return mEst.ListarEstudiantesCursoSemestreAnio(SemestreCurso, AnioCurso);
        }
        
        //  semestre != 0 | anio != 0 | asignatura != 0
        if (SemestreCurso != 0 && AnioCurso != 0 && IdAsignatura != 0) {
            return mEst.ListarEstudiantesCursoAsignaturaSemestre(SemestreCurso, IdAsignatura, AnioCurso);
        }
        return null;
    }
    
    /**
     * Devuelve los estudiantes que contestaron la encuesta.
     * @param IdEncuesta
     * @return
     */
    public List<Estudiante> getEstudiantesEncuesta(int IdEncuesta){
        return mEst.ListarEstudiantesEncuesta(IdEncuesta);
    }
    
    /**
     * Devuelve los estudiantes que deben contestar la encuesta
     * @param IdEncuesta
     * @return
     */
    public List<Estudiante> getEstudiantesCursoEncuesta(int IdEncuesta){
        return mEst.ListarEstudiantesEncuestaCurso(IdEncuesta);
    }
    
    public List<Estudiante> ListarCompanierosDeClases(int idEstudiante){
        return mEst.ListarCompanierosDeClases(idEstudiante);
    }
    
    public List<Estudiante> ListarAlumnosDocente(int idDocente){
        return mEst.ListarAlumnosDocente(idDocente);
    }
}
