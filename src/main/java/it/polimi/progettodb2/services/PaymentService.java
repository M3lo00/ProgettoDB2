package it.polimi.progettodb2.services;

import it.polimi.progettodb2.entities.PaymentEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.exceptions.CredentialsException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Stateless
public class PaymentService {

    @PersistenceContext
    private EntityManager em;

    public PaymentService() {
    }

    public PaymentEntity getPaymentById(int id) throws CredentialsException, NonUniqueResultException {
        List<PaymentEntity> pay= null;
        try {
            System.out.println("Cerco un payment...");
            pay = em.createNamedQuery("Payment.getById", PaymentEntity.class).setParameter("idPayments",id).getResultList();
            System.out.println(pay.toString());
        } catch (PersistenceException e) {
            throw new CredentialsException("No payment found");
        }
        if (pay.size()>0){return pay.get(0);}
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }
}

