
package Docente;

import Enumerados.EstadoCivil.EstadoCivil;
import Enumerados.FacadeEnumerados;
import Usuario.Docente.Docente;
import Usuario.Docente.FacadeDocente;
import Usuario.Estudiante.EnumSexo;
import Usuario.FacadeUsuario;
import Utilidades.Cedula;
import interfaz.FileUpload;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Named
@ViewScoped
@ManagedBean
public class EditarDocente implements Serializable{
    
    private String NombreUsuario;
    private String ApellidoUsuario;
    private String CorreoUsuario;
    private String PasswordUsuario;
    private String ImagenUsuario;
    private String CedulaUsuario;
    private String CredencialCivicaUsuario;
    private String DomicilioUsuario;
    private String DepartamentoUsuario;
    private String LocalidadUsuario;
    private String TelefonoUsuario;
    private String CelularUsuario;
    private EstadoCivil EstadoCivilUsuario;
    private Date FechaNacimientoUsuario;
    private String LugarNacimientoUsuario;
    private String SexoSeleccionado;
    private List<String> ListaSexo;
    private String Rol;
    private Part PartImagenPerfil;
    private List<String> ListEstadoCivil;
    private String EstadoCivilSeleccionado;
    private EnumSexo EnumSexoSeleccionado;
    private String strFechaNacimiento;
    private int IdUsuario;
    
    private boolean Correcto;
    
    @EJB
    private FacadeUsuario fUsr;
    @EJB
    private FacadeDocente fDoc;
    @EJB
    private FileUpload fUp;
    
    @EJB
    private Cedula verifCedula;
    
    @EJB
    private FacadeEnumerados fEnum;
    
    //  Getters
    public String getNombreUsuario() {return NombreUsuario;}
    public String getApellidoUsuario() {return ApellidoUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public String getImagenUsuario() {return ImagenUsuario;}
    public String getCedulaUsuario() {return CedulaUsuario;}
    public String getCredencialCivicaUsuario() {return CredencialCivicaUsuario;}
    public String getDomicilioUsuario() {return DomicilioUsuario;}
    public String getDepartamentoUsuario() {return DepartamentoUsuario;}
    public String getLocalidadUsuario() {return LocalidadUsuario;}
    public String getTelefonoUsuario() {return TelefonoUsuario;}
    public String getCelularUsuario() {return CelularUsuario;}
    public EstadoCivil getEstadoCivilUsuario() {return EstadoCivilUsuario;}
    public Date getFechaNacimientoUsuario() {return FechaNacimientoUsuario;}
    public String getLugarNacimientoUsuario() {return LugarNacimientoUsuario;}
    public String getSexoSeleccionado() {return SexoSeleccionado;}
    public List<String> getListaSexo(){return this.ListaSexo;}
    public String getRol() {return Rol;}
    public List<String> getListEstadoCivil() {return this.ListEstadoCivil;}
    public String getEstadoCivilSeleccionado() {return this.EstadoCivilSeleccionado;}
    public Part getPartImagenPerfil() {return PartImagenPerfil;}
    public String getStrFechaNacimiento(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaNacimientoUsuario == null) {
            return this.strFechaNacimiento;
        }else{
            return fDate.format(FechaNacimientoUsuario);
        }
    }
    public int getIdUsuario() {return IdUsuario;}
    
    
    /*  validacion  */
    public boolean isCorrecto(){return this.Correcto;}
    
