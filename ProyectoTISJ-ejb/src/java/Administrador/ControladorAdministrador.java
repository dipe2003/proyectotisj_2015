
package Administrador;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorAdministrador {
    @EJB
    ManejadorAdmininstrador mAdmin;
    
    /**
     * Crea un Administrador y lo persiste.
     * @param NickUsuario
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaIdentidadUsuario
     * @return Devulve un Admininstrador si fue creado, de lo contrario devuelve null.
     */
    public Administrador CrearAdministrador(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario,  int CedulaIdentidadUsuario){
        Administrador admin = new Administrador(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario,  CedulaIdentidadUsuario);
        if (mAdmin.CrearAdministrador(admin)!=-1) {
            return admin;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Administrador en la base de datos.
     * @param administrador
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarAdministrador(Administrador administrador){
        return mAdmin.ModificarAdministrador(administrador);
    }
    
    /**
     * Borra un Administrador de la base de datos.
     * @param administrador
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarAdministrador(Administrador administrador){
        return mAdmin.BorrarAdministrador(administrador);
    }
    
    /**
     * Busca un Admininstrador en la base de datos.
     * @param id
     * @return Devuelve null si el Administrador no se pudo encontrar.
     */
    public Administrador BuscarAdministrador(int id){
        return mAdmin.BuscarAdministrador(id);
    }
    
    /**
     * Devuelve una lista de Administradores desde la base de datos.
     * @return 
     */
    public List<Administrador> ListarAdministradores(){
        return mAdmin.ListarAdministradores();
    }
}
