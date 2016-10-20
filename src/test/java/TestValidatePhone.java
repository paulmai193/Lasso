
/*
 * 
 */
import com.lasso.rest.model.variable.PhoneParam;

/**
 * The Class TestValidatePhone.
 *
 * @author Paul Mai
 */
public class TestValidatePhone {

	/**
	 * The main method.
	 *
	 * @param args
	 *        the arguments
	 */
	public static void main(String[] args) {
		PhoneParam _phoneParam = new PhoneParam("(+65) 9639 1");
		System.out.println(_phoneParam.toString());
	}

}
