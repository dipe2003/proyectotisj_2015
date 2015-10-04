
package interfaz.curso;

import Asignatura.FacadeAsignatura;
import Curso.FacadeCurso;
import Docente.FacadeDocente;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DatosCursoBean implements Serializable{
    
    private int SemestreCurso;
    private int AnioCurso;
    private String ContratoDocenteCurso;
    private int IdAsignatura;
    private int IdDocente;
    private String AsignaturaSeleccionada;
    private String DocenteSeleccionado;
    
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeAsignatura fAsig;
    @EJB
    private FacadeDocente fDoc;
    
    public DatosCursoBean() {
    
    }
    
    //  Setters

    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setAsignaturaSeleccionada(String AsignaturaSeleccionada) {this.AsignaturaSeleccionada = AsignaturaSeleccionada;}    
    public void setIdAsignatura(int IdAsignatura) {this.IdAsignatura = IdAsignatura;}
    public void setIdDocente(int IdDocente) {this.IdDocente = IdDocente;}
    public void setDocenteSeleccionado(String DocenteSeleccionado) {this.DocenteSeleccionado = DocenteSeleccionado;}
    
    //  Getters

    public int getSemestreCurso() {return SemestreCurso;}
    public int getAnioCurso() {return AnioCurso;}
    public String getContratoDocenteCurso() {return ContratoDocenteCurso;}
    public String getAsignaturaSeleccionada() {
        if (this.AsignaturaSeleccionada == null || this.AsignaturaSeleccionada.isEmpty()) {
            this.AsignaturaSeleccionada = fAsig.BuscarNombreAsignatura(IdAsignatura);
        }        
        return AsignaturaSeleccionada;}
    public int getIdAsignatura() {return IdAsignatura;}
    public int getIdDocente() {return IdDocente;}
    public String getDocenteSeleccionado() {
        if (this.DocenteSeleccionado==null || this.DocenteSeleccionado.isEmpty()) {
            if (this.IdDocente == 0) {
                this.DocenteSeleccionado = "No se ha seleccionado docente";
            }else{
                this.DocenteSeleccionado = fDoc.BuscarNombreDocente(IdDocente);
            }            
        }
        return DocenteSeleccionado;
    }
        
    /**
     * Registra la asignatura con los datos ingresados.
     * @return 
     */
    public String registrarCurso(){
        if ((fCurso.RegistrarCurso(SemestreCurso, AnioCurso, AnioCurso, AnioCurso, ContratoDocenteCurso))!=-1) {
            return "registrado";
        }
        return "";
    }
    
    @PostConstruct
    public void init(){
        
    }
        
}

