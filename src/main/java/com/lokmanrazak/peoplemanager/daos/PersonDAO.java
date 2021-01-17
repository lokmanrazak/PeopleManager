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

    public Person findById(long id) {
        return currentSession().get(Person.class, id);
    }

    public Person insert(Person person) {
        return persist(person);
    }

    public void update(Person person) {
        currentSession().update(person);
    }

    public void delete(Person person) {
        currentSession().delete(person);
    }

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return list((Query<Person>) namedQuery("Person.listPeople"));
    }

    public long count() {
        return (Long) namedQuery("Person.countPeople").uniqueResult();
    }
}
