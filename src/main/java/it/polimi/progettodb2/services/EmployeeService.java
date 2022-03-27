package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import it.polimi.progettodb2.exceptions.CredentialsException;
import jakarta.persistence.criteria.Order;

import java.util.List;
import java.util.NoSuchElementException;
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

    public void newPack(String name, Integer sms, Integer minute, Integer mGiga, Float extraMGiga, Float extraSms, Byte fixedPhone, Integer fGiga, Float extraFGiga, Float price12M, List<OptserviceEntity> opts){


        PackageEntity pack = new PackageEntity(name, sms, minute, mGiga, extraMGiga, extraSms, fixedPhone, fGiga, extraFGiga, price12M);
        pack.setOptService(opts);

    }

    public List<OptserviceEntity> findAllOptionalProduct(){
        return em.createNamedQuery("Optional.findAllOptionalProduct", OptserviceEntity.class).getResultList();
    }

    public Optional<EmployeeEntity> findByUsername(String usrn) {
        return em.createNamedQuery("Employee.findByUsername", EmployeeEntity.class)
                .setParameter("username", usrn)
                .getResultStream().findAny();
    }

    public List<TotpurchaseperpackandvalidityEntity> findAllTot (){
        return em.createNamedQuery("TotalPurchasesPerPackage.findAllTot", TotpurchaseperpackandvalidityEntity.class).getResultList();
    }

    public List<AvgproductperserviceEntity> findAllAvg (){
        return em.createNamedQuery("AvgProductPerService.findAllAvg", AvgproductperserviceEntity.class).getResultList();
    }

    public List<InsolventUserEntity> findAllInsolvent (){
        return em.createNamedQuery("InsolventUser.findAllInsolvent", InsolventUserEntity.class).getResultList();
    }

    public List<PackagePerSalesEntity> findAllSales (){
        return em.createNamedQuery("PackagePerSales.findAllSales", PackagePerSalesEntity.class).getResultList();
    }

    public List<UserEntity> findAllUser() {
        return em.createNamedQuery("User.findAllUser", UserEntity.class).getResultList();
    }

    public List<OrderEntity> findAllOrder() {
        return em.createNamedQuery("Order.findAllOrder", OrderEntity.class).getResultList();
    }

    public List<SuspendedOrderEntity> findAllSuspendedOrder() {
        return em.createNamedQuery("SuspendedOrder.findAllSuspendedOrder", SuspendedOrderEntity.class).getResultList();
    }

    public List<AuditEntity> findAllAudit() {
        return em.createNamedQuery("Audit.findAllAudit", AuditEntity.class).getResultList();
    }

    public List<BestOptionalEntity> findAllBestOptional() {
        return em.createNamedQuery("BestOptional.findAllBestOptional", BestOptionalEntity.class).getResultList();
    }


}
