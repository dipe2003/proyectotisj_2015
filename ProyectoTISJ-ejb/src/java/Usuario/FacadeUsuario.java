
package Usuario;

import Administrador.Administrador;
import Administrador.ControladorAdministrador;
import Administrativo.Administrativo;
import Administrativo.ControladorAdministrativo;
import Docente.ControladorDocente;
import Docente.Docente;
import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.ControladorEstudiante;
import Estudiante.EnumSexo;
import Estudiante.Estudiante;
import Estudiante.estudios.ControladorEstudio;
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
    private ControladorEstudio cEstudio;
    
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
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarUsuario(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String ImagenUsuario,
            int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, String LocalidadUsuario,
            String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario,
            EnumSexo SexoUsuario, String Rol){
        if (ImagenUsuario.isEmpty()) ImagenUsuario = "../Resources/Images/userProfile.jpg";
        Usuario Usr = null;
        if (ExisteUsuario(CedulaUsuario, Rol)== -1) {
            switch(Rol){
                case "Administrador":
                    Usr = cAdministrador.CrearAdministrador(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario,
                            CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario,
                            EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
                    break;
                    
                case "Administrativo":
                    Usr = cAdministrativo.CrearAdministrativo(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario,
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
                    break;
                    
                case "Docente":
                    Usr = cDoc.CrearDocente(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario,
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
     * @param usuario
     * @param Rol
     * @return -1 si no se pudo actualizar
     */
    public int ModificarUsuario(Usuario usuario, String Rol){
        switch(Rol){
            case "Administrador":
                return cAdministrador.ModificarAdministrador((Administrador)usuario);
                
            case "Administrativo":
                return cAdministrativo.ModificarAdministrativo((Administrativo)usuario);
                
            case "Docente":
                return cDoc.ModificarDocente((Docente)usuario);
                
            case "Estudiante":
                return cEst.ModificarEstudiante((Estudiante)usuario);
        }
        return -1;
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
     * @return
     */
    public int RegistrarUsuario(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario,
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario,
            String LugarNacimientoUsuario, EnumSexo SexoUsuario){
        if (ImagenUsuario.isEmpty()) ImagenUsuario = "../Resources/Images/userProfile.jpg";
        Usuario Usr = null;
        if (ExisteUsuario(CedulaUsuario, "Estudiante")== -1) {
            if(!FormInscripcion.isEmpty()) {
                Usr = cEst.CrearEstudiante(FormInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario,
                        CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                        FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
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
}
