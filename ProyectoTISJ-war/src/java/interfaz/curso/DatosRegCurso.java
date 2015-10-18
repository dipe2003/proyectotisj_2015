
package interfaz.curso;

import Asignatura.FacadeAsignatura;
import Curso.FacadeCurso;
import Docente.FacadeDocente;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DatosRegCurso implements Serializable {

    private int idAsignatura;
    private int idDocente;
    private int SemestreCurso;
    private int AnioCurso;
    private String ContratoDocenteCurso;
    private String AsignaturaSeleccionada;
    private String DocenteSeleccionado;
    
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeAsignatura fAsig;
    @EJB
    private FacadeDocente fDoc;
    @Inject 
    private RegCursoWizard reg;
      
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
    
    //  Setters
    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setAsignaturaSeleccionada(String AsignaturaSeleccionada) {this.AsignaturaSeleccionada = AsignaturaSeleccionada;}    
    public void setIdAsignatura(int IdAsignatura) {this.idAsignatura = IdAsignatura;}
    public void setIdDocente(int IdDocente) {this.idDocente = IdDocente;}
    public void setDocenteSeleccionado(String DocenteSeleccionado) {this.DocenteSeleccionado = DocenteSeleccionado;}
    
    /**
     * Se traen los valores de docente y asignatura desde el bean de conversacion
     */
    @PostConstruct
    public void init(){
        this.idAsignatura = reg.getIdAsignatura();
        this.idDocente = reg.getIdDocente();
    }
    
    /**
     * Registra el curso y finaliza la conversacion.
     * @throws IOException 
     */
    public void onFinish() throws IOException {
        if ((fCurso.RegistrarCurso(SemestreCurso, AnioCurso, idDocente, idAsignatura, ContratoDocenteCurso))!=-1) {
        reg.endConversation();
        }
    }
}
