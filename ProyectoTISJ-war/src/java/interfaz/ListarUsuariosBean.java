
package interfaz;

import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarUsuariosBean implements Serializable{
    
    private String Rol;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @Inject
    private RegistrarUsuarioBean UsrData;
    
    private List<Usuario> Usuarios;
    private Usuario UsuarioSeleccionado;
    private Map<Integer, Boolean> listChecked;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Usuario getUsuarioSeleccionado() {return this.UsuarioSeleccionado;}
    public String getRol() {return Rol;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}

    //  Setters
    public void setRol(String Rol){this.Rol = Rol;}
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setUsuarioSeleccionado(Usuario UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
 
    
    /**
     * Se piden los usuario segun el rol indicado en el parametro.
     * Se piden los datos de los estados civiles registrados y se llena la lista para utilizarse desde la pagina.
     * Se piden los datos de sexos registrados y se llena la lista para utilizarse desde la pagina.
     * Si el rol seleccionado es estudiante se piden los estudiantes para poder visualizar la informacion particular de esta clase.
     */
    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        //  Roles
        if (Rol==null || Rol.isEmpty()) {
            Rol = request.getParameter("rol");
        }
        this.Usuarios = new ArrayList<>();
        String currentURL = context.getViewRoot().getViewId();
        if (currentURL.equals("/Usuario/ListarUsuarios.xhtml")){
            this.Usuarios = fUsr.listarUsuarios(Rol);
        }else if(currentURL.equals("/Usuario/SeleccionarUsuarios.xhtml")){
            this.Usuarios = fUsr.listarUsuariosSinRol(Rol);
        }
        listChecked = new HashMap<>();
        for (Usuario Usr : Usuarios) {
            listChecked.put(Usr.getIdUsuario(), Boolean.FALSE);
        }
        
    }
    
    /**
     * Retorna la lista con los usuarios seleccionados
     * @return Retorna la lista vacia en caso de ninguno seleccionado
     */
    private  List<Usuario> getCheckedUsers(){
        List<Usuario> CheckedUsers = new ArrayList<>();
        for (Map.Entry e : listChecked.entrySet()) {
            boolean valor = (boolean)e.getValue();
            int Key = (int) e.getKey();
            if ( valor ) CheckedUsers.add(this.findUser(Key));
        }
        return CheckedUsers;
    }
    
    /**
     * Busca un usuario por id en la lista Usuarios
     * @param id
     * @return retorna null en caso contrario
     */
    private Usuario findUser(int id){
        for (Usuario Usr : Usuarios) {
            if (Usr.getIdUsuario()==id) return Usr;
        }
        return null;
    }
    
    /**
     * Registra el nuevo rol al usuario usr
     * @param usr 
     */
    private void RegistrarRol(Usuario usr){
        UsrData.setNombreUsuario(usr.getNombreUsuario());
        UsrData.setApellidoUsuario(usr.getApellidoUsuario());
        UsrData.setCorreoUsuario(usr.getCorreoUsuario());
        UsrData.setPasswordUsuario(usr.getPasswordUsuario());
        UsrData.setImagenUsuario(usr.getImagenURL());
        UsrData.setCedulaUsuario(String.valueOf(usr.getCedulaUsuario()));
        UsrData.setCredencialCivicaUsuario(usr.getCredencialCivicaUsuario());
        UsrData.setDomicilioUsuario(usr.getDomicilioUsuario());
        UsrData.setDepartamentoUsuario(usr.getDepartamentoUsuario());
        UsrData.setLocalidadUsuario(usr.getLocalidadUsuario());
        UsrData.setTelefonoUsuario(usr.getTelefonoUsuario());
        UsrData.setCelularUsuario(usr.getCelularUsuario());
        UsrData.setEstadoCivilUsuario(usr.getEstadoCivilUsuario());
        UsrData.setFechaNacimientoUsuario(usr.getFechaNacimientoUsuario());
        UsrData.setLugarNacimientoUsuario(usr.getLugarNacimientoUsuario());
        UsrData.setSexoSeleccionado(usr.getSexoUsuario().name());
        UsrData.setRol(Rol);
        UsrData.registrarUsuario();
    }
    
    /**
     * Registra un nuevo Rol a los usuarios checkeados
     */
    public void nuevoRol() throws IOException{
        List<Usuario> CheckedUsers = this.getCheckedUsers();
        for (Usuario usr : CheckedUsers) {
            RegistrarRol(usr);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListarUsuarios.xhtml?rol="+Rol);
    }

}