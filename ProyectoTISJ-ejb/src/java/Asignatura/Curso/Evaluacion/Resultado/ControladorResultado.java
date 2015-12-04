package Asignatura.Curso.Evaluacion.Resultado;

import Usuario.Estudiante.Estudiante;
import Asignatura.Curso.Evaluacion.Evaluacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorResultado {
    
    @EJB
    private ManejadorResultado mRes;
    
    /**
     * Crea un Resultado y lo persiste. 
     * @param Resultado
     * @param EstudianteResultado
     * @param EvaluacionResultado
     * @return Devuelve un Resultado si fue creado, de lo contrario devuelve null.
     */
    public Resultado CrearResultado(int Resultado, Estudiante EstudianteResultado, Evaluacion EvaluacionResultado){
        Resultado resultado = new Resultado(Resultado, EstudianteResultado, EvaluacionResultado);
        if (mRes.CrearResultado(resultado)!=-1){
            return resultado;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Resultado en la base de datos.
     * @param resultado
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarResultado(Resultado resultado){
        return mRes.ModificarResultado(resultado);
    }
    
    /**
     * Borra los datos de un Resultado en la base de datos.
     * @param resultado
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarResultado(Resultado resultado){
        return mRes.BorrarResultado(resultado);
    }
    
    /**
     * Busca un Resultado en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Resultado BuscarResultado(int id){
        return mRes.BuscarResultado(id);
    }
    
    /**
     * Devuelve una lista de Resultados desde la base de datos.
     * @return 
     */
    public List<Resultado> ListarResultados(){
        return mRes.ListarResultados();
    }    
    
    public List<Resultado> ListarResultadosEstudiante(int idEstudiante){
        return mRes.ListarResultadosEstudiante(idEstudiante);
    }
}
