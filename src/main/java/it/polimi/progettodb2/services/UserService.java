package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.*;
import it.polimi.progettodb2.exceptions.CredentialsException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.Order;
import jakarta.validation.ConstraintViolationException;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public UserService() {
    }

    /*
        public boolean randomPayment(){
            Random rd = new Random();
            return rd.nextBoolean();
        }
    */
    public UserEntity checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
        List<UserEntity> uList = null;
        try {
            uList = em.createNamedQuery("User.checkCredentials", UserEntity.class).setParameter(1, usrn).setParameter(2, pwd)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentials");
        }
        if (uList.isEmpty())
            return null;
        else if (uList.size() == 1)
            return uList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    public UserEntity createUser(String username, String email, String password) throws SQLException {
        UserEntity user = new UserEntity(username, email, password);
        try {
            em.persist(user);
            em.flush();
            return user;
        } catch (ConstraintViolationException e) {
            return null;
        }
    }

    //public boolean checkPhone ()

    public OrderEntity newOrder(UserEntity user, PackageEntity pack, Date creationD, Date startD, int period, boolean valid, float total, List<OptserviceEntity> opts){

        //generazione del numero di telefono
        Random rd = new Random();
        int nMobile= 100+rd.nextInt();
        int nFixed=100+rd.nextInt();
        Timestamp crD = new Timestamp(creationD.getTime());
        java.sql.Date startDate= new java.sql.Date(startD.getTime());
        OrderEntity order = new OrderEntity(user, pack, crD, startDate, period, valid,(int) total, nMobile, nFixed, opts);

//        List<PackageEntity>

        try{
            em.persist(order);      //forza il db a creare una tupla
            em.flush();             //forza il db a effettuare tutte le operazioni programmateù
            return order;
        }catch (ConstraintViolationException e){
            return null;
        }
    }

    public Optional<UserEntity> findByUserID(int idUser){
        return em.createNamedQuery("User.findByID", UserEntity.class)
                .setParameter("idUser", idUser)
                .getResultStream().findFirst();
    }

    public Optional<UserEntity> findByUsername(String usrn) {
        return em.createNamedQuery("User.findByUsername", UserEntity.class)
                .setParameter("username", usrn)
                .getResultStream().findFirst();
    }

    public Optional<UserEntity> findByEmail(String email) {
        return em.createNamedQuery("User.findByEmail", UserEntity.class)
                .setParameter("email", email)
                .getResultStream().findFirst();
    }

    public List<PackageEntity> findAllPackages(){
        return em.createNamedQuery("Package.findAll", PackageEntity.class).getResultList();
    }

    public List<OrderEntity> findRejectedOrdersByUser(int user_id){
        UserEntity user = findByUserID(user_id).get();
        return em.createNamedQuery("Order.findRejectedOrdersOfUser", OrderEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public Optional<OrderEntity> findOrderByID(int order_id) {
        return em.createNamedQuery("Order.findByID", OrderEntity.class)
                .setParameter("order_id", order_id)
                .getResultStream().findFirst();
    }

    public List<OrderEntity> findAllOrderByUser(int idUser){
        List<OrderEntity> orders = em.createNamedQuery("Order.findAllOrderByUser", OrderEntity.class).
                setParameter("user", findByUserID(idUser).get()).
                getResultList();
        return orders;
    }

    public List<OptserviceEntity> getAllOptionals(int idPack){
        PackageEntity pack = em.find(PackageEntity.class, idPack);
        return pack.getOptService();
    }

    public List<OptserviceEntity> getAllBuyableOpt(int idPack){
        List<OptserviceEntity> pack = em.find(PackageEntity.class, idPack).getOptService();
        List<OptserviceEntity> opts = em.createNamedQuery("Optional.findAllOptionalProduct", OptserviceEntity.class).getResultList();
        ArrayList<OptserviceEntity> difference = new ArrayList<>(opts);
        difference.removeAll(pack);
        return difference;
    }

}