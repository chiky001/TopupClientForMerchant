package vn.mog.topup.contract;

import vn.mog.topup.contract.beans.Transaction;

public class TopupTransactionResponse {
	private String transactionId;
	private String encryptCards;
	private Double balance;
	private Transaction transaction;
	private String dataSign;
	private Status status;

	public TopupTransactionResponse() {

	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getEncryptCards() {
		return encryptCards;
	}

	public Double getBalance() {
		return balance;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public String getDataSign() {
		return dataSign;
	}

	public Status getStatus() {
		return status;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setEncryptCards(String encryptCards) {
		this.encryptCards = encryptCards;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void setDataSign(String dataSign) {
		this.dataSign = dataSign;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public class Status {
		private int code;
		private String value;

		public Status() {
		}

		public Status(int code, String value) {
			this.code = code;
			this.value = value;
		}

		public int getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
