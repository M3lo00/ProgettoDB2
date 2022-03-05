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

    @Column(name = "email", nullable=false)
    private String email; //Loro hanno aggiunto in più che devono essere unique

    @Column(name = "Username", nullable=false)
    private String Username; //Loro hanno aggiunto in più che devono essere unique

    @Column(name = "Password", nullable=false)
    private String Password; //Loro hanno aggiunto in più che devono essere unique

    @Column(name = "Insolvent", nullable=false)
    private Boolean Insolvent;

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
}
