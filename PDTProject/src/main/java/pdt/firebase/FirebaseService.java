package pdt.firebase;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import pdt.entity.User;

@Service
public class FirebaseService {

	public String saveUserDetails(User user) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<com.google.cloud.firestore.WriteResult> collectionApiFuture = dbFirestore.collection("user")
				.document(user.getUserId()).set(user);
		return collectionApiFuture.get().getUpdateTime().toString();
	}

	public User getUserDetails(String userId) throws InterruptedException, ExecutionException {
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("user").document(userId);
		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		User user = null;

		if (document.exists()) {
			user = document.toObject(User.class);
			return user;
		} else {
			return null;
		}

	}
	
//	public List<User> getAllUser() throws InterruptedException, ExecutionException{
//		
//		Firestore dbFirestore = FirestoreClient.getFirestore();
//		DocumentReference documentReference = dbFirestore.collection("user").document();
//		ApiFuture<DocumentSnapshot> future = documentReference.get();
//		
//		DocumentSnapshot document = future.get();
//		
//		List<User> userList = null;
//		if (document.exists()) {
//			userList = document;
//			return user;
//		} else {
//			return null;
//		}
//		
//		
//	}

}
