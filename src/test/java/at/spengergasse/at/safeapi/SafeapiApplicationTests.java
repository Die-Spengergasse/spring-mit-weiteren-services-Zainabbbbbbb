package at.spengergasse.at.safeapi;

import at.spengergasse.at.safeapi.fhir.Communication;
import at.spengergasse.at.safeapi.fhir.ICommunicationRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class SafeapiApplicationTests {
// https://www.hl7.org/fhir/patient.html
	//https://github.com/maximiliankraft/spring-security-guide/tree/showcase4A
	static Gson gson = new Gson();

	@Autowired
	MockMvc mockMvc;

	@Autowired UserRepository userRepository;
	@Autowired AuthorityRepositorz authorityRepositorz;
	@Autowired PermissionRepo permissionRepo;
	@Autowired TestRessourceRessourceRepo testRessourceRessourceRepo;
	@Autowired PermissionChecker permissionChecker;


	@Test
	public void testGetCommunicationById() throws Exception {
		mockMvc
				.perform(get("/communication/1"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("keine zeit mehr")));
	}

	@Test
	public void testAddCommunication() throws Exception {
		mockMvc
				.perform(get("/communication/add/routine/"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("keine zeit mehr")));
	}

	@Test
	public void testDeleteCommunication() throws Exception {
		mockMvc
				.perform(get("/communication/delete/1"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("Communication deleted")));
	}



	@Test
	void contextLoads() {
	}


	@Test
	void testRegisterNewUser() throws Exception {
		//GET http://localhost:8080/user/register/peter/123

		mockMvc.perform(get("/user/register/peter1/123")).andExpect(status().is(200));

		mockMvc.perform(get("/user/register/")).andExpect(status().is(404));

		mockMvc.perform(get("/user/register///")).andExpect(status().is(404));
	}

	@Test
	void testLoginNewUser() throws Exception {
		mockMvc.perform(get("/user/register/peter2/123")).andExpect(status().is(200));
		mockMvc.perform(get("/user/login/peter2/123")).andExpect(status().is(200));


		mockMvc.perform(get("/user/login/admin/admin")).andExpect(status().is(200));
	}


	@Test
	void testAddNewRessource() throws Exception {

			var initialResource = new TestRessource(null, "Test", "Test 2");
			var initialJson = gson.toJson(initialResource);
			var username = "peter";
			var password = "123";

			mockMvc.perform(get("/user/register/" + username + "/" + password))
							.andExpect(status().is(200));


		MvcResult creationResult = mockMvc.perform(put("/test/")
						.with(user(username).password(password))
						.content(initialJson)
						.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andReturn();
		// check if TestResource with id 100 is available
		TestRessource resultingResource = gson.fromJson(
				creationResult.getResponse().getContentAsString(),
				TestRessource.class
		);

		assert !Objects.equals(resultingResource.getId(), initialResource.getId());

		var queringResult = mockMvc.perform(get("/test/" + resultingResource.getId())
						.with(user(username).password(password)))
				.andExpect(status().isOk())
				.andReturn();

		TestRessource queryingResource = gson.fromJson(queringResult.getResponse().getContentAsString(), TestRessource.class);

		assert Objects.equals(initialResource.getTitle(), queryingResource.getTitle()) &&
				Objects.equals(initialResource.getDescription(), queryingResource.getDescription());

	}

	@Test
	void testGrantResource() throws Exception {

		//register users
		mockMvc.perform(get("/user/register/a/a")).andExpect(status().is(200));
		mockMvc.perform(get("/user/register/b/b")).andExpect(status().is(200));

		//create resource
		MvcResult creationResponse = mockMvc.perform(put("/test/")
						.with(user("a").password("a"))
						.contentType("application/json")
						.content("{\"id\": null, \"title\": \"Test\", \"description\": \"Test 2\"}"))
				.andExpect(status().isOk())
						.andReturn();


		TestRessource testRessourceResponse = gson.fromJson(
				creationResponse.getResponse().getContentAsString(),
				TestRessource.class);

		mockMvc.perform(put("/user/grant/b/WRITE/TestRessource/" + testRessourceResponse.getId())
						.with(user("a").password("a")))
								.andExpect(status().isOk());

		mockMvc.perform(get("/test/" + testRessourceResponse.getId())
						.with(user("b").password("b")))
				.andExpect(status().isOk());


	}



}
