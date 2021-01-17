package com.lokmanrazak.peoplemanager.resources;

import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Person createPerson(@Valid Person person) {
        return personDAO.create(person);
    }

    @GET
    @Path("/list")
    @UnitOfWork
    public List<Person> listPeople() {
        return personDAO.listPeople();
    }
}
