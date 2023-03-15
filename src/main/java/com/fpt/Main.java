package com.fpt;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
	@Autowired
	CustomerRepository customerRepository;
	
//	public Main(CustomerRepository customerRepository) {
//		this.customerRepository = customerRepository;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class,args); 
	}
	
	//API GET
	@GetMapping
	public List<Customer>getCustomers(){
		return customerRepository.findAll();
	}
	
	//API POST
	record NewCustomerRequest(
			String name,
			String email,
			Integer age
	){
		
	}
	
	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name);
		customer.setEmail(request.email);
		customer.setAge(request.age);
		customerRepository.save(customer);
	}
	
	//API UPDATE
	@PutMapping
	public void updateCustomer(@RequestParam("id") Integer id,@RequestBody NewCustomerRequest request){
		Customer customer = customerRepository.findById(id).get();
		customer.setName(request.name);
		customer.setEmail(request.email);
		customer.setAge(request.age);
		customerRepository.save(customer);
	}
	
	//API DELETE
	@DeleteMapping("{id}")
	public void DeleteCustomer(@PathVariable("id") Integer id){
		customerRepository.deleteById(id);
	}
	
	@GetMapping("/greet")
	public GreetResponse greet() {
		GreetResponse response = new GreetResponse(
				"Hello",
				List.of("Java","Golang","Javascript"),
				new Person("Alex",21,30000)
		);
		return response;
	}
	
	record Person(String name, int age, double savings) {
	
	}
	record GreetResponse(
			String greet,
			List<String> favProgrammingLanguages,
			Person person
	) {}
	
//	class GreetResponse{
//		private final String greet;
//
//	    GreetResponse(String greet) {
//			this.greet = greet;
//		}
//
//		public String getGreet() {
//			return greet;
//		}
//
//		@Override
//		public String toString() {
//			return "GreetResponse [greet=" + greet + "]";
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(greet);		 
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null || getClass() != obj.getClass())
//				return false;
//			GreetResponse other = (GreetResponse) obj;
//			return Objects.equals(greet, other.greet);
//		}
//
//	}
}
 