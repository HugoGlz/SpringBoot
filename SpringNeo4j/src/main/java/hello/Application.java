package hello;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	CommandLineRunner demo(PersonRepository personRepository){
		return args -> {
			
			Person jugo = new Person("Jugo");
			Person toto = new Person("Toto");
			Person tucan = new Person("Tucan");
			
			List<Person> team = Arrays.asList(jugo, toto, tucan);
			
			team.stream().forEach(person -> logger.info(String.format("\nPerson(%s)", person.getName())));
			
			personRepository.save(jugo);
			personRepository.save(toto);
			personRepository.save(tucan);
			
			jugo = personRepository.findByName("Jugo");
			jugo.worksWith(toto);
			jugo.worksWith(tucan);
			personRepository.save(jugo);
			

			toto = personRepository.findByName("Toto");
			toto.worksWith(jugo);
			personRepository.save(toto);

			logger.info("Lookup each person by name");
			
			team.stream().forEach(person -> {
				Person temp = personRepository.findByName(person.getName());
				logger.info(String.format("Person info ====> %s",temp.toString()));
			});
		};
	}
}
