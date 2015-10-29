
package Usuario.Administrador;

import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.EnumSexo;
import java.util.Date;
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
     * @return Devulve un Admininstrador si fue creado, de lo contrario devuelve null.
     */
    public Administrador CrearAdministrador(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword, String ImagenUsuario, 
            int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, String LocalidadUsuario, 
            String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, 
            EnumSexo SexoUsuario){
        Administrador admin = new Administrador(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, SaltPassword, ImagenUsuario, CedulaUsuario, 
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, 
                EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario,SexoUsuario);
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
