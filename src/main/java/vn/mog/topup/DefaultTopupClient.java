package vn.mog.topup;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import vn.mog.topup.contract.TopupTransactionRequest;
import vn.mog.topup.contract.TopupTransactionResponse;
import vn.mog.topup.util.HttpUtil;
import vn.mog.topup.util.SecurityUtil;

public class DefaultTopupClient implements TopupClient {

	public static String DEFAULT_PUBLIC_KEY = "/home/chiky/key/public.der";
	public static String DEFAULT_PRIVATE_KEY = "/home/chiky/key_qa/private.der";
	public static String DEFAULT_USER_NAME = "merchant@truemoney.com.vn";
	public static String DEFAULT_API_CODE = "255888";
	public static String DEFAULT_API_USER_NAME = "acggq6c33ngsqgfqh262";
	public static String DEFAULT_API_PASSWORD = "m2vfr1qpuqqlbkexaffspq990fls3wbx";
	public static String DEFAULT_URL = "http://qa.mytopup.truemoney.com.vn/topup/api/service/requestTransaction";

	private String publicKey;
	private String privateKey;
	private String userName;
	private String apiCode;
	private String apiUserName;
	private String apiPassword;
	private String url;

	public DefaultTopupClient() {
		this.publicKey = DEFAULT_PUBLIC_KEY;
		this.privateKey = DEFAULT_PRIVATE_KEY;
		this.userName = DEFAULT_USER_NAME;
		this.apiCode = DEFAULT_API_CODE;
		this.apiUserName = DEFAULT_API_USER_NAME;
		this.apiPassword = DEFAULT_API_PASSWORD;
		this.url = DEFAULT_URL;
	}

	public DefaultTopupClient(String publicKey, String privateKey, String userName, String apiCode, String apiUserName,
			String apiPassword, String url) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.userName = userName;
		this.apiCode = apiCode;
		this.apiUserName = apiUserName;
		this.apiPassword = apiPassword;
		this.url = url;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getUserName() {
		return userName;
	}

	public String getApiCode() {
		return apiCode;
	}

	public String getApiUserName() {
		return apiUserName;
	}

	public String getApiPassword() {
		return apiPassword;
	}

	public String getUrl() {
		return url;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public void setApiUserName(String apiUserName) {
		this.apiUserName = apiUserName;
	}

	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TopupTransactionResponse topup(TopupTransactionRequest request) {
		TopupTransactionResponse response = HttpUtil.postForObject(this.url, request, TopupTransactionResponse.class);
		return response;
	}

	public TopupTransactionResponse softpin(TopupTransactionRequest request) {
		TopupTransactionResponse response = HttpUtil.postForObject(this.url, request, TopupTransactionResponse.class);
		return response;
	}

	public TopupTransactionResponse topup(String serviceCode, String phoneNumber, Integer price, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest(this.userName, this.apiCode, this.apiUserName,
				requestId, serviceCode, phoneNumber, price, dataSign);
		return topup(request);
	}

	public TopupTransactionResponse softpin(String serviceCode, Integer price, Integer quantity, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest(this.userName, this.apiCode, this.apiUserName,
				requestId, serviceCode, price, quantity, dataSign);
		return softpin(request);
	}

	public TopupTransactionResponse checkBalance(String serviceCode, String requestId) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest();
		request.setUsername(this.userName);
		request.setApiCode(this.apiCode);
		request.setApiUsername(this.apiUserName);
		request.setServiceCode(serviceCode);
		request.setRequestId(requestId);
		request.setDataSign(dataSign);
		return HttpUtil.postForObject(this.url, request, TopupTransactionResponse.class);
	}

	public TopupTransactionResponse getTransaction(String serviceCode, String requestId, String txnIdOrTxnRequestId,
			boolean isTxnId) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			SignatureException, IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest();
		request.setUsername(this.userName);
		request.setApiCode(this.apiCode);
		request.setApiUsername(this.apiUserName);
		request.setServiceCode(serviceCode);
		request.setRequestId(requestId);
		if (isTxnId)
			request.setTxnId(txnIdOrTxnRequestId);
		else
			request.setTxnRequestId(txnIdOrTxnRequestId);
		request.setDataSign(dataSign);
		return HttpUtil.postForObject(this.url, request, TopupTransactionResponse.class);
	}

	private String createSign(String serviceCode, String requestId) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(this.userName).append("|").append(this.apiCode).append("|").append(this.apiUserName).append("|")
				.append(serviceCode).append("|").append(requestId);
		String plainText = sb.toString();
		String dataSign = SecurityUtil.createSign(plainText, this.privateKey);
		return dataSign;
	}
}
