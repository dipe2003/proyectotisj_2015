package Usuario;

import Usuario.Administrador.Administrador;
import Usuario.Administrativo.Administrativo;
import Usuario.Docente.Docente;
import Usuario.Estudiante.Estudiante;
import Utilidades.Seguridad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorUsuario {
    
    @EJB
    private ManejadorUsuario mUsr;
    @EJB
    private Seguridad cSeg;
        
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
        List<Usuario> Usuarios = mUsr.BuscarUsuarioPorCedula(Cedula);
        if (!Usuarios.isEmpty()) {
            for (int i = 0; i < Usuarios.size(); i++) {
                Usuario user = Usuarios.get(i);
                if (EsRol(user, Rol) && cSeg.getPasswordSeguro(Password, user.getSaltPasswordUsuario()).equals(user.getPasswordUsuario())) {
                    return user;
                }
            }
        }
        return null;
    }
    /**
     * Devuelve todos los roles del usuario que coincida con los datos especificados.
     * @param Cedula
     * @param Password
     * @return Devuelve al menos un rol si el usuario existe.
     */
    public List<String> ValidarUsuario(int Cedula, String Password){
        List<Usuario> Usuarios = mUsr.BuscarUsuarioPorCedula(Cedula);
        List<String> Roles = new ArrayList<>();
        if (!Usuarios.isEmpty()) {
            for (int i = 0; i < Usuarios.size(); i++) {
                Usuario user = Usuarios.get(i);
                if (cSeg.getPasswordSeguro(Password, user.getSaltPasswordUsuario()).equals(user.getPasswordUsuario())) {
                    Roles.add(getRol(user));
                }                
            }
        }
        return Roles;
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
     * Comprueba y devuelve el Rol del Usuario especificado
     * @param usuario
     * @return
     */
    private String getRol(Usuario usuario){
        if (usuario instanceof Administrador) {
            return "Administrador";
        }
        
        if (usuario instanceof Administrativo) {
            return "Administrativo";
        }
        
        if (usuario instanceof Docente) {
            return "Docente";
        }
        
        if (usuario instanceof Estudiante) {
            return "Estudiante";
        }
        return "";
    }
    
    /**
     * Quita todos los usuarios de cedula CI de la lista de usuarios
     * @param CI
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
