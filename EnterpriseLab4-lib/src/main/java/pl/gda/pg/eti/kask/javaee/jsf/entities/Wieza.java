
package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;


/**
 * <p>Java class for wieza complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="wieza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mag" type="{http://www.eti.pg.gda.pl/kask/javaee/wieze}mag" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="wysokosc" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@ToString(of = "wysokosc")
@EqualsAndHashCode(exclude = "mag")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wieza", namespace = "http://www.eti.pg.gda.pl/kask/javaee/wieze", propOrder = {
    "mag"
})
@Entity
@Table(name = "towers")
@NamedQuery(name = "Wieza.findAll", query = "SELECT b FROM Wieza b")
@Getter
@Setter
public class Wieza {

  @XmlElement(namespace = "http://www.eti.pg.gda.pl/kask/javaee/wieze")
  @OneToMany(mappedBy="wieza", cascade= CascadeType.ALL)
  protected List<Mag> mag;
  @XmlAttribute(name = "wysokosc", required = true)
  @Column
  protected int wysokosc;
  @XmlAttribute(name = "id", required = true)
  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  /**
   * Gets the value of the mag property.
   * <p/>
   * <p/>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the mag property.
   * <p/>
   * <p/>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getMag().add(newItem);
   * </pre>
   * <p/>
   * <p/>
   * <p/>
   * Objects of the following type(s) are allowed in the list
   * {@link Mag }
   */


}
