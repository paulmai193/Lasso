import java.io.IOException;

import com.lasso.rest.model.push.PushData;
import com.lasso.rest.model.push.PushNotification;
import com.lasso.rest.model.push.SendPushRequest;
import com.lasso.rest.service.impl.ImplMessageManagement;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestFirebase {

	public static void main(String[] args) throws UnirestException, IOException {
		SendPushRequest _request = new SendPushRequest();
		_request.setTo(
		        "d9VIWu79nSo:APA91bEgnQUFto6k6wt8Oy0_f0VtZWKNJQt-Xid45r6_uth5r5GUC7mBkmDclOabCOdzd21wtYA4b7jZTurKenRwXTXCv-pBF5UIm9LZ0NanNU0JqiM-ZIj2Uf4dr_1ZnjXmzSWDn7Y0");
		_request.setNotification(
		        new PushNotification("Title kute thay push ko", "Content kute thay push ko"));
		new ImplMessageManagement().sendPush(_request);
	}

}

class Data implements PushData {

}
