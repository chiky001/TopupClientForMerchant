package vn.mog.topup;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.web.client.RestTemplate;

import vn.mog.topup.contract.TopupTransactionRequest;
import vn.mog.topup.contract.TopupTransactionResponse;
import vn.mog.topup.util.HttpUtil;
import vn.mog.topup.util.SecurityUtil;

public class DefaultTopupClient implements TopupClient {

	public static String PUBLIC_KEY = "/home/chiky/key/public.der";
	public static String PRIVATE_KEY = "/home/chiky/key_qa/private.der";
	public static String USER_NAME = "merchant@truemoney.com.vn";
	public static String API_CODE = "255888";
	public static String API_USER_NAME = "acggq6c33ngsqgfqh262";
	public static String API_PASSWORD = "m2vfr1qpuqqlbkexaffspq990fls3wbx";
	public static String URL =  "http://qa.mytopup.truemoney.com.vn/topup/api/service/requestTransaction";

	public TopupTransactionResponse topup(TopupTransactionRequest request) {
		TopupTransactionResponse response = HttpUtil.postForObject(URL, request, TopupTransactionResponse.class);
		return response;
	}

	public TopupTransactionResponse softpin(TopupTransactionRequest request) {
		TopupTransactionResponse response = HttpUtil.postForObject(URL, request, TopupTransactionResponse.class);
		return response;
	}

	public TopupTransactionResponse topup(String serviceCode, String phoneNumber, Integer price, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest(USER_NAME, API_CODE, API_USER_NAME, requestId,
				serviceCode, phoneNumber, price, dataSign);
		return topup(request);
	}

	public TopupTransactionResponse softpin(String serviceCode, Integer price, Integer quantity, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest(USER_NAME, API_CODE, API_USER_NAME, requestId,
				serviceCode, price, quantity, dataSign);
		return softpin(request);
	}

	public TopupTransactionResponse checkBalance(String serviceCode, String requestId) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest();
		request.setUsername(USER_NAME);
		request.setApiCode(API_CODE);
		request.setApiUsername(API_USER_NAME);
		request.setServiceCode(serviceCode);
		request.setRequestId(requestId);
		request.setDataSign(dataSign);
		return HttpUtil.postForObject(URL, request, TopupTransactionResponse.class);
	}

	public TopupTransactionResponse getTransaction(String serviceCode, String requestId, String txnIdOrTxnRequestId,
			boolean isTxnId) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			SignatureException, IOException {
		String dataSign = createSign(serviceCode, requestId);
		TopupTransactionRequest request = new TopupTransactionRequest();
		request.setUsername(USER_NAME);
		request.setApiCode(API_CODE);
		request.setApiUsername(API_USER_NAME);
		request.setServiceCode(serviceCode);
		request.setRequestId(requestId);
		if (isTxnId)
			request.setTxnId(txnIdOrTxnRequestId);
		else
			request.setTxnRequestId(txnIdOrTxnRequestId);
		request.setDataSign(dataSign);
		return HttpUtil.postForObject(URL, request, TopupTransactionResponse.class);
	}

	private String createSign(String serviceCode, String requestId) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(USER_NAME).append("|").append(API_CODE).append("|").append(API_USER_NAME).append("|")
				.append(serviceCode).append("|").append(requestId);
		String plainText = sb.toString();
		String dataSign = SecurityUtil.createSign(plainText, PRIVATE_KEY);
		return dataSign;
	}
}
