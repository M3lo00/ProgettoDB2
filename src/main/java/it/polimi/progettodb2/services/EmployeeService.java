package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import it.polimi.progettodb2.exceptions.CredentialsException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeService {

    @PersistenceContext
    private EntityManager em;

    public EmployeeEntity checkCredentials(String username, String password) throws CredentialsException, NonUniqueResultException {
        List<EmployeeEntity> eList;
        try {
            eList = em.createNamedQuery("Employee.checkCredentials", EmployeeEntity.class).setParameter(1, username).setParameter(2, password)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentials");
        }
        if (eList.isEmpty())
            return null;
        else if (eList.size() == 1)
            return eList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");
    }

    public void newPack(EmployeeEntity emp, String name, Integer sms, Integer minute, Integer mGiga, Float extraMinute, Float extraMGiga, Float extraSms, Byte fixedPhone, Integer fGiga, Float extraFGiga, Float price12M, List<OptserviceEntity> opts){


        PackageEntity pack = new PackageEntity(emp, name, sms, minute, mGiga, extraMinute, extraMGiga, extraSms, fixedPhone, fGiga, extraFGiga, price12M);
        pack.setOptService(opts);

        try{
            em.persist(pack);
            em.flush();
        }catch (ConstraintViolationException e) {
            e.printStackTrace();
        }

    }

    public void newOpt(EmployeeEntity emp, String name, Float price){

        OptserviceEntity opt = new OptserviceEntity(emp, name, price);
        try{
            em.persist(opt);
            em.flush();
        }catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }

    public List<OptserviceEntity> findAllOptionalProduct(){
        return em.createNamedQuery("Optional.findAllOptionalProduct", OptserviceEntity.class).getResultList();
    }

    public Optional<EmployeeEntity> findByUsername(String usrn) {
        return em.createNamedQuery("Employee.findByUsername", EmployeeEntity.class)
                .setParameter("username", usrn)
                .getResultStream().findAny();
    }

    public List<UserEntity> findAllInsolvent (){
        return em.createNamedQuery("User.findAllInsolvent", UserEntity.class).getResultList();
    }

    public List<AuditEntity> findAllAudit() {
        return em.createNamedQuery("Audit.findAllAudit", AuditEntity.class).getResultList();
    }

    public List<OptserviceEntity> findAllBestOptional() {
        return em.createQuery( "SELECT o FROM OptserviceEntity o ORDER BY o.totSold desc ", OptserviceEntity.class).getResultList();
    }

    public List<OrderEntity> findAllSuspendedOrder() {
        return em.createNamedQuery("Order.findAllSuspendedOrder", OrderEntity.class).getResultList();
    }
}
