package Usuario;

import Administrador.Administrador;
import Administrativo.Administrativo;
import Docente.Docente;
import Estudiante.Estudiante;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorUsuario {
    
    @EJB
    ManejadorUsuario mUsr;
    
    /**
     * Crea un Usuario y lo persiste.
     * @param NickUsuario
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @return Devuelve un Usuario si fue creado, de lo contrario devuelve null.
     */
    public Usuario CrearUsuario(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario){
        Usuario usr = new Usuario(NickUsuario,NombreUsuario,CorreoUsuario,PasswordUsuario,CedulaUsuario);
        if (mUsr.CrearUsuario(usr)!=-1){
            return usr;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Usuario en la base de datos.
     * @param usuario
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarUsuario(Usuario usuario){
        return mUsr.ModificarUsuario(usuario);
    }
    
    /**
     * Borra los datos de un Usuario en la base de datos.
     * @param usuario
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarUsuario(Usuario usuario){
        return mUsr.BorrarUsuario(usuario);
    }
    
    /**
     * Busca un Usuario en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Usuario BuscarUsuario(int id){
        return mUsr.BuscarUsuario(id);
    }
    
    /**
     * Devuelve el usuario que coincida con los datos especificados.
     * @param NickName
     * @param Password
     * @param Rol
     * @return Devuelve null si no existe.
     */
    public Usuario ExisteUsuario(String NickName, String Password, String Rol){
        List<Usuario> Usuarios = mUsr.BuscarUsuario(NickName, Password);
        if (!Usuarios.isEmpty()) {
            for (int i = 0; i < Usuarios.size(); i++) {
                if (EsRol(Usuarios.get(i), Rol)) {
                    return Usuarios.get(i);
                }
            }
        }
        return null;
    }
    
    
    /**
     * Devuelve una lista de Usuarios desde la base de datos.
     * @return 
     */
    public List<Usuario> ListarUsuarios(){
        return mUsr.ListarUsuarios();
    }
    
    /**
     * Comprueba el Rol del Usuario especificado
     * @param usuario
     * @param Rol
     * @return True: si coincide el rol.
     */
    private boolean EsRol(Usuario usuario,String Rol){
        switch(Rol){
            case "Administrador":
                if (usuario instanceof Administrador) {
                    return true;
                }
                
            case "Administrativo":
                if (usuario instanceof Administrativo) {
                    return true;
                }
                
            case "Docente":
                if (usuario instanceof Docente) {
                    return true;
                }
                
            case "Estudiante":
                if (usuario instanceof Estudiante) {
                    return true;
                }
        }
        return false;
    }
    
}
