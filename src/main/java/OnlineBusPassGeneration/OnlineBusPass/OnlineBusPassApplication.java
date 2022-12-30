package OnlineBusPassGeneration.OnlineBusPass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineBusPassApplication {

	public static void main(String[] args) {
		System.out.println("Application Started Running");
		SpringApplication.run(OnlineBusPassApplication.class, args);
		System.out.println("Application Running over");
	}
}