
package interfaz.curso;

import interfaz.*;
import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import Curso.FacadeCurso;
import Estudiante.FacadeEstudiante;
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
public class UsuariosClase implements Serializable{
    
    @EJB
    private FacadeUsuario fUsr;

    private String TemaClase;
    
    private List<Usuario> Usuarios;
    private Map<Integer, Boolean> listChecked;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public String getTemaClase() {return TemaClase;}
    
    //  Setters
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setTemaClase(String TemaClase) {this.TemaClase = TemaClase;}
    
    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        this.Usuarios = new ArrayList<>();
        this.Usuarios = fUsr.listarUsuarioEstudianteCurso(Integer.valueOf(request.getParameter("idCurso")));
        
        listChecked = new HashMap<>();
        for (Usuario Usr : Usuarios) {
            listChecked.put(Usr.getIdUsuario(), Boolean.FALSE);
        }
        
    }
    
   
    public void registrarInasistencia(){
        List<Usuario> CheckedUsers = this.getCheckedUsers();
        
    }
    
    
    
    private  List<Usuario> getCheckedUsers(){
        List<Usuario> CheckedUsers = new ArrayList<>();
        for (Map.Entry e : listChecked.entrySet()) {
            boolean valor = (boolean)e.getValue();
            int Key = (int) e.getKey();
            if ( valor ) CheckedUsers.add(this.findUser(Key));
        }
        return CheckedUsers;
    }
    
    private Usuario findUser(int id){
        for (Usuario Usr : Usuarios) {
            if (Usr.getIdUsuario()==id) return Usr;
        }
        return null;
    }
}
