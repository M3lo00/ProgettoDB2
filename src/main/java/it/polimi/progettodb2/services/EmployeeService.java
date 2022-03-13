package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.EmployeeEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import it.polimi.progettodb2.exceptions.CredentialsException;

import java.util.List;

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
}
