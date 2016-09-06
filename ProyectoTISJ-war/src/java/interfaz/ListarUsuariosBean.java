
package interfaz;

import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.Estudios.Estudio;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    
    @EJB
    private FacadeEstudiante fEst;
    
    @EJB
    private FacadeAsignatura fAsig;
    
    @EJB
    private FacadeCurso fCurso;
    
    @Inject
    private RegistrarUsuarioBean UsrData;
    
    private List<Usuario> Usuarios;
    private Map<String, Usuario> MapUsuarios;
    private List<Usuario> UsuariosFiltrados;
    private Map<String, Usuario> MapUsuariosFiltrados;
    private Usuario UsuarioSeleccionado;
    private Map<Integer, Boolean> listChecked;
    private String Opt;
    private int IdOpt;
    private List<String> AniosCursos;
    private Map<String, Integer> AsignaturasCursos;
    private Map<Integer, String> EstudiosCursadosEstudiante;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Map<String, Usuario> getMapUsuarios() {return MapUsuarios;}
    public Usuario getUsuarioSeleccionado() {return this.UsuarioSeleccionado;}
    public String getRol() {return Rol;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public String getOpt(){return this.Opt;}
    public List<Usuario> getUsuariosFiltrados() {return UsuariosFiltrados;}
    public Map<String, Usuario> getMapUsuariosFiltrados() {return MapUsuariosFiltrados;}
    public List<String> getAniosCursos() {return AniosCursos;}
    public Map<String, Integer> getAsignaturasCursos() {return AsignaturasCursos;}
    public int getIdOpt() {return IdOpt;}
    public Map<Integer, String> getEstudiosCursadosEstudiante() {return EstudiosCursadosEstudiante;}
    
    //  Setters
    public void setRol(String Rol){this.Rol = Rol;}
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setMapUsuarios(Map<String, Usuario> MapUsuarios) {this.MapUsuarios = MapUsuarios;}
    public void setUsuarioSeleccionado(Usuario UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setOpt(String Opt){this.Opt = Opt;}
    public void setUsuariosFiltrados(List<Usuario> UsuariosFiltrados) {this.UsuariosFiltrados = UsuariosFiltrados;}
    public void setMapUsuariosFiltrados(Map<String, Usuario> MapUsuariosFiltrados){this.MapUsuariosFiltrados = MapUsuariosFiltrados;}
    public void setAniosCursos(List<String> AniosCursos) {this.AniosCursos = AniosCursos;}
    public void setAsignaturasCursos(Map<String, Integer> AsignaturasCursos) {this.AsignaturasCursos = AsignaturasCursos;}
    public void setIdOpt(int IdOpt) {this.IdOpt = IdOpt;}
    public void setEstudiosCursadosEstudiante(Map<Integer, String> EstudiosCursadosEstudiante) {this.EstudiosCursadosEstudiante = EstudiosCursadosEstudiante;}
    
    
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
        
        // parametros para listado.
        try{
            this.Opt = request.getParameter("opt");
            this.IdOpt = Integer.parseInt(request.getParameter("idopt"));
        }catch(NullPointerException ex){
            this.Opt = "no";
        }catch(NumberFormatException ex){
            this.IdOpt = 0;
        }finally{
            if (this.Opt==null) this.Opt = "no";
        }
        
        //  Roles
        if (Rol==null || Rol.isEmpty()) {
            Rol = request.getParameter("rol");
        }
        this.Usuarios = new ArrayList<>();
        this.MapUsuarios = new HashMap<>();
        String currentURL = context.getViewRoot().getViewId();
        if (currentURL.equals("/Usuario/ListarUsuarios.xhtml")){
            if(this.Opt.equals("estudianteCurso")){
                this.Usuarios = fUsr.listarUsuarioEstudianteCurso(IdOpt);
                for(Usuario usr: Usuarios){
                    MapUsuarios.put(usr.getApellidoUsuario(), usr);
                    MapUsuarios = new TreeMap<>(MapUsuarios);
                }
            }else{
                this.Usuarios = fUsr.listarUsuarios(Rol);
                for(Usuario usr: Usuarios){
                    MapUsuarios.put(usr.getApellidoUsuario(), usr);
                    MapUsuarios = new TreeMap<>(MapUsuarios);
                }
            }
        }else if(currentURL.equals("/Usuario/SeleccionarUsuarios.xhtml")){
            switch(Opt){
                case "nuevorol":
                    this.Usuarios = fUsr.listarUsuariosSinRol(Rol);
                    for(Usuario usr: Usuarios){
                        MapUsuarios.put(usr.getApellidoUsuario(), usr);
                        MapUsuarios = new TreeMap<>(MapUsuarios);
                    }
                    break;
                    
                case "addestcurso":
                    this.Usuarios = fUsr.listarUsuarioEstudianteSinCurso(IdOpt);
                    for(Usuario usr: Usuarios){
                        MapUsuarios.put(usr.getApellidoUsuario(), usr);
                        MapUsuarios = new TreeMap<>(MapUsuarios);
                    }
                    break;
                    
                default:
                    this.Usuarios = fUsr.listarUsuarios(Rol);
                    for(Usuario usr: Usuarios){
                        MapUsuarios.put(usr.getApellidoUsuario(), usr);
                        MapUsuarios = new TreeMap<>(MapUsuarios);
                    }
                    break;
            }
        }
        listChecked = new HashMap<>();
        for (Usuario Usr : MapUsuarios.values()) {
            listChecked.put(Usr.getIdUsuario(), Boolean.FALSE);
        }
        this.MapUsuariosFiltrados = this.MapUsuarios;
        
        this.AniosCursos = new ArrayList<>();
        this.AniosCursos = fCurso.getAniosCursos();
        this.AniosCursos.add("Todos");
        
        AsignaturasCursos = new HashMap<>();
        List<Asignatura> ListAsignaturaCurso =  fAsig.ListarAsignaturasCurso();
        for (int i = 0; i < ListAsignaturaCurso.size(); i++) {
            AsignaturasCursos.put(ListAsignaturaCurso.get(i).getNombreAsignatura(),ListAsignaturaCurso.get(i).getIdAsignatura());
        }
        AsignaturasCursos.put("Todos",0);
        EstudiosCursadosEstudiante = new HashMap<>();
        if(Rol.equals("Estudiante")){
            for(Usuario user: MapUsuarios.values()){
                if(user instanceof Estudiante){
                    String EstudiosCursados = "";
                    List<Estudio> estudios = ((Estudiante) user).getEstudiosCursadosEstudiante();
                    for(int i = 0; i < estudios.size(); i++){
                        EstudiosCursados += estudios.get(i).getTipoDeEstudio().getTipoDeEstudio() + ": " + estudios.get(i).getOrientacionEstudio();
                        if(i+1<estudios.size()) {
                            EstudiosCursados += ", ";
                        }else{
                            EstudiosCursados += ".";
                        }
                    }
                    EstudiosCursadosEstudiante.put(user.getIdUsuario(), EstudiosCursados);
                }
            }
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
        for (Usuario Usr : MapUsuarios.values()) {
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
        UsrData.setImagenUsuario(usr.getImagenUsuario());
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
        UsrData.setIdUsuario(usr.getIdUsuario());
        try{
            UsrData.registrarUsuario();
        }catch(IOException ex){}
        
    }
    
    /**
     * Registra un nuevo Rol a los usuarios checkeados
     * @throws java.io.IOException
     */
    public void nuevoRol() throws IOException{
        List<Usuario> CheckedUsers = this.getCheckedUsers();
        for (Usuario usr : CheckedUsers) {
            RegistrarRol(usr);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListarUsuarios.xhtml?rol="+Rol);
    }
    
    public void agregarEstudianteACurso() throws IOException{
        List<Usuario> CheckedUsers = this.getCheckedUsers();
        for (Usuario usr : CheckedUsers) {
            fCurso.AgregarEstudianteACurso(usr.getIdUsuario(), this.IdOpt);
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("../Curso/ListarCursos.xhtml");
    }
    
    /**
     * retonra un String con los nombres de las asignaturas dictadas por el docente de Id idDocente
     * @param idDocente
     * @return
     */
    public String listarAsignaturasDocente(int idDocente){
        String result = "";
        List <String> AsignaturasDocente = fAsig.AsignaturasDocente(idDocente);
        for (int i = 0; i < AsignaturasDocente.size(); i++) {
            result += AsignaturasDocente.get(i);
            if(i+1<AsignaturasDocente.size()){
                result += ", ";
            }else{
                result += ".";
            }
        }
        return result;
    }
    /**
     * retonra un String con los nombres de los estudios cursados por el estudiante.
     * @param idEstudiante
     * @return
     */
    public String listarEstudiosCursados(int idEstudiante){
        String resultado = "";
        List <String> EstudiosCursados = fEst.ListarEstudiosOrientacionCursados(idEstudiante);
        for (String estudio : EstudiosCursados) {
            resultado = resultado + " " + estudio;
        }
        return resultado;
    }
    
    public void filtrarPorNombreCedula(String nameFilter, String cedulaFilter ){
        List<Usuario> listUsuarios = new ArrayList<>();
        if (((nameFilter == null) || (nameFilter.isEmpty()))&&((cedulaFilter == null) || (cedulaFilter.isEmpty()))){
            MapUsuariosFiltrados = MapUsuarios;
        }else{
            MapUsuariosFiltrados = new HashMap<>();
            for(Usuario usr: MapUsuarios.values()){
                listUsuarios.add(usr);
            }
            for(int i = 0; i < listUsuarios.size(); i++){
                if(((listUsuarios.get(i).getNombreCompleto().toLowerCase().contains(nameFilter.toLowerCase())))
                        && ((String.valueOf(listUsuarios.get(i).getCedulaUsuario()).contains(cedulaFilter))))
                    MapUsuariosFiltrados.put(listUsuarios.get(i).getApellidoUsuario(), listUsuarios.get(i));
            }
        }
    }
    
    public void filtrarPorAnioSemestreAssignatura(String anioFiltro, int semestre, int Idasignatura){
        
        int anio = 0;
        
        if (!anioFiltro.equals("Todos")){
            anio = Integer.valueOf(anioFiltro);
        }
        
        if(Rol.equals("Docente")){
            Usuarios = fUsr.listarUsuariosDocente(semestre, anio, Idasignatura);
            for(Usuario usr: Usuarios){
                MapUsuarios.put(usr.getApellidoUsuario(), usr);
                MapUsuarios = new TreeMap<>(MapUsuarios);
            }
        }else if (Rol.equals("Estudiante")){
            Usuarios = fUsr.listarUsuariosEstudiante(semestre, anio, Idasignatura);
            for(Usuario usr: Usuarios){
                MapUsuarios.put(usr.getApellidoUsuario(), usr);
                MapUsuarios = new TreeMap<>(MapUsuarios);
            }
        }
        MapUsuariosFiltrados = MapUsuarios;
    }
    
}
