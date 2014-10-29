package pl.gda.pg.eti.kask.javaee.jsf;

import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Mag;
import pl.gda.pg.eti.kask.javaee.jsf.entities.ObjectFactory;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Swiat;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wieza;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @author psysiu
 * @author pc222
 */
@Stateless
@LocalBean
@Log
public class WiezaService implements Serializable {

  @PersistenceContext
  EntityManager em;

  //private SortedMap<Integer, Wieza> wieze;

  //private SortedMap<Integer, Mag> magowie;

  public WiezaService() {
//    wieze = new TreeMap<>();
//    magowie = new TreeMap<>();
//    try {
//      JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
//      Unmarshaller u = jaxbContext.createUnmarshaller();
//      Swiat swiat = (Swiat) u.unmarshal(getClass().getResourceAsStream("/pl/gda/pg/eti/kask/javaee/jsf/xml/wieze.xml"));
//      for (Wieza wieza : swiat.getWieza()) {
//        wieze.put(wieza.getId(), wieza);
//        for (Mag mag : wieza.getMag()) {
//          // setting tower to which belongs mag
//          mag.setWieza(wieza, false);
//          magowie.put(mag.getId(), mag);
//        }
//      }
//
//    } catch (JAXBException ex) {
//      log.log(Level.WARNING, ex.getMessage(), ex);
//    }
  }

  private List<Mag> asList(Mag... magowie) {
    return em.createNamedQuery("Mag.findAll").getResultList();
  }

  public List<Wieza> findAllWieze() {
    //return new ArrayList<>(wieze.values());
    return em.createNamedQuery("Wieza.findAll").getResultList();
  }

  public Wieza findWieza(int id) {
    return em.find(Wieza.class, id);
  }

  public void removeWieza(Wieza wieza) {
    //wieze.remove(wieza.getId());
      
            wieza = em.merge(wieza);
            em.remove(wieza);

  }

  public void saveWieza(Wieza wieza) {
        
            if (wieza.getId() < 1) {
                em.persist(wieza);
            } else {
                em.merge(wieza);
            }
        
  }

  public List<Mag> findAllMagowie() {
    return em.createNamedQuery("Mag.findAll").getResultList();
  }

  public Mag findMag(int id) {
    //return magowie.get(id);
      return em.find(Mag.class, id);
  }

  public void removeMag(Mag mag) {
    //mag.setWieza(null);
    //magowie.remove(mag.getId());
        
            mag = em.merge(mag);
            em.remove(mag);
            
  }

  public void saveMag(Mag mag) {
       
            if (mag.getId() < 1) {
                em.persist(mag);
            } else {
                em.merge(mag);
            }
        
  }

  public void marshalSwiat(OutputStream out) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
      Marshaller m = jaxbContext.createMarshaller();
      Swiat swiat = new Swiat();
      swiat.getWieza().addAll(findAllWieze());
      m.marshal(swiat, out);
    } catch (JAXBException ex) {
      log.log(Level.WARNING, ex.getMessage(), ex);
    }
  }
}
