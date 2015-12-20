
package interfaz;

import Enumerados.EstadoCivil.EstadoCivil;
import Enumerados.FacadeEnumerados;
import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Administrador.Administrador;
import Usuario.Administrativo.Administrativo;
import Usuario.Docente.Docente;
import Usuario.Estudiante.EnumSexo;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.Estudios.Estudio;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import Utilidades.Cedula;
import Utilidades.Seguridad;
import java.io.IOException;
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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Named
@ViewScoped
public class EditarPerfil implements Serializable{
    
    private Usuario usuario;
    private String NombreUsuario;
    private String ApellidoUsuario;
    private String CorreoUsuario;
    private String PasswordUsuario;
    private String SaltPasswordUsuario;
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
    
    /*
    *   Estudiante  *
    */
    private int GeneracionEstudiante;
    private static List<EstudioCursado> ListaEstudiosCursados;
    
    /*
    *   Cambio de password  *
    */
    private String PasswordActual;
    private String PasswordNuevo;
    
    /*
    *   Redireccion
    */
    private boolean exito;
    
    @EJB
    private FacadeUsuario fUsr;
    @Inject
    private UsuarioLogueadoBean login;
    @EJB
    private Cedula verifCedula;
    @EJB
    private Seguridad cSeg;
    @EJB
    private FacadeEnumerados fEnum;
    @EJB
    private FacadeEstudiante fEst;
    @EJB
    private FileUpload fUp;
    
