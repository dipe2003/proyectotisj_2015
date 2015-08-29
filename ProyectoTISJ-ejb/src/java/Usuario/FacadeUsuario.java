
package Usuario;

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

    public FacadeUsuario() {
    }
    
    
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
}
