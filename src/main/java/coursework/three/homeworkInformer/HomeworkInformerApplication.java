package coursework.three.homeworkInformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomeworkInformerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkInformerApplication.class, args);
	}

}
