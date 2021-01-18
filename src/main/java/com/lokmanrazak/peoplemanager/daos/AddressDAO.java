package com.lokmanrazak.peoplemanager.daos;

import com.lokmanrazak.peoplemanager.models.Address;
import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class AddressDAO extends AbstractDAO<Address> {
    public AddressDAO(SessionFactory factory) {
        super(factory);
    }

    public Address findById(long id) {
        return currentSession().get(Address.class, id);
    }

    public Person getPerson(long addressId) {
        return currentSession().get(Address.class, addressId).getPerson();
    }

    public Address insert(Address address) {
        return persist(address);
    }

    public void update(Address address) {
        currentSession().merge(address);
    }

    public void delete(Address address) {
        currentSession().delete(address);
    }
}
