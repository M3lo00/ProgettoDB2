package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import it.polimi.progettodb2.exceptions.CredentialsException;

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

    /*public void newPack(){

        PackageEntity pack = new PackageEntity(String name, Integer sms, );

    }*/

    public List<OptserviceEntity> findAllOptionalProduct(){
        return em.createNamedQuery("Optional.findAllOptionalProduct", OptserviceEntity.class).getResultList();
    }

    public Optional<EmployeeEntity> findByUsername(String usrn) {
        return em.createNamedQuery("Employee.findByUsername", EmployeeEntity.class)
                .setParameter("username", usrn)
                .getResultStream().findAny();
    }

    public TotpurchaseperpackandvalidityEntity purchasePerPackage (int package_id){
        TotpurchaseperpackandvalidityEntity totalPurchasesPerPackageEntity;
        return totalPurchasesPerPackageEntity=em.createNamedQuery("TotalPurchasesPerPackage.findByPackage", TotpurchaseperpackandvalidityEntity.class)
                .setParameter("package_id", package_id).getResultList().stream().findFirst().get();
    }





}
