
package Usuario;

import Administrador.ControladorAdministrador;
import Administrativo.ControladorAdministrativo;
import Docente.ControladorDocente;
import Estudiante.ControladorEstudiante;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@Stateless
@SessionScoped
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
     * Devuelve el id del usuario especificado si existe.
     * @param NickUsuario
     * @param Password
     * @param Rol
     * @return -1 si no existe.
     */
    public int ExisteUsuario(String NickUsuario, String Password, String Rol){
        Usuario usuario = cUsr.ExisteUsuario(NickUsuario, Password, Rol);
        if (usuario != null) {
            return usuario.getIdUsuario();
        }else{
            return -1;
        }
    }
    
    /**
     * Devuelve el id del usuario registrado.
     * @param NickUsuario
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @param Rol
     * @param Extra Strig de ubicacion del formualrio de Inscripcion (Estudiante) o Contrato (Docente).
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarUsuario(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String Rol, String Extra){
        Usuario Usr = null;
        if (ExisteUsuario(NickUsuario, PasswordUsuario, Rol)== -1) {
            switch(Rol){
                case "Administrador":
                    Usr = cAdministrador.CrearAdministrador(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
                    break;

                case "Administrativo":
                    Usr = cAdministrativo.CrearAdministrativo(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
                    break;

                case "Docente":
                    if(!Extra.isEmpty()) Usr = cDoc.CrearDocente(Extra, NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
                    break;

                case "Estudiante":
                    if(!Extra.isEmpty()) Usr = cEst.CrearEstudiante(Extra, NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
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
    
}
