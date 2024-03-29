package pl.gda.pg.eti.kask.javaee.jsf.view;


import pl.gda.pg.eti.kask.javaee.jsf.WiezaService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wieza;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 * @author psysiu
 * @author pc222
 */
@ViewScoped
@ManagedBean
public class ListWieze implements Serializable {

  @EJB
  private WiezaService wiezaService;

  public void setWiezaService(WiezaService wiezaService) {
    this.wiezaService = wiezaService;

    this.getWieze();
    this.getMagowie();
  }

  private List<Wieza> wieze;
  private List<Mag> magowie;

  public List<Wieza> getWieze() {    
    if (wieze == null) {
      wieze = wiezaService.findAllWieze();
    }
    return wieze;
  }

  public void removeWieza(Wieza wieza) {
    wiezaService.removeWieza(wieza);
    wieze.remove(wieza);
  }

  public List<Mag> getMagowie() {
    if (magowie == null) {
      magowie = wiezaService.findAllMagowie();
    }
    return magowie;
  }

  public void removeMag(Mag mag) {
    wiezaService.removeMag(mag);
    magowie.remove(mag);
    
    //wieze = wiezaService.findAllWieze();
  }

  public void downloadSwiatXML() throws IOException {
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();

    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    ec.setResponseContentType("text/xml"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"swiat.xml\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

    OutputStream output = ec.getResponseOutputStream();

    wiezaService.marshalSwiat(output);

    fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
  }
}
