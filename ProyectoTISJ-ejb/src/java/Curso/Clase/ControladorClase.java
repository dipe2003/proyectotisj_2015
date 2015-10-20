package Curso.Clase;

import Curso.Curso;
import Estudiante.Estudiante;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorClase {
    
    @EJB
    private ManejadorClase mClase;
    
    /**
     * Crea una clase en la base de datos.
     * @param FechaClase
     * @param TemaClase
     * @return 
     */
    public Clase CrearClase(Date FechaClase, String TemaClase){
        Clase clase = new Clase(FechaClase, TemaClase);
        if (mClase.CrearClase(clase)!=-1) {
            return clase;
        }else{
            return null;
        }
    }
    
    /**
     * Actualiza los datos de la clase en la base de datos.
     * @param clase
     * @return 
     */
    public int ModificarClase(Clase clase){
        return mClase.ModificarClase(clase);
    }
    
    public int BorrarClase(Clase clase){
        return mClase.BorrarClase(clase);
    }
    
    /**
     * Busca una clase especifica por su id.
     * @param idClase
     * @return 
     */
    public Clase BuscarClase(int idClase){
        return mClase.BuscarClase(idClase);
    }
    
    /**
     * Lista todas las clases registradas en el sistema.
     * @return 
     */
    public List<Clase> ListarClases(){
        return mClase.ListarClases();
    }
    
    /**
     * Devuelve una lista de clases pertenecientes al curso especificado por su id.
     * @param IdCurso
     * @return 
     */
    public List<Clase> ListarClases(int IdCurso){
        return mClase.ListarClases(IdCurso);
    }
    
    /**
     * Agrega un curso a la clase y la devuelve.
     * @param CursoClase
     * @param IdClase
     * @return 
     */
    public Clase AgregarCursoClase(Curso CursoClase, int IdClase){
        Clase clase = mClase.BuscarClase(IdClase);
        clase.setCursoClase(CursoClase);
        mClase.ModificarClase(clase);
        return clase;
    } 
    
    /**
     * Agrega una lista de estudiantes a una clase especificada por su id. (Registra las asistenias).
     * @param EstudiantesClase
     * @param IdClase
     * @return 
     */
    public Clase AgregarEstudiantesClase(List<Estudiante> EstudiantesClase, int IdClase){
        Clase clase = mClase.BuscarClase(IdClase);
        clase.setAsistenciasClase(EstudiantesClase);
        mClase.ModificarClase(clase);
        return clase;
    }
    
    /**
     * Agraga un estudiante a la clase.
     * @param EstudianteClase
     * @param IdClase
     * @return 
     */
    public Clase AgregarEstudianteAClase(Estudiante EstudianteClase, int IdClase){
        Clase clase = mClase.BuscarClase(IdClase);
        clase.addEstudianteClase(EstudianteClase);
        mClase.ModificarClase(clase);
        return clase;
    }
}
