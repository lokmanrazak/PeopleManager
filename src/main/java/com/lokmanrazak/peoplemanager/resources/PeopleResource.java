package com.lokmanrazak.peoplemanager.resources;

import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final PersonDAO personDAO;

    public PeopleResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @POST
    @UnitOfWork
    public Person add(@Valid Person person) {
        return personDAO.insert(person);
    }

    @PUT
    @UnitOfWork
    public Person update(@Valid Person person) {
        personDAO.update(person);

        return person;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") long id) {
        personDAO.delete(personDAO.findById(id));
    }

    @GET
    @UnitOfWork
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @GET
    @Path("/count")
    @UnitOfWork
    public long count() {
        return personDAO.count();
    }
}
