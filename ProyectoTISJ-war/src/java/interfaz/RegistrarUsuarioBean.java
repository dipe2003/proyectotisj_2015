
package interfaz;

import Enumerados.EstadoCivil.EstadoCivil;
import Enumerados.FacadeEnumerados;
import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Estudiante.EnumSexo;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.FacadeUsuario;
import Utilidades.Cedula;
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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Named
@ViewScoped
@ManagedBean
public class RegistrarUsuarioBean implements Serializable{
    
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
    private Part PartImagenFormInscripcion;
    private int GeneracionAnioEstudiante;
    private Part PartImagenPerfil;
    private List<String> ListEstadoCivil;
    private String EstadoCivilSeleccionado;
    private EnumSexo EnumSexoSeleccionado;
    private String strFechaNacimiento;
    
    // id solo con valor distinto de 0 cuando se esta registrando un nuevo rol
    private int IdUsuario;

    /**
     * Lista de EstudiosCursados para utilizarse desde la pagina para registrar los estudios del estudiante.
     */
    private static List<EstudioCursado> ListaEstudiosCursados;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FacadeEstudiante fEst;
    
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
    
    public List<EstudioCursado> getListaEstudiosCursados() {return ListaEstudiosCursados;}
    
    /*  Solo Estudiante   */
    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}
    public int getGeneracionAnioEstudiante() {return GeneracionAnioEstudiante;}
    
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
    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
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
    public void setGeneracionAnioEstudiante(int GeneracionAnioEstudiante) {this.GeneracionAnioEstudiante = GeneracionAnioEstudiante;}
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    
    /**
     * Comprueba que la cedula sea valida.
     * @param Cedula
     */
    public void comprobarCedula(String Cedula){
        if (!verifCedula.EsCedulaValida(Cedula)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            if (fUsr.ExisteUsuario(Cedula)) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula ya esta registrada.");
                FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
                FacesContext.getCurrentInstance().renderResponse();
            }
        }
        this.CedulaUsuario = Cedula;
    }
    
    /**
     * Comprueba que se haya seleccionado el formulario de inscripcion del estudiante.
     */
    public void comprobarFormularioInscripcion(){
        if (this.Rol.equals("Estudiante")) {
            FacesContext context = FacesContext.getCurrentInstance();
            if (this.PartImagenFormInscripcion == null) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono formulario de inscripcion.");
                context.addMessage("frmIngresoDatos:inputFormIngreso", fm);
                context.renderResponse();
            }
        }
    }
    
    /**
     * Registra el usuario si este no esta ya registrado
     * @throws IOException
     */
    public void registrarUsuarioInterfaz() throws IOException {
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if (fUsr.ExisteUsuario(CedulaUsuario)){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula ya esta registrada.");
            FacesContext.getCurrentInstance().addMessage("frmIngresoDatos:inputCedula", fm);
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            if(registrarUsuario()!=-1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/Usuario/ListarUsuarios.xhtml?rol=Estudiante");
            }
        }
    }
    
    /**
     * Registra un usuario segun el rol seleccionado.
     * Sino se selecciono imagen de perfil se registra con imagen por defecto.
     * [ESTUDIANTE] Sino se selecciono formulario de inscripcion no se registra.
     * @return 
     * @throws IOException
     *
     */
    public int registrarUsuario() throws IOException{
        int idUsr = -1;
        FacesContext context = FacesContext.getCurrentInstance();
        if (verifCedula.EsCedulaValida(CedulaUsuario)) {
            String ubicacionPerfil = fUp.guardarArchivo("ImagenesPerfil", PartImagenPerfil, CedulaUsuario);
            if (Rol.equals("Estudiante")) {
                String ubicacionFrmInscripcion = fUp.guardarArchivo("frmInscripcion", PartImagenFormInscripcion, String.valueOf(CedulaUsuario));
                if (ubicacionFrmInscripcion!=null) {
                    if (!ubicacionPerfil.equals("no")) {
                        if ((idUsr =fUsr.RegistrarUsuario(ubicacionFrmInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ubicacionPerfil, Integer.valueOf(CedulaUsuario),
                                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                                FechaNacimientoUsuario, LugarNacimientoUsuario, EnumSexoSeleccionado, GeneracionAnioEstudiante))!=-1) {
                            for (int i = 0; i < ListaEstudiosCursados.size(); i++) {
                                if (!ListaEstudiosCursados.get(i).OrientacionEstudio.equals("")) {
                                    fEst.agregarEstudiosEstudiante(ListaEstudiosCursados.get(i).IdEstudio, ListaEstudiosCursados.get(i).OrientacionEstudio, idUsr);
                                }
                            }
                            return idUsr;
                        }
                    }else{
                        if ((idUsr = fUsr.RegistrarUsuario(ubicacionFrmInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, "", Integer.valueOf(CedulaUsuario),
                                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario,
                                FechaNacimientoUsuario, LugarNacimientoUsuario, EnumSexoSeleccionado, GeneracionAnioEstudiante))!=-1) {
                            for (int i = 0; i < ListaEstudiosCursados.size(); i++) {
                                if (!ListaEstudiosCursados.get(i).OrientacionEstudio.equals("")) {
                                    fEst.agregarEstudiosEstudiante(ListaEstudiosCursados.get(i).IdEstudio, ListaEstudiosCursados.get(i).OrientacionEstudio, idUsr);
                                }
                            }
                            return idUsr;
                        }
                    }
                }else{
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono formulario de inscripcion");
                    context.addMessage("frmIngresoDatos:inputFormIngreso", fm);                    
                    return -1;
                }
            }else{
                if (!ubicacionPerfil.equals("no")) {
                    if((idUsr=fUsr.RegistrarUsuario(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ubicacionPerfil, Integer.valueOf(CedulaUsuario),
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CedulaUsuario, EstadoCivilUsuario,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, EnumSexoSeleccionado, Rol, IdUsuario))!=-1){
                        return idUsr;
                    }
                }else{
                    if((idUsr =fUsr.RegistrarUsuario(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, "", Integer.valueOf(CedulaUsuario),
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CedulaUsuario, EstadoCivilUsuario,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, EnumSexoSeleccionado, Rol, IdUsuario))!=-1){
                        return idUsr;
                    }
                }
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            context.addMessage("frmIngresoDatos:inputCedula", fm);
            
        }
        return idUsr;
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
        Rol = request.getParameter("rol");
        
        //  Estado Civil
        List<EstadoCivil> lstEstadoCivil = fEnum.ListarEstadosCiviles();
        this.ListEstadoCivil = new ArrayList<>();
        for (int i = 0; i < lstEstadoCivil.size(); i++) {
            this.ListEstadoCivil.add(lstEstadoCivil.get(i).getEstadoCivil());
        }
        this.EstadoCivilSeleccionado = this.ListEstadoCivil.get(0);
        
        //  Sexo
        this.ListaSexo = new ArrayList<>();
        for (int i = 0; i < EnumSexo.values().length; i++) {
            this.ListaSexo.add(EnumSexo.values()[i].toString());
        }
        this.SexoSeleccionado = ListaSexo.get(0);
        
        //  Estudios
        ListaEstudiosCursados = new ArrayList<>();
        List<TipoEstudio> lstTipoEstudios = fEnum.ListarTiposDeEstudios();
        if (ListaEstudiosCursados.isEmpty()) {
            for (int i = 0; i < lstTipoEstudios.size(); i++) {
                ListaEstudiosCursados.add(new EstudioCursado(lstTipoEstudios.get(i).getIdTipoEstudio(), lstTipoEstudios.get(i).getTipoDeEstudio(),""));
            }
        }
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

