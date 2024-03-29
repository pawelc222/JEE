package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import javax.ejb.EJB;
import pl.gda.pg.eti.kask.javaee.jsf.WiezaService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wieza;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author psysiu
 */
@ManagedBean
@RequestScoped
public class WiezaConverter implements Converter {

    @EJB
    private WiezaService wiezaService;

    public void setWiezaService(WiezaService wiezaService) {
        this.wiezaService = wiezaService;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return wiezaService.findWieza(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((Wieza) value).getId() + "";
    }
}
