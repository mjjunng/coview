package coview.coview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("coview.coview")
public class CoviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoviewApplication.class, args);
	}

}
