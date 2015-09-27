
package Estudiante;

import Enumerados.TipoDeEstudio.TipoEstudio;
import Estudiante.estudios.ControladorEstudio;
import Estudiante.estudios.Estudio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeEstudiante implements Serializable {
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private ControladorEstudio cEstudio;
    
    public FacadeEstudiante() {}

    /**
     * Agrega el estudio especificado al estudiante indicado por Id
     * @param TipoDeEstudio
     * @param Orientacion
     * @param idEstudiante 
     */
    public void agregarEstudiosEstudiante(TipoEstudio TipoDeEstudio, String Orientacion, int idEstudiante){
        Estudiante estudiante = cEst.BuscarEstudiante(idEstudiante);
        Estudio estudio = cEstudio.CrearEstudio(TipoDeEstudio, Orientacion);
        estudiante.addEstudioCursado(estudio);
        cEst.ModificarEstudiante(estudiante);
    }
    
    /**
     * Devuelve una lista con todos los estudiantes registrados en el sistema.
     * @return 
     */
    public List<Estudiante> ListarEstudiates(){
        return cEst.ListarEstudiantees();
    }
 }
