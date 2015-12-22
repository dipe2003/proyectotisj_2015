
package interfaz.curso;

import Asignatura.Curso.Clase.FacadeClase;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.Estudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
@ManagedBean
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
    
    private boolean Correcto;
    
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
    public int getAnioCurso(){return fCurso.BuscarCurso(idCurso).getAnioCurso();}
    public String getNombreCurso(){return fCurso.BuscarCurso(idCurso).getAsignaturaCurso().getNombreAsignatura();}
    public int getClasesDictadas(){return fCurso.GetCantidadClasesCurso(idCurso);}
    public boolean isCorrecto() {return Correcto;}
    public int getIdCurso() {return idCurso;}
    
    //  Setters
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setTemaClase(String TemaClase) {this.TemaClase = TemaClase;}
    public void setFechaClase(Date FechaClase) {this.FechaClase = FechaClase;}
    public void setStrFechaClase(String strFechaClase) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaClase));
        }catch(ParseException ex){}
        this.strFechaClase = strFechaClase;
        this.FechaClase = cal.getTime();
    }
    public void setCorrecto(boolean Correcto) {this.Correcto = Correcto;}
    public void setIdCurso(int idCurso) {this.idCurso = idCurso;}
    
    @PostConstruct
    public void Init() {
        Correcto = false;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        idCurso = Integer.valueOf(request.getParameter("idCurso"));
        this.Usuarios = new ArrayList<>();
        this.Usuarios = fUsr.listarUsuarioEstudianteCurso(idCurso);
        
        listChecked = new HashMap<>();
        for (Usuario Usr : Usuarios) {
            listChecked.put(Usr.getIdUsuario(), Boolean.TRUE);
        }
        
    }
    
    public int inasistenciaEstudiante(int idEstudiante){
        return fCurso.GetInanistenciasEstudianteCurso(idEstudiante, idCurso);
    }
    
    public void registrarClase() throws IOException{
            List<Usuario> CheckedUsers = this.getCheckedUsers();
            int idClase = fClase.RegistarClase(FechaClase, TemaClase, idCurso );
            if (idClase!=-1) {
                for (int i = 0; i < CheckedUsers.size(); i++) {
                    fClase.RegistrarAsistenciaEstudiante(((Estudiante)CheckedUsers.get(i)).getIdUsuario(), idClase);
                }
                Correcto = true;
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("../Curso/ListarClases.xhtml?opt="+idCurso);
            context.responseComplete();
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
    
    /**
     * Comprueba si ya se dicto la clase en la fecha indicada.
     * No utilizar si se contempla el caso de recuperacion de clases. Por lo que se podria dictar una clase de ese curso un dia repetido.
     * @param event 
     */
    public void comprobarFecha(AjaxBehaviorEvent event){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date fecha = sdf.parse(this.strFechaClase);
            if (fClase.ExisteFechaClase(idCurso,fecha)) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ya existe clase en esta fecha");
                FacesContext.getCurrentInstance().addMessage("frmIngresoDatosClase:msjFechaClase", fm);
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(ParseException ex){}
    }
}
