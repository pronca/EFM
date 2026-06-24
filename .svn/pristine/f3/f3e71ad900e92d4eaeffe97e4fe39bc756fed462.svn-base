package it.eng.care.domain.flow.crypt;

import java.text.ParseException;
import java.util.Date;

public interface CryptoManager {

	void setPassword(String password);

	byte[] encrypt(byte[] data);

	byte[] decrypt(byte[] data);
	
	Object encryptObject(Object data);

	String encryptString(String data);

	String decryptString(String data);

	String encryptDate(Date data);

	Date decryptDate(String data) throws ParseException;

	String encryptNumber(Number data);

	Integer decryptInteger(Integer data);

	Float decryptFloat(Float data);

	Double decryptDouble(Double data);

	Long decryptLong(Long data);
	
	Object decryptObject(Object data);

}
