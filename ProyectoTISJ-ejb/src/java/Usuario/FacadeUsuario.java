
package Usuario;

import Usuario.Administrador.ControladorAdministrador;
import Usuario.Administrativo.ControladorAdministrativo;
import Usuario.Docente.ControladorDocente;
import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.ControladorEstudiante;
import Usuario.Estudiante.EnumSexo;
import Utilidades.Seguridad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeUsuario implements Serializable {
    @EJB
    private ControladorUsuario cUsr;
    @EJB
    private ControladorAdministrador cAdministrador;
    @EJB
    private ControladorAdministrativo cAdministrativo;
    @EJB
    private ControladorDocente cDoc;
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private Seguridad cSeguridad;
    
    public FacadeUsuario() {}
    
    
    /**
     * Devuelve el id del usuario especificado por su id, password y Rol, si existe.
     * @param CedulaUsuario
     * @param Password
     * @param Rol
     * @return -1 si no existe.
     */
    public int ValidarLogin(int CedulaUsuario, String Password, String Rol){
        Usuario usuario = cUsr.ValidarUsuario(CedulaUsuario, Password, Rol);
        if (usuario != null) {
            return usuario.getIdUsuario();
        }else{
            return -1;
        }
    }
    /**
     * Devuelve los roles del usuario especificado por su cedula y password, si existe.
     * @param CedulaUsuario
     * @param Password
     * @return -1 si no existe.
     */
    public List<String> ValidarLogin(int CedulaUsuario, String Password){
        return cUsr.ValidarUsuario(CedulaUsuario, Password);
    }
    
    /**
     * Devuelve el id del usuario registrado.<br/>
     * <b>No realiza el registro de usuario estudiante</b>
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
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
     * @param Rol
     * @param IdUsuario
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarUsuario(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String ImagenUsuario,
            int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, String LocalidadUsuario,
            String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario,
            EnumSexo SexoUsuario, String Rol, int IdUsuario){
        if (ImagenUsuario.isEmpty()) ImagenUsuario = "../Resources/Images/userProfile.jpg";
        Usuario Usr = null;
        // array para guardar password mas la palabra clave
        String[] pass = new String[2];
        //  si se recibe un idusuario se esta registrando un nuevo rol, por lo que se debe traer password y palabra del ya registrado.
        if(IdUsuario != 0 && IdUsuario != -1) {
            Usr = BuscarUsuario(IdUsuario);
            pass[0] = Usr.getSaltPasswordUsuario();
            pass[1] = Usr.getPasswordUsuario();
        }else{
            pass = cSeguridad.getPasswordSeguro(PasswordUsuario);
        }
        if (ExisteUsuario(CedulaUsuario, Rol)== -1) {
            switch(Rol){
                case "Administrador":
                    Usr = cAdministrador.CrearAdministrador(NombreUsuario, ApellidoUsuario, CorreoUsuario, pass[1], pass[0], ImagenUsuario,
                            CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario,
                            EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
                    break;
                    
                case "Administrativo":
                    Usr = cAdministrativo.CrearAdministrativo(NombreUsuario, ApellidoUsuario, CorreoUsuario, pass[1], pass[0], ImagenUsuario, CedulaUsuario,
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
                    break;
                    
                case "Docente":
                    Usr = cDoc.CrearDocente(NombreUsuario, ApellidoUsuario, CorreoUsuario, pass[1], pass[0], ImagenUsuario, CedulaUsuario,
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario,
                            EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
                    break;
            }
        }
        if (Usr==null) {
            return -1;
        }else{
            return Usr.getIdUsuario();
        }
    }
    
    /**
     * Devuelve el usuario identificado por su id.
     * PRE: existe el usuario en la base de datos.
     * @param Id
     * @return
     */
    public Usuario BuscarUsuario(int Id){
        return cUsr.BuscarUsuario(Id);
    }
    
    /**
     * Devuelve el id del usuario especificado si existe.
     * @param CedulaUsuario
     * @param Password
     * @param Rol
     * @return -1 si no existe.
     */
    private int ExisteUsuario(int CedulaUsuario, String Rol){
        Usuario usuario = cUsr.ExisteUsuario(CedulaUsuario, Rol);
        if (usuario != null) {
            return usuario.getIdUsuario();
        }else{
            return -1;
        }
    }
    
    /**
     * Actualiza los datos de un usuario en la base de datos.
     * Comprueba los roles y actualiza los datos de cada uno.
     * @param usuario
     * @return -1 si no se pudo actualizar
     */
    public int ModificarUsuario(Usuario usuario){
        Usuario userBD = BuscarUsuario(usuario.getIdUsuario());
        List<String> roles = cUsr.getRolesUsuario(userBD.getCedulaUsuario());
        int correcto = -1;
        for(String Rol: roles){
            switch(Rol){
                case "Administrador":
                    correcto = cAdministrador.ModificarAdministrador(usuario, cUsr.ExisteUsuario(usuario.getCedulaUsuario(), Rol).getIdUsuario());
                    break;
                case "Administrativo":
                    correcto =  cAdministrativo.ModificarAdministrativo(usuario, cUsr.ExisteUsuario(usuario.getCedulaUsuario(), Rol).getIdUsuario());
                    break;
                case "Docente":
                    correcto = cDoc.ModificarDocente(usuario, cUsr.ExisteUsuario(usuario.getCedulaUsuario(), Rol).getIdUsuario());
                    break;
                case "Estudiante":
                    correcto =  cEst.ModificarEstudiante(usuario, cUsr.ExisteUsuario(usuario.getCedulaUsuario(), Rol).getIdUsuario());
                    break;
            }
        }
        return correcto;
    }
    
    /**
     * Devuelve el id del usuario registrado.<br/>
     * <b>Solo registra usuario estudiante</b>
     * @param FormInscripcion
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
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
     * @return
     */
    public int RegistrarUsuario(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario,
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario,
            String LugarNacimientoUsuario, EnumSexo SexoUsuario, int GeneracionAnioEstudiante){
        if (ImagenUsuario.isEmpty()) ImagenUsuario = "../Resources/Images/userProfile.jpg";
        Usuario Usr = null;
        if (ExisteUsuario(CedulaUsuario, "Estudiante")== -1) {
            if(!FormInscripcion.isEmpty()) {
                String[] pass = cSeguridad.getPasswordSeguro(PasswordUsuario);
                Usr = cEst.CrearEstudiante(FormInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, pass[1], pass[0], ImagenUsuario, CedulaUsuario,
                        CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                        FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario, GeneracionAnioEstudiante);
            }
        }
        if (Usr==null) {
            return -1;
        }else{
            return Usr.getIdUsuario();
        }
    }
    
    /**
     * Devuleve una lista de usuarios del rol especificado.
     * @param Rol Estudiante, Docente, Administrador, Administrativo
     * @return una lista vacia si no existen usuarios del rol especificado.
     */
    public List<Usuario> listarUsuarios(String Rol){
        switch(Rol){
            case "Administrador":
                return (List<Usuario>) (ArrayList<?>) cAdministrador.ListarAdministradores();
                
            case "Administrativo":
                return (List<Usuario>) (ArrayList<?>) cAdministrativo.ListarAdministrativos();
                
            case "Docente":
                return (List<Usuario>) (ArrayList<?>) cDoc.ListarDocentes();
                
            case "Estudiante":
                return (List<Usuario>) (ArrayList<?>) cEst.ListarEstudiantes();
        }
        return new ArrayList<>();
    }
    
    /**
     * Devuleve una lista de usuarios que no son del rol especificado.
     * @param Rol Estudiante, Docente, Administrador, Administrativo
     * @return una lista vacia si no existen usuarios que no son del rol especificado.
     */
    public List<Usuario> listarUsuariosSinRol(String Rol){
        List<Usuario> allUsr = cUsr.ListarUsuarios();
        List<Integer> cedulasParaBorrar = new ArrayList<>();
        for(Usuario usr : allUsr){
            if (cUsr.EsRol(usr, Rol)) cedulasParaBorrar.add(usr.getCedulaUsuario());
        }
        for (Integer cedula : cedulasParaBorrar){
            cUsr.removeUsrByCI(cedula, allUsr);
        }
        cUsr.removeRepeatUsr(allUsr);
        return allUsr;
    }
    
    /**
     * retorna true si el usuario de cedula Cedula existe en la base de datos
     * @param Cedula
     * @return
     */
    public boolean ExisteUsuario(String Cedula){
        int CI = Integer.valueOf(Cedula);
        return cUsr.ExisteUsuario(CI);
    }
    
    /**
     * Lista los usuarios docentes que estan dictando curso en el año y el semestre especificado.
     * @param SemestreCurso si = 0 se devuelven todos los semestres
     * @param AnioCurso si = 0 se devuelven todos los años
     * @param IdAsignatura si = 0 se devuelven todas las asignaturas
     * @return
     */
    public List<Usuario> listarUsuariosDocente(int SemestreCurso, int AnioCurso, int IdAsignatura){
        return (List<Usuario>) (ArrayList<?>) cDoc.ListarDocentesCurso(SemestreCurso, AnioCurso, IdAsignatura);
    }
    
    /**
     * Lista los usuarios estudiantes que estan dictando curso en el año y el semestre especificado.
     * @param SemestreCurso si = 0 se devuelven todos los semestres
     * @param AnioCurso si = 0 se devuelven todos los años
     * @param IdAsignatura si = 0 se devuelven todas las asignaturas
     * @return
     */
    public List<Usuario> listarUsuariosEstudiante(int SemestreCurso, int AnioCurso, int IdAsignatura){
        return (List<Usuario>) (ArrayList<?>) cEst.ListarEstudiantesCurso(SemestreCurso, AnioCurso, IdAsignatura);
    }
    
    /**
     * Lista los usuarios estudiantes que pertenecen al curso especificado por su id.
     * @param IdCurso
     * @return
     */
    public List<Usuario> listarUsuarioEstudianteCurso(int IdCurso){
        return (List<Usuario>) (ArrayList<?>) cEst.ListarEstudiantesCurso(IdCurso);
    }
    /**
     * Lista los usuarios estudiantes que no pertenecen al curso especificado por su id.
     * @param IdCurso
     * @return
     */
    public List<Usuario> listarUsuarioEstudianteSinCurso(int IdCurso){
        return (List<Usuario>) (ArrayList<?>) cEst.ListarEstudiantesSinCurso(IdCurso);
    }
}
