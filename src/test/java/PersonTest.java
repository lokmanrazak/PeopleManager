import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lokmanrazak.peoplemanager.models.Person;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJson() throws JsonProcessingException {
        Person person = new Person("David", "Green");

        String result = MAPPER.writeValueAsString(MAPPER.readValue(fixture("jsons/person.json"), Person.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(result);
    }
}
