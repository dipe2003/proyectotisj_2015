package Administrativo;

import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.EnumSexo;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorAdministrativo {
    
    @EJB
            ManejadorAdministrativo mAdmin;
    
    /**
     * Crea un Administrativo y lo persiste.
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param ImagenUsuario
     * @param CedulaUsuario
     * @param CredencialCivicaUsuario
     * @param DomicilioUsuario
     * @param DepartamentoUsuario
     * @param LocalidadUsuario
     * @param TelefonoUsuario
     * @param CelularUsuario
     * @return Devuelve un Administrativo si fue creado, de lo contrario devuelve null.
     */
    /**
     * Crea un Administrativo y lo persiste.
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
     * @return Devuelve un Administrativo si fue creado, de lo contrario devuelve null.
     */
    public Administrativo CrearAdministrativo(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String ImagenUsuario, 
            int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, String LocalidadUsuario, String TelefonoUsuario, 
            String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, EnumSexo SexoUsuario){
        Administrativo admin = new Administrativo(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario, 
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, 
                FechaNacimientoUsuario, LugarNacimientoUsuario, 
            SexoUsuario);
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
