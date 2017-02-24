package vn.mog.topup.main.example;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import vn.mog.topup.DefaultTopupClient;
import vn.mog.topup.TopupClient;
import vn.mog.topup.contract.TopupTransactionResponse;
import vn.mog.topup.util.SecurityUtil;

public class TopupClientTest {
	public static void main(String[] args) {
		
		TopupClient topupClient = new DefaultTopupClient();

		// Nap tien truc tiep cho mot so dien thoai cu the
		topupTelco(topupClient);

		// Mua ma the dien thoai
//		softpin(topupClient);
		
		// Kiem tra tai khoan
//		checkBalance(topupClient);
		
		// Query chi tiet Transaction
//		getTransaction(topupClient);


	}
	
	private static void topupTelco(TopupClient topupClient){
		try {
			System.out.println("--------- TOPUP TELCO - NAP TIEN CHO SO DIEN THOAI TRA TRUOC -------- ");
			String phoneNumber = "0123456789";
			Integer price = 10000;
			String serviceCode = "1PAY0010";
			String requestId = String.valueOf(System.currentTimeMillis());
			TopupTransactionResponse response = topupClient.topup(serviceCode, phoneNumber, price, requestId);
			displayResutlTopupService(response);

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static void softpin(TopupClient topupClient){
		Integer price = 10000;
		Integer quantity = 5;
		String serviceCode = "1PAY0058";
		String requestId = String.valueOf(System.currentTimeMillis());
		try {
			System.out.println("--------- SOFTPIN - MUA MA THE DIEN THOAI -------- ");
			TopupTransactionResponse response = topupClient.softpin(serviceCode, price, quantity, requestId);
			displayResustSoftpinService(response);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private static void checkBalance(TopupClient topupClient){
		String serviceCode = "1PAY0060";
		String requestId = String.valueOf(System.currentTimeMillis());
		try {
			System.out.println("--------- TRUY VAN SO DU -------- ");
			TopupTransactionResponse response = topupClient.checkBalance(serviceCode, requestId);
			if (response.getStatus().getCode() == 0) {
				String sign = response.getDataSign();
				String plainText = response.getStatus().getCode() + "|" + response.getBalance();
				//if (SecurityUtil.verifySign(sign, plainText, DefaultTopupClient.PUBLIC_KEY)) {
					if (1 == 3) {
					System.out.println(
							"Du lieu tra ve dang nghi.");
				}
				else{
					System.out.println("Giao dich thanh cong.So du : " + response.getBalance().doubleValue());
				}
			} else {
				System.out.println("Co loi xay ra trong qua trinh giao dich : ");
				System.out.println("Ma loi : " + response.getStatus().getCode());
				System.out.println("Mo ta loi : " + response.getStatus().getValue());
				System.out.println("Ma giao dich (neu co) : " + response.getTransactionId());
			}
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getTransaction(TopupClient topupClient){
		String serviceCode = "1PAY0050";
		String requestId = String.valueOf(System.currentTimeMillis());
		String txnIdOrTxnRequestId = "";
		boolean isTxnId = true;
		try {
			TopupTransactionResponse response = topupClient.getTransaction(serviceCode, requestId, txnIdOrTxnRequestId, isTxnId);
			if (response.getStatus().getCode() == 0) {
				String sign = response.getDataSign();
				String plainText = response.getStatus().getCode() + "|" + response.getBalance();
				if (SecurityUtil.verifySign(sign, plainText, DefaultTopupClient.PUBLIC_KEY)) {
					System.out.println(
							"Du lieu tra ve dang nghi.");
				}
				else{
					System.out.println("Giao dich thanh cong.Chi tiet : \n" + response.getTransaction().toString());
				}
			} else {
				System.out.println("Co loi xay ra trong qua trinh giao dich : ");
				System.out.println("Ma loi : " + response.getStatus().getCode());
				System.out.println("Mo ta loi : " + response.getStatus().getValue());
				System.out.println("Ma giao dich (neu co) : " + response.getTransactionId());
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void displayResutlTopupService(TopupTransactionResponse response) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		if (response.getStatus().getCode() == 0) {
			String sign = response.getDataSign();
			String plainText = response.getStatus().getCode() + "|" + response.getTransactionId();
			if (SecurityUtil.verifySign(sign, plainText, DefaultTopupClient.PUBLIC_KEY)) {
				System.out.println(
						"Du lieu tra ve dang nghi.");
			}
			else{
				System.out.println("Giao dich thanh cong.");
			}
		} else {
			System.out.println("Co loi xay ra trong qua trinh giao dich : ");
			System.out.println("Ma loi : " + response.getStatus().getCode());
			System.out.println("Mo ta loi : " + response.getStatus().getValue());
			System.out.println("Ma giao dich (neu co) : " + response.getTransactionId());
		}

	}

	private static void displayResustSoftpinService(TopupTransactionResponse response) throws Exception {
		if (response.getStatus().getCode() == 0) {
			String sign = response.getDataSign();
			String plainText = response.getStatus().getCode() + "|" + response.getTransactionId();
			if (SecurityUtil.verifySign(sign, plainText, DefaultTopupClient.PUBLIC_KEY)) {
				System.out.println(
						"Du lieu tra ve dang nghi.");
			}
			else{
				System.out.println("Giao dich thanh cong.Danh sach ma the : ");
				String encryptedCard = response.getEncryptCards();
				String listcard = SecurityUtil.decrypt(DefaultTopupClient.API_PASSWORD, encryptedCard);
				String[] spiltedCards = listcard.split(";");
				System.out.println("MA THE \t MENH GIA \t PIN \t SERIAL \t NGAY HET HAN");
				for(int i = 0; i<spiltedCards.length;i++){
					String cardStr = spiltedCards[i];
					String[] cards = cardStr.split("\\|");
					System.out.print(cards[0]+" \t "+cards[1] +" \t "+ cards[2] + " \t "+cards[3] );
				}
 			}
		} else {
			System.out.println("Co loi xay ra trong qua trinh giao dich : ");
			System.out.println("Ma loi : " + response.getStatus().getCode());
			System.out.println("Mo ta loi : " + response.getStatus().getValue());
			System.out.println("Ma giao dich (neu co) : " + response.getTransactionId());
		}		
	}
}
