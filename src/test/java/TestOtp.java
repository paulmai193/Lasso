
/*
 * 
 */
import com.lasso.util.EncryptionUtil;

/**
 * The Class TestOtp.
 *
 * @author Paul Mai
 */
public class TestOtp {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException {
		for (int _i = 0; _i < 60; _i++) {
			System.out.println(EncryptionUtil.generateTOTP());
			Thread.sleep(1000);
		}

	}

}
