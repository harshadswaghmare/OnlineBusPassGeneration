package OnlineBusPassGeneration.OnlineBusPass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineBusPassApplication {
	static Logger log = LoggerFactory.getLogger(OnlineBusPassApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineBusPassApplication.class, args);
		log.info("Welcome to the Online Bus Pass System");

	}
}