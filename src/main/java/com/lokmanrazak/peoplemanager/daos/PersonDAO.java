package com.lokmanrazak.peoplemanager.daos;

import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Person create(Person person) {
        return persist(person);
    }

    @SuppressWarnings("unchecked")
    public List<Person> listPeople() {
        return list((Query<Person>) namedQuery("com.lokmanrazak.peoplemanager.models.Person.listPeople"));
    }
}
