
package interfaz.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

@FacesValidator(value="ImageFileValidator")
public class ImagenValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        String extensionArchivo = "";
        
        try {
            extensionArchivo = FilenameUtils.getExtension(file.getSubmittedFileName());
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage("Archivo Invalido"), ex);
        }
        if (!extensionArchivo.equals("jpg") || !extensionArchivo.equals("png") || !extensionArchivo.equals("tiff") || !extensionArchivo.equals("bmp")) {
            throw new ValidatorException(new FacesMessage("Archivo de imagen no valido. Utilizar .pdf|.bmp|.png|.tiff"));
        }
        
    }
    
}
