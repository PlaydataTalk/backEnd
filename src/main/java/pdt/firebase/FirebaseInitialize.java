package pdt.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseInitialize {
	
	@PostConstruct
	public void initialize() {
		
		try {

		FileInputStream serviceAccount = new FileInputStream("/Users/iseha/Downloads/playdatatalk-firebase-adminsdk-znqdh-378e37af4e.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://playdatatalk-default-rtdb.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
