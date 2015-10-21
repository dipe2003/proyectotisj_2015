
package interfaz.curso;

import interfaz.*;
import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import Curso.Clase.FacadeClase;
import Curso.FacadeCurso;
import Estudiante.Estudiante;
import Estudiante.FacadeEstudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class UsuariosClase implements Serializable{
    
    private int idCurso;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FacadeCurso fCurso;
    
    @EJB
    private FacadeClase fClase;
    
    private String TemaClase;
    
    private List<Usuario> Usuarios;
    private Map<Integer, Boolean> listChecked;
    private Date FechaClase;
    private String strFechaClase;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public String getTemaClase() {return TemaClase;}
    public Date getFechaClase() {return FechaClase;}
    public String getStrFechaClase() {
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaClase == null) {
            return this.strFechaClase;
        }else{
            return fDate.format(FechaClase);
        }
    }
    public int getAnioCurso(){
        return fCurso.BuscarCurso(idCurso).getAnioCurso();
    }
    public String getNombreCurso(){
        return fCurso.BuscarCurso(idCurso).getAsignaturaCurso().getNombreAsignatura();
    }
    public int getClasesDictadas(){
        return fCurso.GetCantidadClasesCurso(idCurso);
    }
    
    //  Setters
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setTemaClase(String TemaClase) {this.TemaClase = TemaClase;}
    public void setFechaClase(Date FechaClase) {this.FechaClase = FechaClase;}
    public void setStrFechaClase(String strFechaClase) {
        Date fecha = new Date(strFechaClase);
        this.strFechaClase = strFechaClase;
        this.FechaClase = fecha;
    }
    
    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        idCurso = Integer.valueOf(request.getParameter("idCurso"));
        this.Usuarios = new ArrayList<>();
        this.Usuarios = fUsr.listarUsuarioEstudianteCurso(idCurso);
        
        listChecked = new HashMap<>();
        for (Usuario Usr : Usuarios) {
            listChecked.put(Usr.getIdUsuario(), Boolean.FALSE);
        }
        
    }
    
    public int inasistenciaEstudiante(int idEstudiante){
        return fCurso.GetInanistenciasEstudianteCurso(idEstudiante, idCurso);
    }
    
    public void registrarClase(){
        List<Usuario> CheckedUsers = this.getCheckedUsers();
        int idClase = fClase.RegistarClase(FechaClase, TemaClase, idCurso );
        if (idClase!=-1) {
            for (int i = 0; i < CheckedUsers.size(); i++) {
                fClase.RegistrarAsistenciaEstudiante((Estudiante)CheckedUsers.get(i), idClase);
            }
        }
        
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
