package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lokmanrazak.peoplemanager.models.Address;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJson() throws JsonProcessingException {
        Address address = new Address("Main Street", "Dublin City", "Dublin", "D1");

        String result = MAPPER.writeValueAsString(MAPPER.readValue(fixture("jsons/address.json"), Address.class));

        assertThat(MAPPER.writeValueAsString(address)).isEqualTo(result);
    }
}
