package at.spengergasse.at.safeapi;

import at.spengergasse.at.safeapi.fhir.ICommunicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class CommunicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ICommunicationRepository communicationRepository;

    @Test
    public void testReturnAll() throws Exception {
        mockMvc
                .perform(get("/communication/"))
                .andExpect(status().isOk());
    }


		/*
new Communication(
                      null,
							  2,
					  LocalDate.of(2006,05,07), true,
			new IdentifierUse(null, IdentifierUse.Useable.usual, "type", "value"),
                        new HumanName(null, HumanName.Useable.nickname),
                        new ContactPoint(null, ContactPoint.System.email, ContactPoint.Useable.mobile, "value")
              ));

	@Test
	public void testGetAllCommmunication() throws Exception {   // keine Zeit mehr
		mockMvc
				.perform(get("/communication/"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("[" 2,
						LocalDate.of(2006,05,07), true,
						new IdentifierUse(null, IdentifierUse.Useable.usual, "type", "value"])));
	}
*/
    //----------------------------------------------------------------------------------------

}
