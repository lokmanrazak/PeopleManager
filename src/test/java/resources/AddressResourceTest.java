package resources;

import com.lokmanrazak.peoplemanager.daos.AddressDAO;
import com.lokmanrazak.peoplemanager.daos.PersonDAO;
import com.lokmanrazak.peoplemanager.models.Address;
import com.lokmanrazak.peoplemanager.models.Person;
import com.lokmanrazak.peoplemanager.resources.AddressResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AddressResourceTest {
    private static final PersonDAO PERSON_DAO = mock(PersonDAO.class);
    private static final AddressDAO ADDRESS_DAO = mock(AddressDAO.class);
    public static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new AddressResource(ADDRESS_DAO, PERSON_DAO))
            .build();

    private Address address;
    private Person person;

    @BeforeEach
    public void setup() {
        address = new Address();
        address.setId(1L);

        person = new Person();
        person.setId(1L);
    }

    @AfterEach
    public void tearDown() {
        reset(PERSON_DAO, ADDRESS_DAO);
    }

    @Test
    public void addAddress() {
        when(PERSON_DAO.findById(anyLong())).thenReturn(person);
        when(ADDRESS_DAO.insert(any(Address.class))).thenReturn(address);

        Response response = EXT.target("/address/1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(address, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void updateAddress() {
        when(ADDRESS_DAO.getPerson(anyLong())).thenReturn(person);

        Response response = EXT.target("address")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(address, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }

    @Test
    public void deleteAddress() {
        when(ADDRESS_DAO.findById(anyLong())).thenReturn(address);

        Response response = EXT.target("/address/1")
                .request()
                .delete();

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
    }
}
