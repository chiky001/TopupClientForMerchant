package vn.mog.topup.contract;

public class TopupTransactionRequest {
	private String username;
	private String apiCode;
	private String apiUsername;
	private String requestId;
	private String txnId;
	private String txnRequestId;
	private String serviceCode;
	private String phoneNumber;
	private Integer price;
	private Integer quantity;
	private String dataSign;

	public TopupTransactionRequest() {
	}

	public TopupTransactionRequest(String username, String apiCode, String apiUsername, String requestId,
			String serviceCode, String phoneNumber, Integer price, String dataSign) {
		this.username = username;
		this.apiCode = apiCode;
		this.apiUsername = apiUsername;
		this.requestId = requestId;
		this.serviceCode = serviceCode;
		this.phoneNumber = phoneNumber;
		this.price = price;
		this.dataSign = dataSign;
	}

	public TopupTransactionRequest(String username, String apiCode, String apiUsername, String requestId,
			String serviceCode, Integer price, Integer quantity, String dataSign) {
		this.username = username;
		this.apiCode = apiCode;
		this.apiUsername = apiUsername;
		this.requestId = requestId;
		this.serviceCode = serviceCode;
		this.price = price;
		this.quantity = quantity;
		this.dataSign = dataSign;
	}

	public String getUsername() {
		return username;
	}

	public String getApiCode() {
		return apiCode;
	}

	public String getApiUsername() {
		return apiUsername;
	}

	public String getRequestId() {
		return requestId;
	}

	public String getTxnId() {
		return txnId;
	}

	public String getTxnRequestId() {
		return txnRequestId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public void setTxnRequestId(String txnRequestId) {
		this.txnRequestId = txnRequestId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getDataSign() {
		return dataSign;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public void setApiUsername(String apiUsername) {
		this.apiUsername = apiUsername;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDataSign(String dataSign) {
		this.dataSign = dataSign;
	}

}
