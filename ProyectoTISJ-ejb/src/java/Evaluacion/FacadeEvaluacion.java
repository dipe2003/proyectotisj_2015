package Evaluacion;

import Curso.ControladorCurso;
import Estudiante.ControladorEstudiante;
import Evaluacion.Resultado.ControladorResultado;
import Examen.ControladorExamen;
import Laboratorio.ControladorLaboratorio;
import Parcial.ControladorParcial;
import java.io.Serializable;
import java.util.Date;
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

}
