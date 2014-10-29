
package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.entities.validators.MagName;


/**
 * <p>Java class for mag complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="mag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="imie" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mana" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="zywiol" use="required" type="{http://www.eti.pg.gda.pl/kask/javaee/wieze}zywiol" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@ToString
@EqualsAndHashCode(exclude = "wieza")
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mag", namespace = "http://www.eti.pg.gda.pl/kask/javaee/wieze")
@Getter
@Setter
@Entity
@Table(name = "mages")
@NamedQuery(name = "Mag.findAll", query = "SELECT m FROM Mag m")
public class Mag {

  @XmlAttribute(name = "imie", required = true)
  @Column
  @MagName(regex = "^[A-z]+$")
  protected String imie;
  @XmlAttribute(name = "mana", required = true)
  @Column
  protected int mana;
  @XmlAttribute(name = "zywiol", required = true)
  @Column
  @Enumerated(EnumType.STRING)
  protected Zywiol zywiol = Zywiol.WODA;
  @XmlAttribute(name = "id", required = true)
  @Column
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  @XmlTransient
  @ManyToOne()
  @JoinColumn(name = "wieza")
  protected Wieza wieza;

  /**
   * Gets the value of the imie property.
   *
   * @return possible object is
   * {@link String }
   */
 
}
