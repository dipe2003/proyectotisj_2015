package Usuario.Docente;

import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.EnumSexo;
import Usuario.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorDocente {
    
    @EJB
    private ManejadorDocente mDoc;
    
    /**
     * Crea un Docente y lo persiste.
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
     * @return  Devuelve un Docente si fue creado, de lo contrario devuelve null.
     */
    public Docente CrearDocente(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario,
            String DepartamentoUsuario, String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario,
            EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, EnumSexo SexoUsuario){
        Docente doc = new Docente(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, SaltPassword, ImagenUsuario, CedulaUsuario,
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario,
                EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
        if (mDoc.CrearDocente(doc)!=-1){
            return doc;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Docente en la base de datos.
     * @param docente
     * @param IdDocente
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarDocente(Usuario docente, int IdDocente){
        Docente doc = mDoc.BuscarDocente(IdDocente);
        doc.setPasswordUsuario(docente.getPasswordUsuario());
        doc.setSaltPasswordUsuario(docente.getSaltPasswordUsuario());
        doc.setApellidoUsuario(docente.getApellidoUsuario());
        doc.setCedulaUsuario(docente.getCedulaUsuario());
        doc.setCelularUsuario(docente.getCelularUsuario());
        doc.setCorreoUsuario(docente.getCorreoUsuario());
        doc.setCredencialCivicaUsuario(docente.getCredencialCivicaUsuario());
        doc.setDepartamentoUsuario(docente.getDepartamentoUsuario());
        doc.setDomicilioUsuario(docente.getDomicilioUsuario());
        doc.setEstadoCivilUsuario(docente.getEstadoCivilUsuario());
        doc.setFechaNacimientoUsuario(docente.getFechaNacimientoUsuario());
        doc.setImagenUsuario(docente.getImagenUsuario());
        doc.setLocalidadUsuario(docente.getLocalidadUsuario());
        doc.setLugarNacimientoUsuario(docente.getLugarNacimientoUsuario());
        doc.setNombreUsuario(docente.getNombreUsuario());
        doc.setSexoUsuario(docente.getSexoUsuario());
        doc.setTelefonoUsuario(docente.getTelefonoUsuario());
        return mDoc.ModificarDocente(doc);
    }
    
    /**
     * Borra los datos de un Docente en la base de datos.
     * @param docente
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarDocente(Docente docente){
        return mDoc.BorrarDocente(docente);
    }
    
    /**
     * Busca un Docente en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Docente BuscarDocente(int id){
        return mDoc.BuscarDocente(id);
    }
    
    /**
     * Devuelve una lista de Docentes desde la base de datos.
     * @return
     */
    public List<Docente> ListarDocentes(){
        return mDoc.ListarDocentes();
    }
    
    /**
     * Lista los usuarios docentes que estan dictando curso de la asignatura indicada, en el año y el semestre especificado.
     * @param SemestreCurso si = 0 se devuelven todos los semestres
     * @param AnioCurso si = 0 se devuelven todos los años
     * @param IdAsignatura si = 0 se devuelven todas las asignaturas
     * @return
     */
    public List<Docente> ListarDocentesCurso(int SemestreCurso, int AnioCurso, int IdAsignatura){
        //  semestre  = 0 | anio  = 0 | asignatura  = 0
        if (SemestreCurso == 0 && AnioCurso == 0 && IdAsignatura == 0) {
            return mDoc.ListarDocentes();
        }
        
        //  semestre  = 0 | anio  = 0 | asingatura != 0
        if (SemestreCurso == 0 && AnioCurso == 0 && IdAsignatura != 0) {
            return mDoc.ListarDocentesCursoAsignatura(IdAsignatura);
        }
        
        //  semestre  = 0 | anio != 0 | asignatura  = 0
        if (SemestreCurso == 0 && AnioCurso != 0 && IdAsignatura == 0) {
            return mDoc.ListarDocentesCursoAnio(AnioCurso);
        }
        
        //  semestre  = 0 | anio != 0 | asignatura != 0
        if (SemestreCurso == 0 && AnioCurso != 0 && IdAsignatura != 0) {
            return mDoc.ListarDocentesCursoAsignaturaAnio(AnioCurso, IdAsignatura);
        }
        
        //  semestre != 0 | anio  = 0 | asignatura  = 0
        if (SemestreCurso != 0 && AnioCurso == 0 && IdAsignatura == 0) {
            return mDoc.ListarDocentesCursoSemestre(SemestreCurso);
        }
        
        //  semestre != 0 | anio  = 0 | asingatura != 0
        if (SemestreCurso != 0 && AnioCurso == 0 && IdAsignatura != 0) {
            return mDoc.ListarDocentesCursoAsignaturaAnio(AnioCurso, IdAsignatura);
        }
        
        //  semestre != 0 | anio != 0 | asignatura  = 0
        if (SemestreCurso != 0 && AnioCurso != 0 && IdAsignatura == 0) {
            return mDoc.ListarDocentesCursoSemestreAnio(SemestreCurso, AnioCurso);
        }
        
        //  semestre != 0 | anio != 0 | asignatura != 0
        if (SemestreCurso != 0 && AnioCurso != 0 && IdAsignatura != 0) {
            return mDoc.ListarDocentesCursoAsignaturaSemestre(SemestreCurso, IdAsignatura, AnioCurso);
        }
        return null;
    }
    
    /**
     * Devuelve el nombre del docente especificado por su id.
     * @param IdDocente
     * @return
     */
    public String getNombreDocente(int IdDocente){
        return mDoc.getNombreDocente(IdDocente);
    }
    
    public List<Docente> ListarProfesoresDeEstudiante(int idEstudiante){
        return mDoc.ListarProfesoresDeEstudiante(idEstudiante);
    }
    
    public Docente getDocenteCurso(int idCurso){
        return mDoc.getDocenteCurso(idCurso);
    }
    
}
