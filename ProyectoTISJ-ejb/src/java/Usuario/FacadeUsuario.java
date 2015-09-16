
package Usuario;

import Administrador.Administrador;
import Administrador.ControladorAdministrador;
import Administrativo.Administrativo;
import Administrativo.ControladorAdministrativo;
import Docente.ControladorDocente;
import Docente.Docente;
import Estudiante.ControladorEstudiante;
import Estudiante.Estudiante;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
     * Devuelve el id del usuario registrado.
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @param Rol
     * @param FormInscripcion Strig de ubicacion del formulario de Inscripcion.
     * @param ImagenUsuario 
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarUsuario(String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, 
            String Rol, String FormInscripcion, String ImagenUsuario){
        if (ImagenUsuario.isEmpty()) ImagenUsuario = "../Resources/Images/userProfile.jpg";
        Usuario Usr = null;
        if (ExisteUsuario(CedulaUsuario, Rol)== -1) {
            switch(Rol){
                case "Administrador":
                    Usr = cAdministrador.CrearAdministrador(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
                    break;

                case "Administrativo":
                    Usr = cAdministrativo.CrearAdministrativo(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
                    break;

                case "Docente":
                    Usr = cDoc.CrearDocente(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
                    break;

                case "Estudiante":
                    if(!FormInscripcion.isEmpty()) Usr = cEst.CrearEstudiante(FormInscripcion, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
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
    
}