    //  Setters
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    public void setApellidoUsuario(String ApellidoUsuario) {this.ApellidoUsuario = ApellidoUsuario;}
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setImagenUsuario(String ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setCredencialCivicaUsuario(String CredencialCivicaUsuario) {this.CredencialCivicaUsuario = CredencialCivicaUsuario;}
    public void setDomicilioUsuario(String DomicilioUsuario) {this.DomicilioUsuario = DomicilioUsuario;}
    public void setDepartamentoUsuario(String DepartamentoUsuario) {this.DepartamentoUsuario = DepartamentoUsuario;}
    public void setLocalidadUsuario(String LocalidadUsuario) {this.LocalidadUsuario = LocalidadUsuario;}
    public void setTelefonoUsuario(String TelefonoUsuario) {this.TelefonoUsuario = TelefonoUsuario;}
    public void setCelularUsuario(String CelularUsuario) {this.CelularUsuario = CelularUsuario;}
    public void setEstadoCivilUsuario(EstadoCivil EstadoCivilUsuario) {this.EstadoCivilUsuario = EstadoCivilUsuario;}
    public void setFechaNacimientoUsuario(Date FechaNacimientoUsuario) {this.FechaNacimientoUsuario = FechaNacimientoUsuario;}
    public void setLugarNacimientoUsuario(String LugarNacimientoUsuario) {this.LugarNacimientoUsuario = LugarNacimientoUsuario;}
    public void setSexoSeleccionado(String SexoSeleccionado) {
        this.SexoSeleccionado = SexoSeleccionado;
        EnumSexoSeleccionado = EnumSexo.valueOf(SexoSeleccionado);
    }
    public void setListaSexo(List<String> ListaSexo){this.ListaSexo = ListaSexo;}
    public void setRol(String Rol) {this.Rol = Rol;}
    public void setListEstadoCivil(List<String> ListEstadoCivil) {this.ListEstadoCivil = ListEstadoCivil;}
    public void setEstadoCivilSeleccionado(String EstadoCivilSeleccionado) {
        this.EstadoCivilSeleccionado = EstadoCivilSeleccionado;
        this.EstadoCivilUsuario = getEstadoCivilPorNombre(EstadoCivilSeleccionado);
    }
    public void setPartImagenPerfil(Part PartImagenPerfil) {this.PartImagenPerfil = PartImagenPerfil;}
    public void setStrFechaNacimiento(String strFechaNacimiento) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaNacimiento));
        }catch(ParseException ex){}
        this.strFechaNacimiento = strFechaNacimiento;
        this.FechaNacimientoUsuario = cal.getTime();
    }
    public void setCorrecto(boolean Correcto) {this.Correcto = Correcto;}
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    
    /**
     * Comprueba que la cedula sea valida.
     * @param Cedula
     */
    public void comprobarCedula(String Cedula){
        if (!verifCedula.EsCedulaValida(Cedula)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
        }else{
            if (fUsr.ExisteUsuario(Cedula)) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula ya esta registrada.");
                FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
            }
        }
        this.CedulaUsuario = Cedula;
    }
    
   
    private void guardarDatos(){
        
        Correcto=true;
    }
    
    /**
     * Busca el estado civil segun su nombre.
     * @param NombreEstadoCivil
     * @return
     */
    private EstadoCivil getEstadoCivilPorNombre(String NombreEstadoCivil){
        List<EstadoCivil> lstEstadoCivil =fEnum.ListarEstadosCiviles();
        for (int i = 0; i < lstEstadoCivil.size(); i++) {
            if (lstEstadoCivil.get(i).getEstadoCivil().equals(NombreEstadoCivil)) {
                return lstEstadoCivil.get(i);
            }
        }
        return null;
    }
    
    /**
     * Se piden los datos de los estados civiles registrados y se llena la lista para utilizarse desde la pagina.
     * Se piden los datos de sexos registrados y se llena la lista para utilizarse desde la pagina.
     * Se piden los datos de los tipos de estudios registrados y se llena la lista con la inner class EstudioCursado para utilizarse desde la pagina.
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        //  Estado Civil
        List<EstadoCivil> lstEstadoCivil = fEnum.ListarEstadosCiviles();
        this.ListEstadoCivil = new ArrayList<>();
        for (int i = 0; i < lstEstadoCivil.size(); i++) {
            this.ListEstadoCivil.add(lstEstadoCivil.get(i).getEstadoCivil());
        }
        //  Sexo
        this.ListaSexo = new ArrayList<>();
        for (int i = 0; i < EnumSexo.values().length; i++) {
            this.ListaSexo.add(EnumSexo.values()[i].toString());
        }
        
        try{
            IdUsuario = Integer.parseInt(request.getParameter("id"));
            Docente doc = fDoc.GetDocente(IdUsuario);
            NombreUsuario = doc.getNombreUsuario();
            ApellidoUsuario = doc.getApellidoUsuario();
            CorreoUsuario = doc.getCorreoUsuario();
            
            ImagenUsuario = doc.getImagenUsuario();
            CedulaUsuario = String.valueOf(doc.getCedulaUsuario());
            CredencialCivicaUsuario = doc.getCredencialCivicaUsuario();
            DomicilioUsuario = doc.getDomicilioUsuario();
            DepartamentoUsuario = doc.getDepartamentoUsuario();
            LocalidadUsuario = doc.getLocalidadUsuario();
            TelefonoUsuario = doc.getTelefonoUsuario();
            CelularUsuario = doc.getCelularUsuario();
            EstadoCivilUsuario = doc.getEstadoCivilUsuario();
            FechaNacimientoUsuario = doc.getFechaNacimientoUsuario();
            LugarNacimientoUsuario = doc.getLugarNacimientoUsuario();
            EnumSexoSeleccionado = doc.getSexoUsuario();
            this.setEstadoCivilSeleccionado(doc.getEstadoCivilUsuario().getEstadoCivil()); 
            this.setSexoSeleccionado(doc.getSexoUsuario().toString());
        }catch(NullPointerException ex){}
                this.Correcto = false;
    }
}

