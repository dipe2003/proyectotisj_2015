package Administrativo;

import java.util.List;
import javax.ejb.EJB;

public class ControladorAdministrativo {
    
    @EJB
    ManejadorAdministrativo mAdmin;
    
    /**
     * Crea un Administrativo y lo persiste.
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @return Devuelve un Administrativo si fue creado, de lo contrario devuelve null.
     */
    public Administrativo CrearAdministrativo(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario){
        Administrativo admin = new Administrativo(NombreUsuario,ApellidoUsuario,CorreoUsuario,PasswordUsuario,CedulaUsuario);
        if (mAdmin.CrearAdministrativo(admin)!=-1){
            return admin;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Administrativo en la base de datos.
     * @param administrativo
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarAdministrativo(Administrativo administrativo){
        return mAdmin.ModificarAdministrativo(administrativo);
    }
    
    /**
     * Borra los datos de un Administrativo en la base de datos.
     * @param administrativo
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarAdministrativo(Administrativo administrativo){
        return mAdmin.BorrarAdministrativo(administrativo);
    }
    
    /**
     * Busca un Administrativo en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Administrativo BuscarAdministrativo(int id){
        return mAdmin.BuscarAdministrativo(id);
    }
    
    /**
     * Devuelve una lista de Administrativos desde la base de datos.
     * @return 
     */
    public List<Administrativo> ListarAdministrativos(){
        return mAdmin.ListarAdministrativos();
    }
    
}
