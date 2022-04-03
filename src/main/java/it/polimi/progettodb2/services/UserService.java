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
import java.sql.Timestamp;
import java.util.*;

@Stateless
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public UserService() {
    }

    public UserEntity checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
        List<UserEntity> uList;
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

    public void newOrder(UserEntity user, PackageEntity pack, Date creationD, Date startD, int period, float total, List<OptserviceEntity> opts){

        Random rd = new Random();
        boolean valid=rd.nextBoolean();
        Timestamp paymentT=null;
        if (valid){
            paymentT=new Timestamp(creationD.getTime());
        }
        String nMobile= null;
        String nFixed= null;

        if (pack.getMinute()!=null||pack.getSms()!=null||pack.getmGiga()!=null){
            nMobile= "333"+(rd.nextInt(9000000)+1000000);
        }
        if (pack.getFixedPhone()!=null||pack.getfGiga()!=null){
            nFixed= "02"+(rd.nextInt(9000000)+1000000);
        }


        Timestamp crD = new Timestamp(creationD.getTime());
        java.sql.Date startDate= new java.sql.Date(startD.getTime());

        OrderEntity order = new OrderEntity(user, pack, crD, paymentT, startDate, period, rd.nextBoolean(), total, nMobile, nFixed, opts);

        try{
            em.persist(order);
            em.flush();
        }catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    public UserEntity findByUserID(int idUser){
        return em.find(UserEntity.class, idUser);
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

    public List<PackageEntity> findAllPackagesOptional(){
        return em.createNamedQuery("PackageOpts.findAll", PackageEntity.class).getResultList();
    }

    public List<OrderEntity> findRejectedOrdersByUser(int user_id){
        UserEntity user = findByUserID(user_id);
        return em.createNamedQuery("Order.findRejectedOrdersOfUser", OrderEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public OrderEntity findOrderByID(int idOrder) {
        OrderEntity order= em.find(OrderEntity.class, idOrder);
        order.getOptServices().size();
        return order;
    }

    public List<OrderEntity> findAllOrderByUser(int idUser){
        return em.createNamedQuery("Order.findAllOrderByUser", OrderEntity.class).
                setParameter("user", findByUserID(idUser)).
                getResultList();
    }

    public List<OptserviceEntity> getAllBuyableOpt(int idPack){
        List<OptserviceEntity> pack = em.find(PackageEntity.class, idPack).getOptService();
        List<OptserviceEntity> opts = em.createNamedQuery("Optional.findAllOptionalProduct", OptserviceEntity.class).getResultList();
        ArrayList<OptserviceEntity> difference = new ArrayList<>(opts);
        difference.removeAll(pack);
        return difference;
    }

    public void retryPayment (int idOrder){
        Random rd = new Random();
        Date d = new Date();
        Timestamp dateTime= new Timestamp(d.getTime());

        try {
            boolean b= rd.nextBoolean();
            OrderEntity order = em.find(OrderEntity.class, idOrder);
            order.setValid(b);
            order.setPaymentDate(dateTime);

            em.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OptserviceEntity> getPackOptionals (int idPack){
        return em.find(PackageEntity.class, idPack).getOptService();
    }

}