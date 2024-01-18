package jpabook.japshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JapshopApplication {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();

		SpringApplication.run(JapshopApplication.class, args);
	}


}
