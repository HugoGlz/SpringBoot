import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.BDDAssertions.then;


import hello.HelloWorldConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloWorldConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port = 0"})
public class HelloWorldConfigurationTest {

	@LocalServerPort
	private int port;
	
	@Value("${local.management.port}")
	private int mgt;
	
	@Autowired
	private RestTemplate testRestTemplate;
	
	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rowtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
						"http://localhost:" + this.port + "/hello-world", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.mgt + "/info", Map.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
}
