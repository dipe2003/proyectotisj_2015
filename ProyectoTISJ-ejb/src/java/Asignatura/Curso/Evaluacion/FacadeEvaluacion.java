package Asignatura.Curso.Evaluacion;

import Asignatura.Curso.ControladorCurso;
import Usuario.Estudiante.ControladorEstudiante;
import Asignatura.Curso.Evaluacion.Resultado.ControladorResultado;
import Asignatura.Curso.Evaluacion.Examen.ControladorExamen;
import Asignatura.Curso.Evaluacion.Laboratorio.ControladorLaboratorio;
import Asignatura.Curso.Evaluacion.Parcial.ControladorParcial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeEvaluacion implements Serializable {
    @EJB
    private ControladorEvaluacion cEva;
    @EJB
    private ControladorLaboratorio cLab;
    @EJB
    private ControladorExamen cExam;
    @EJB
    private ControladorParcial cPar;
    @EJB
    private ControladorCurso cCurso;
    @EJB
    private ControladorResultado cRes;
    @EJB
    private ControladorEstudiante cEst;
    
    public FacadeEvaluacion() {}
        
    /**
     * Crea una evalucaion del tipo especificado y la persiste.
     * @param idCurso Curso al que pertenece la evaluacion.
     * @param FechaEvaluacion
     * @param TipoEvaluacion Tipo de Evaluacion: Laboratorio, Examen, Parcial.
     * @return 
     */
    public int RegistrarEvaluacion(int idCurso, Date FechaEvaluacion, String TipoEvaluacion){
        Evaluacion evaluacion = null;
        switch(TipoEvaluacion){
            case "Laboratorio":
                evaluacion = cLab.CrearLaboratorio(FechaEvaluacion, cCurso.BuscarCurso(idCurso));
                break;
                
            case "Examen":
                evaluacion = cExam.CrearExamen(FechaEvaluacion, cCurso.BuscarCurso(idCurso));
                break;

            case "Parcial":
                evaluacion = cPar.CrearParcial(FechaEvaluacion, cCurso.BuscarCurso(idCurso));
                break;
                
            default: break;
        }
        
        if (evaluacion!=null) {
            return evaluacion.getIdEvaluacion();
        }
        return -1;
    }

    
    public List<Evaluacion> getEvaluacionesPorCurso(int idCurso){
        return cEva.getEvaluacionesPorCurso(idCurso);
    }
    
    public List<Evaluacion> getEvaluacionPorEstudiante(int idEstudiante){
        return cEva.getEvaluacionPorEstudiante(idEstudiante);
    }
}
