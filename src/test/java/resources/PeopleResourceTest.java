package resources;

import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Person;
import com.lokmanrazak.peoplemanager.resources.PeopleResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class PeopleResourceTest {
    private static final PersonDAO DAO = mock(PersonDAO.class);
    public static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new PeopleResource(DAO))
            .build();

    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person();
        person.setId(1L);
    }

    @AfterEach
    public void tearDown() {
        reset(DAO);
    }

    @Test
    public void addPerson() {
        when(DAO.insert(any(Person.class))).thenReturn(person);

        Response response = EXT.target("/people")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void updatePerson() {
        Response response = EXT.target("/people")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void deletePerson() {
        Response response = EXT.target("/people/1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void listPeople() {
        List<Person> people = Collections.singletonList(person);
        when(DAO.findAll()).thenReturn(people);

        List<Person> response = EXT.target("/people")
                .request().get(new GenericType<>() {
                });

        assertThat(response).containsAll(people);
    }
}
