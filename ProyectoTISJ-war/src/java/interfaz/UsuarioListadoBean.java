
package interfaz;

import Enumerados.FacadeEnumerados;
import Estudiante.Estudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class UsuarioListadoBean implements Serializable{
    private String Rol;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FacadeEnumerados fEnum;
    
    private List<Usuario> Usuarios;
    private Usuario UsuarioSeleccionado;
    
    /**
     * Lista de estudiantes para visualizar datos particulares de la clase estudiante
     */
    private List<Estudiante> Estudiantes;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Usuario getUsuarioSeleccionado() {return this.UsuarioSeleccionado;}
    public String getRol() {return Rol;}
    public List<Estudiante> getEstudiantes(){return this.Estudiantes;}
    
    //  Setters
    public void setRol(String Rol){this.Rol = Rol;}
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setUsuarioSeleccionado(Usuario UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public void setEstudiantes(List<Estudiante> Estudiantes){this.Estudiantes= Estudiantes;}
    
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
        Rol = request.getParameter("rol");
        this.Usuarios = fUsr.listarUsuarios(Rol);
        
        //  Estudiantes
        this.Estudiantes = new ArrayList<>();
        if (Rol.equals("Estudiante")) {
            for (int i = 0; i < Usuarios.size(); i++) {
                if (Usuarios.get(i) instanceof Estudiante) {
                    this.Estudiantes.add((Estudiante) (Usuarios.get(i)));
                }
            }
        }
        
        
    }
    
}
