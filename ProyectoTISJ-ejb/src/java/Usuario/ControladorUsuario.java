package Usuario;

import Administrador.Administrador;
import Administrativo.Administrativo;
import Docente.Docente;
import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.EnumSexo;
import Estudiante.Estudiante;
import java.util.Date;
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
     * @return Devuelve un Usuario si fue creado, de lo contrario devuelve null.
     */
    public Usuario CrearUsuario(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario,
            String DepartamentoUsuario, String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario,
            EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario,
            EnumSexo SexoUsuario){
        Usuario usr = new Usuario(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario,ImagenUsuario, CedulaUsuario,
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario,
                EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);
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
        return mUsr.BuscarUsuarioPorId(id);
    }
    
    /**
     * Devuelve el usuario que coincida con los datos especificados.
     * @param Cedula
     * @param Password
     * @param Rol
     * @return Devuelve null si no existe.
     */
    public Usuario ValidarUsuario(int Cedula, String Password, String Rol){
        List<Usuario> Usuarios = mUsr.BuscarUsuarioLogin(Cedula, Password);
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
     * Devuelve el usuario que coincida con los datos especificados.
     * @param Cedula
     * @param Rol
     * @return Devuelve null si no existe.
     */
    public Usuario ExisteUsuario(int Cedula, String Rol){
        List<Usuario> Usuarios = mUsr.BuscarUsuarioPorCedula(Cedula);
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
    public boolean EsRol(Usuario usuario, String Rol){
        switch(Rol){
            case "Administrador":
                if (usuario instanceof Administrador) {
                    return true;
                }
                break;
                
            case "Administrativo":
                if (usuario instanceof Administrativo) {
                    return true;
                }
                break;
                
            case "Docente":
                if (usuario instanceof Docente) {
                    return true;
                }
                break;
                
            case "Estudiante":
                if (usuario instanceof Estudiante) {
                    return true;
                }
                break;
        }
        return false;
    }
    
    /**
     * Quita todos los usuarios de cedula CI de la lista de usuarios
     * @param cedula
     * @param usuarios
     */
    public void removeUsrByCI(int CI, List<Usuario> usuarios){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCedulaUsuario() == CI){
                usuarios.remove(i);
                i--;
            }
        }
    }
    
    /**
     * Quita los usuarios repetidos, dejando una unica instancia de cada uno
     * @param usuarios 
     */
    public void removeRepeatUsr(List<Usuario> usuarios){
        boolean UsrRepetido;
        for (int i = 0; i < usuarios.size(); i++) {
            UsrRepetido = false;
            for (int j = i+1; j < usuarios.size(); j++) {
                if (usuarios.get(i).getCedulaUsuario() == usuarios.get(j).getCedulaUsuario()){
                    UsrRepetido = true;
                }
            }
            if (UsrRepetido){
                usuarios.remove(i);
                i--;
            }
        }
    }
    
    /**
     * retorna true si el usuario de cedula Cedula existe en la base de datos
     * @param Cedula
     * @return 
     */
    public boolean ExisteUsuario(int Cedula){
        return mUsr.ExisteUsuario(Cedula);
    }
    
}
