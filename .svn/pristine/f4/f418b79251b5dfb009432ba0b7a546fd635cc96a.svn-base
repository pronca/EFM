package it.eng.care.domain.flow.crypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.eng.care.domain.flow.core.utility.LogUtil;

@Component
public class CryptoManagerImpl implements CryptoManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CryptoManagerImpl.class);
	
	private String algorithm = "AES/CBC/PKCS5Padding";

	Cipher cypherEncrypt;
	Cipher cypherDecrypt;

	boolean enableCrypto = false;

	private String stringEncoding = "UTF-8";

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public CryptoManagerImpl(boolean enableCrypto) {
		super();
		this.enableCrypto = enableCrypto;
	}

	@Override
	public void setPassword(String password) {
		try {

			SecretKey key = new SecretKeySpec(password.getBytes(), "AES");

			byte[] iv = new byte[16];
			byte b = 0;
			Arrays.fill(iv, b);

			BouncyCastleProvider provider = new BouncyCastleProvider();
			cypherEncrypt = Cipher.getInstance(algorithm, provider);
			cypherDecrypt = Cipher.getInstance(algorithm, provider);

			cypherEncrypt.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			cypherDecrypt.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Object encryptObject(Object data) {
		if (data instanceof String) {
			return encryptString((String) data);
		} else if (data instanceof Number) {
			return encryptNumber((Number)data);
		} else if (data instanceof Date) {
			return encryptDate((Date) data);
		} else if (data instanceof byte[]) {
			return encrypt((byte[]) data);
		}
		
		return null;
	}

	@Override
	public byte[] encrypt(byte[] data) {
		if (data != null) {
			try {
				return cypherEncrypt.doFinal(data);
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "", e);
				throw new RuntimeException(e);
			}
		} else {
			return data;
		}
	}

	@Override
	public byte[] decrypt(byte[] data) {
		if (data != null) {
			try {
				return cypherDecrypt.doFinal(data);
			} catch (Exception e) {
				LogUtil.logException(LOGGER, "", e);
				throw new RuntimeException(e);
			}
		} else {
			return data;
		}
	}

	@Override
	public String encryptString(String data) {
		if (enableCrypto) {
			try {
				if (data != null) {
					return Hex.encodeHexString(encrypt(data.getBytes(stringEncoding))).toUpperCase();
				} else {
					return data;
				}
			} catch (UnsupportedEncodingException e) {
				LogUtil.logException(LOGGER, "", e);
				
				throw new RuntimeException(e);
			}
		} else {
			return data;
		}
	}

	@Override
	public String decryptString(String data) {
		if (enableCrypto) {
			try {
				if (data != null) {
					return new String(decrypt(Hex.decodeHex(data.toLowerCase().toCharArray())), stringEncoding);
				} else {
					return data;
				}
			} catch (DecoderException e) {
				throw new RuntimeException(e);
			} catch (UnsupportedEncodingException e) {
				LogUtil.logException(LOGGER, "", e);
				
				throw new RuntimeException(e);
			}
		} else {
			return data;
		}
	}

	@Override
	public String encryptDate(Date data) {
		return encryptString(dateFormat.format(data));
	}

	@Override
	public Date decryptDate(String data) throws ParseException {
		return dateFormat.parse(encryptString(data));
	}

	@Override
	public String encryptNumber(Number data) {
		return encryptString(data.toString());
	}

	@Override
	public Integer decryptInteger(Integer data) {
		//return Integer.parseInt(decryptString(data));
		return data;
	}

	@Override
	public Float decryptFloat(Float data) {
		//return Float.parseFloat(decryptString(data));
		return data;
	}

	@Override
	public Double decryptDouble(Double data) {
		//return Double.parseDouble(decryptString(data));
		return data;
	}

	@Override
	public Long decryptLong(Long data) {
		//return Long.parseLong(decryptString(data));
		return data;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getStringEncoding() {
		return stringEncoding;
	}

	public void setStringEncoding(String stringEncoding) {
		this.stringEncoding = stringEncoding;
	}
	
	@Override
	public Object decryptObject(Object data) {
		if(data != null) {
			if (data instanceof String) {
				return decryptString((String) data);
			} else if (data instanceof Integer) {
				return decryptInteger((Integer) data);
			} else if (data instanceof Float) {
				return decryptFloat((Float) data);
			} else if (data instanceof Double) {
				return decryptDouble((Double) data);
			} else if (data instanceof Long) {
				return decryptLong((Long) data);
			}
		}
		return null;
	}

}
