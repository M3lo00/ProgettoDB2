package it.polimi.progettodb2.entities;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;





//@Entity
/*
Dobbiamo definire un'entità in modo che JPA ne sia a conoscenza.
La definiamo utilizzando l'annotazione @Entity. Dobbiamo specificare questa
annotazione a livello di classe. Dobbiamo anche assicurarci che l'entità abbia
un costruttore no-arg e una chiave primaria.
 */

/*
Per impostazione predefinita, il nome dell'entità è il nome della classe.
Possiamo cambiarne il nome usando l'elemento name:
@Entity(name="userNuovaEntità")
 */

/*
Because various JPA implementations will try subclassing our entity in order
to provide their functionality, ***entity classes must not be declared final***.
 */
//@Table(name = "user", schema = "dbproj")
/*
Nella maggior parte dei casi, il nome della tabella nel database e il nome
dell'entity non saranno gli stessi.
In questi casi, possiamo specificare il nome della tabella usando
l'annotazione @Table.
 */

/*
If we don't use the @Table annotation, the name of the table will be the
name of the entity.
 */


@Entity
@Table(name = "user", schema = "dbproj")

@NamedQuery(name = "User.checkCredentials",
        query = "SELECT r FROM UserEntity r  WHERE r.username = ?1 and r.password = ?2")


public class UserEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    //@Id
    /*
    Ciascuna entità JPA deve avere una chiave primaria che la identifichi in
    modo univoco. L'annotazione @Id definisce la chiave primaria.
    */



    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Possiamo generare gli identificatori in diversi modi. Essi sono specificati
    dall'annotazione @GeneratedValue.

    Possiamo scegliere tra quattro strategie di generazione di id con
    l'elemento strategy. Il valore può essere AUTO, TABLE, SEQUENCE o IDENTITY:
     */

    /*
    In questo caso utilizziamo @GeneratedValue(strategy = GenerationType.IDENTITY)
    per poter generare una chiave primaria autoincrementale
     */

    //@Column(name = "idUser", nullable=false)
    //private Long idUser;

    /*
    Proprio come l'annotazione @Table, possiamo usare l'annotazione @Column per
    menzionare i dettagli di una colonna nella tabella.
    L'annotazione @Column ha molti elementi come name, length, nullable (valori nulli)
    e unique.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable=false)
    private Long idUser; //noi abbiamo usato int.. dobbiamo cambiare in Long???
    @Basic
    @Column(name = "email", nullable=false)
    private String email; //Loro hanno aggiunto in più che devono essere unique
    @Column(name = "Username", nullable=false)
    private String username; //Loro hanno aggiunto in più che devono essere unique
    //Un signed int può avere al max ((2^31)-1) 429.4967.295 elementi. Mi sa che ci basta int long è ((2^63)-1)
    @Column(name = "Password", nullable=false)
    private String password; //Loro hanno aggiunto in più che devono essere unique
    @Column(name = "Insolvent", nullable=false)
    private Boolean insolvent;
    @Basic
    @Column(name = "failedPay", nullable=false)
    private int failedPay;
    /*
    A volte, potremmo voler rendere un campo non persistente.
    Possiamo usare l'annotazione @Transient per farlo. Specifica che il campo
    non sarà persistente.
    Ad esempio, possiamo calcolare l'età di uno studente dalla data di nascita.
    Quindi annotiamo l'età del campo con l'annotazione @Transient:

    @Transient
    private Integer age;

    In some cases, we may have to save temporal values in our table:

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    However, with JPA 2.2, we also have support for java.time.LocalDate,
    java.time.LocalTime, java.time.LocalDateTime, java.time.OffsetTime and
    java.time.OffsetDateTime.
     */

    public UserEntity() {
    }

    //costruttore entity
    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        failedPay=0;
        insolvent=false;
    }

    /*
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "Insolvent")
    private Object insolvent;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getFailedPay() {
        return failedPay;
    }

    public void setFailedPay(Object failedPay) {
        this.failedPay = failedPay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getInsolvent() {
        return insolvent;
    }

    public void setInsolvent(Object insolvent) {
        this.insolvent = insolvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (failedPay != that.failedPay) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (insolvent != null ? !insolvent.equals(that.insolvent) : that.insolvent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (insolvent != null ? insolvent.hashCode() : 0);
        result = 31 * result + failedPay;
        return result;
    }*/
}
