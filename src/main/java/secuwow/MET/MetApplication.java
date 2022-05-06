package secuwow.MET;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import secuwow.MET.function.PeriodicTaskThread;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class MetApplication {

	public static void main(String[] args) {
		Timer timer = new Timer();
		PeriodicTaskThread timerTask = new PeriodicTaskThread();

		timer.schedule(timerTask, 60000, 60000);

		SpringApplication.run(MetApplication.class, args);
	}

}
