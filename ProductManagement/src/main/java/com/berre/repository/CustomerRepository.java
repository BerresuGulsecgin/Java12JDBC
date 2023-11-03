package com.berre.repository;

import com.berre.entity.Customer;
import com.berre.util.MyRepositoryFactory;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class CustomerRepository extends MyRepositoryFactory<Customer,Long> {
    public CustomerRepository() {
        super(Customer.class);
    }

    public Optional<Customer> findCustomerByIdentity(String identity) {
        openSession();
        CriteriaQuery<Customer> criteriaQuery=getCriteriaBuilder().createQuery(Customer.class);
        Root<Customer> root=criteriaQuery.from(Customer.class);
        criteriaQuery.select(root).where(getCriteriaBuilder().equal(root.get("identity"),identity));


           List<Customer> singleResult = getSession().createQuery(criteriaQuery).getResultList();
            closeSession();

        //return Optional.ofNullable(singleResult.get(0));
        return singleResult.isEmpty()? Optional.empty(): Optional.of(singleResult.get(0));
    }
}
