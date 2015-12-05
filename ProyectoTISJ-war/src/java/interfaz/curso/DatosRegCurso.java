
package interfaz.curso;

import Asignatura.FacadeAsignatura;
import Asignatura.Curso.FacadeCurso;
import Usuario.Docente.FacadeDocente;
import Usuario.FacadeUsuario;
import interfaz.FileUpload;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class DatosRegCurso implements Serializable {
    
    private int idAsignatura;
    private int idDocente;
    private int SemestreCurso;
    private int AnioCurso;
    private Part PartContratoDocente;
    private String ContratoDocenteCurso;
    private String AsignaturaSeleccionada;
    private String DocenteSeleccionado;
    private boolean Correcto;
    
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeAsignatura fAsig;
    @EJB
    private FacadeDocente fDoc;
    @EJB
    private FacadeUsuario fUsr;
    @Inject
    private RegCursoWizard reg;
    @Inject
    private FileUpload fUp;
    
    //  Getters
    public int getSemestreCurso() {return SemestreCurso;}
    public int getAnioCurso() {return AnioCurso;}
    public String getContratoDocenteCurso() {return ContratoDocenteCurso;}
    public String getAsignaturaSeleccionada() {
        if (this.AsignaturaSeleccionada == null || this.AsignaturaSeleccionada.isEmpty()) {
            this.AsignaturaSeleccionada = fAsig.BuscarNombreAsignatura(idAsignatura);
        }
        return AsignaturaSeleccionada;}
    public int getIdAsignatura() {return idAsignatura;}
    public int getIdDocente() {return idDocente;}
    public String getDocenteSeleccionado() {
        if (this.DocenteSeleccionado==null || this.DocenteSeleccionado.isEmpty()) {
            if (this.idDocente == 0) {
                this.DocenteSeleccionado = "No se ha seleccionado docente";
            }else{
                this.DocenteSeleccionado = fDoc.BuscarNombreDocente(idDocente);
            }
        }
        return DocenteSeleccionado;
    }
    public Part getPartContratoDocente() {return PartContratoDocente;}
    public boolean isCorrecto() {return Correcto;}
    
    //  Setters
    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setAsignaturaSeleccionada(String AsignaturaSeleccionada) {this.AsignaturaSeleccionada = AsignaturaSeleccionada;}
    public void setIdAsignatura(int IdAsignatura) {this.idAsignatura = IdAsignatura;}
    public void setIdDocente(int IdDocente) {this.idDocente = IdDocente;}
    public void setDocenteSeleccionado(String DocenteSeleccionado) {this.DocenteSeleccionado = DocenteSeleccionado;}
    public void setPartContratoDocente(Part PartContratoDocente) {this.PartContratoDocente = PartContratoDocente;}
    public void setCorrecto(boolean Correcto) {this.Correcto = Correcto;}
    
    /**
     * Se traen los valores de docente y asignatura desde el bean de conversacion
     */
    @PostConstruct
    public void init(){
        this.idAsignatura = reg.getIdAsignatura();
        this.idDocente = reg.getIdDocente();
        this.Correcto = false;
    }
    
    /**
     * Registra el curso y finaliza la conversacion.
     * @throws IOException
     */
    public void onFinish() throws IOException {
        if(comprobarFormularioInscripcion()){
            if ((fCurso.RegistrarCurso(SemestreCurso, AnioCurso, idDocente, idAsignatura, ContratoDocenteCurso))!=-1) {
                Correcto=true;
                reg.endConversation();
                
            }
        }
    }
    
    /**
     * Comprueba que se haya seleccionado el contrato del docente.
     */
    public boolean comprobarFormularioInscripcion(){
        String cedula = String.valueOf(fUsr.BuscarUsuario(idDocente).getCedulaUsuario());
        ContratoDocenteCurso= fUp.guardarArchivo("ContratoDocente", PartContratoDocente, cedula);
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.ContratoDocenteCurso == null || this.ContratoDocenteCurso.equals("no")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono contrato.");
            context.addMessage("frmIngresoDatos:msjContrato", fm);
            return false;
        }
        return true;
    }
}
