package Asignatura.Curso.Evaluacion.Resultado;

import Usuario.Estudiante.ControladorEstudiante;
import Asignatura.Curso.Evaluacion.ControladorEvaluacion;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeResultado implements Serializable {
    @EJB
    private ControladorEvaluacion cEva;
    @EJB
    private ControladorResultado cRes;
    @EJB
    private ControladorEstudiante cEst;
    
    public FacadeResultado() {}
    
    /**
     * Registra un resultado en la base de datos.
     * @param IdEvaluacion 
     * @param IdEstudiante
     * @param ResultadoEvaluacion resutlado de la evaluacion.
     * @return
     */
    public int RegistrarResultadoEvaluacion(int IdEvaluacion, int IdEstudiante, int ResultadoEvaluacion){
        Resultado resultado = cRes.CrearResultado(ResultadoEvaluacion, cEst.BuscarEstudiante(IdEstudiante), cEva.BuscarEvaluacion(IdEvaluacion));
        if (resultado!=null) {
            return resultado.getIdResultado();
        }
        return -1;
    }
    
    public List<Resultado> ListarResultadosEstudiante(int idEstudiante){
        return cRes.ListarResultadosEstudiante(idEstudiante);
    }
    
    public List<Resultado> ListarResultadosEstudiantePorCurso(int idEstudiante, int idCurso){
        return cRes.ListarResultadosEstudiantePorCurso(idEstudiante, idCurso);
    }
}
