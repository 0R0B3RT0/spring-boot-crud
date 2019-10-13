import java.util.List;

import com.spring.springbootcrud.domain.entity.Person;

import integrationtest.RunCucumberTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaveValidPersonStep extends RunCucumberTest {

	List<Person> persons;

	@Given("the persons with these fields")
	public void thePersonsWithTheseFields(List<Person> persons) {
		this.persons = persons;
		System.out.println(persons);
	}


	@When("the register of this product is requested")
	public void theRegisterOfThisProductIsRequested() {
		System.out.println(persons);
	}

	@Then("the result should be a persisted person")
	public void theResultShouldBeAPersistedPerson() {
		System.out.println(persons);
	}

}
