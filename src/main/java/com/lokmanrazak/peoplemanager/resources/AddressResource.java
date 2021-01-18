package com.lokmanrazak.peoplemanager.resources;

import com.lokmanrazak.peoplemanager.daos.AddressDAO;
import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Address;
import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

    private final AddressDAO addressDAO;
    private final PersonDAO personDAO;

    public AddressResource(AddressDAO addressDAO, PersonDAO personDAO) {
        this.addressDAO = addressDAO;
        this.personDAO = personDAO;
    }

    @POST
    @Path("/{people_id}")
    @UnitOfWork
    public Address add(@PathParam("people_id") long peopleId, @Valid Address address) {
        Person person = personDAO.findById(peopleId);
        address.setPerson(person);

        return addressDAO.insert(address);
    }

    @PUT
    @UnitOfWork
    public Address update(@Valid Address address) {
        Person person = addressDAO.getPerson(address.getId());
        address.setPerson(person);
        addressDAO.update(address);

        return address;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") long id) {
        addressDAO.delete(addressDAO.findById(id));
    }
}
