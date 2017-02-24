package vn.mog.topup;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import vn.mog.topup.contract.TopupTransactionRequest;
import vn.mog.topup.contract.TopupTransactionResponse;

public interface TopupClient {

	TopupTransactionResponse topup(TopupTransactionRequest request);

	TopupTransactionResponse topup(String serviceCode, String phoneNumber, Integer price, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException;

	TopupTransactionResponse softpin(TopupTransactionRequest request);

	TopupTransactionResponse softpin(String serviceCode, Integer price, Integer quantity, String requestId)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException;

	TopupTransactionResponse checkBalance(String serviceCode, String requestId) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException;

	TopupTransactionResponse getTransaction(String serviceCode, String requestId, String txnIdOrTxnRequestId,
			boolean isTxnId) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			SignatureException, IOException;

}