    //  Getters
    public String getNombreUsuario() {return NombreUsuario;}
    public String getApellidoUsuario() {return ApellidoUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public String getSaltPasswordUsuario() {return SaltPasswordUsuario;}
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
    public EnumSexo getEnumSexoSeleccionado() {return EnumSexoSeleccionado;}
    public int getIdUsuario() {return IdUsuario;}
    public int getGeneracionEstudiante() {return GeneracionEstudiante;}
    public List<EstudioCursado> getListaEstudiosCursados() {return ListaEstudiosCursados;}
    
    public String getPasswordActual() {return PasswordActual;}
    public String getPasswordNuevo() {return PasswordNuevo;}
    public boolean isExito() {return exito;}
    
    //  Setters
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    public void setApellidoUsuario(String ApellidoUsuario) {this.ApellidoUsuario = ApellidoUsuario;}
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setSaltPasswordUsuario(String SaltPasswordUsuario) {this.SaltPasswordUsuario = SaltPasswordUsuario;}
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
    public void setEnumSexoSeleccionado(EnumSexo EnumSexoSeleccionado) {this.EnumSexoSeleccionado = EnumSexoSeleccionado;}
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
    public void setPasswordActual(String PasswordActual) {this.PasswordActual = PasswordActual;}
    public void setPasswordNuevo(String PasswordNuevo) {this.PasswordNuevo = PasswordNuevo;}
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    public void setGeneracionEstudiante(int GeneracionEstudiante) {this.GeneracionEstudiante = GeneracionEstudiante;}
    public void setListaEstudiosCursados(List<EstudioCursado> ListaEstudiosCursados) {EditarPerfil.ListaEstudiosCursados = ListaEstudiosCursados;}
    public void setExito(boolean exito) {this.exito = exito;}
    
    /**
     * Comprueba que la cedula sea valida.
     * @return
     */
    public boolean comprobarCedula(){
        if (!verifCedula.EsCedulaValida(this.CedulaUsuario)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
            return false;
        }
        return true;
    }
    
    public void guardarDatos() throws IOException{
        int ok = -1;
        if(comprobarCedula()){
            Usuario user = null;
            switch(Rol){
                case "Administrador":
                    user = new Administrador();
                    break;
                case "Administrativo":
                    user = new Administrativo();
                    break;
                case "Docente":
                    user = new Docente();
                    break;
                case "Estudiante":
                    user = new Estudiante();
                    break;
            }
            try{
                user.setApellidoUsuario(ApellidoUsuario);
                user.setCedulaUsuario(Integer.parseInt(CedulaUsuario));
                user.setCelularUsuario(CelularUsuario);
                user.setCorreoUsuario(CorreoUsuario);
                user.setCredencialCivicaUsuario(CredencialCivicaUsuario);
                user.setDepartamentoUsuario(DepartamentoUsuario);
                user.setDomicilioUsuario(DomicilioUsuario);
                user.setEstadoCivilUsuario(EstadoCivilUsuario);
                user.setFechaNacimientoUsuario(FechaNacimientoUsuario);
                user.setIdUsuario(IdUsuario);
                if(PartImagenPerfil!=null){
                    ImagenUsuario = fUp.guardarArchivo("ImagenesPerfil", PartImagenPerfil, CedulaUsuario);
                }
                user.setImagenUsuario(ImagenUsuario);
                user.setLocalidadUsuario(LocalidadUsuario);
                user.setLugarNacimientoUsuario(LugarNacimientoUsuario);
                user.setNombreUsuario(NombreUsuario);
                user.setSexoUsuario(EnumSexoSeleccionado);
                user.setTelefonoUsuario(TelefonoUsuario);
                if(Rol.equals("Estudiante")) {
                    ((Estudiante)user).setGeneracionAnioEstudiante(GeneracionEstudiante);
                }
                
                if(comprobarPassword()){
                    user.setPasswordUsuario(PasswordUsuario);
                    user.setSaltPasswordUsuario(SaltPasswordUsuario);
                    if((ok=fUsr.ModificarUsuario(user))!=-1){
                        if(Rol.equals("Estudiante")) {
                            for (int i = 0; i < ListaEstudiosCursados.size(); i++) {
                                fEst.actualizarEstudioEstudiante(ListaEstudiosCursados.get(i).IdEstudio, ListaEstudiosCursados.get(i).OrientacionEstudio, user.getIdUsuario());
                            }
                        }
                        FacesContext context = FacesContext.getCurrentInstance();
                        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                        Usuario  User = (Usuario)request.getSession().getAttribute("Usuario");
                        if(User.getIdUsuario()== user.getIdUsuario()){
                            login.modificarUsuarioLogueado(user);
                        }
                    }
                }
            }catch(NullPointerException ex){}
        }
        if(ok!=-1) {
            exito = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/Views/index.xhtml");
        }
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
    
    public boolean comprobarPassword(){
        if(PasswordActual.isEmpty() && PasswordNuevo.isEmpty()){
            PasswordUsuario = usuario.getPasswordUsuario();
            SaltPasswordUsuario = usuario.getSaltPasswordUsuario();
            return true;
        }else{
            if(PasswordActual.isEmpty() | PasswordNuevo.isEmpty()){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Falta ingresar un password");
                FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputPassNuevo", fm);
            }else{
                String keyDocente = usuario.getSaltPasswordUsuario();
                String passDocente = usuario.getPasswordUsuario();
                String passEditar = cSeg.getPasswordSeguro(PasswordActual, keyDocente);
                if(!passEditar.equals(passDocente)){
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El password ingresado no es correcto");
                    FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputPassActual", fm);
                }else{
                    String[] nuevoPass = cSeg.getPasswordSeguro(PasswordNuevo);
                    PasswordUsuario = nuevoPass[1];
                    SaltPasswordUsuario = nuevoPass[0];
                    return true;
                }
            }
        }
        return false;
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
        exito = false;
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
            Rol = request.getParameter("Rol");
            usuario = fUsr.BuscarUsuario(IdUsuario);
            NombreUsuario = usuario.getNombreUsuario();
            ApellidoUsuario = usuario.getApellidoUsuario();
            CorreoUsuario = usuario.getCorreoUsuario();
            
            ImagenUsuario = usuario.getImagenUsuario();
            CedulaUsuario = String.valueOf(usuario.getCedulaUsuario());
            CredencialCivicaUsuario = usuario.getCredencialCivicaUsuario();
            DomicilioUsuario = usuario.getDomicilioUsuario();
            DepartamentoUsuario = usuario.getDepartamentoUsuario();
            LocalidadUsuario = usuario.getLocalidadUsuario();
            TelefonoUsuario = usuario.getTelefonoUsuario();
            CelularUsuario = usuario.getCelularUsuario();
            EstadoCivilUsuario = usuario.getEstadoCivilUsuario();
            FechaNacimientoUsuario = usuario.getFechaNacimientoUsuario();
            LugarNacimientoUsuario = usuario.getLugarNacimientoUsuario();
            EnumSexoSeleccionado = usuario.getSexoUsuario();
            this.setEstadoCivilSeleccionado(usuario.getEstadoCivilUsuario().getEstadoCivil());
            this.setSexoSeleccionado(usuario.getSexoUsuario().toString());
            if(Rol.equals("Estudiante")) {
                GeneracionEstudiante = ((Estudiante)usuario).getGeneracionAnioEstudiante();
                ListaEstudiosCursados = new ArrayList<>();
                List<TipoEstudio> lstTipoEstudios = fEnum.ListarTiposDeEstudios();
                if (ListaEstudiosCursados.isEmpty()) {
                    for (int i = 0; i < lstTipoEstudios.size(); i++) {
                        ListaEstudiosCursados.add(new EstudioCursado(lstTipoEstudios.get(i).getIdTipoEstudio(), lstTipoEstudios.get(i).getTipoDeEstudio(),""));
                    }
                }
                for (int i = 0; i < ((Estudiante)usuario).getEstudiosCursadosEstudiante().size(); i++) {
                    Estudio estudio = ((Estudiante)usuario).getEstudiosCursadosEstudiante().get(i);
                    for (int j = 0; j < ListaEstudiosCursados.size(); j++) {
                        if(ListaEstudiosCursados.get(j).getTipoEstudio().equals(estudio.getTipoDeEstudio().getTipoDeEstudio())){
                            EstudioCursado est = new EstudioCursado(estudio.getTipoDeEstudio().getIdTipoEstudio(), estudio.getTipoDeEstudio().getTipoDeEstudio(), estudio.getOrientacionEstudio());
                            ListaEstudiosCursados.remove(j);
                            ListaEstudiosCursados.add(est);
                        }
                    }
                }
            }
            
        }catch(NullPointerException ex){}
    }
    
    /**
     * clase para representar los estudios cursados por el estudiante.
     */
    public static class EstudioCursado {
        private int IdEstudio;
        private String TipoEstudio;
        private String OrientacionEstudio;
        
        public EstudioCursado(int IdEstudio, String TipoEstudio, String OrientacionEstudio) {
            this.IdEstudio = IdEstudio;
            this.TipoEstudio = TipoEstudio;
            this.OrientacionEstudio = OrientacionEstudio;
        }
        
        public EstudioCursado() {}
        
        //  Setters
        
        public void setIdEstudio(int IdEstudio) {this.IdEstudio = IdEstudio;}
        public void setTipoEstudio(String TipoEstudio) {this.TipoEstudio = TipoEstudio;}
        public void setOrientacionEstudio(String OrientacionEstudio) {this.OrientacionEstudio = OrientacionEstudio;}
        
        // Getters
        
        public int getIdEstudio() {return IdEstudio;}
        public String getTipoEstudio() {return TipoEstudio;}
        public String getOrientacionEstudio() {return OrientacionEstudio;}
        
    }
    
}

