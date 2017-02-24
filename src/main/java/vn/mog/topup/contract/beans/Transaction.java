package vn.mog.topup.contract.beans;

import java.util.Date;
import java.util.List;

public class Transaction {
	private String id;
	private String cif;
	private Long amount;
	private Integer commision;
	private Integer fee;
	private Integer price;
	private Integer quantity;
	private Long realAmount;
	private Long lastBalance;
	private Long currentBalance;
	private String info;
	private String serviceType;
	private String subject;
	private Integer errorCode;
	private String errorMessage;
	private String transactionStatus;
	private Date creationDate;
	private String serviceCode;
	private String serviceName;
	private String telcoType;
	private List<Card> serials;

	public Transaction() {

	}

	public String getId() {
		return id;
	}

	public String getCif() {
		return cif;
	}

	public Long getAmount() {
		return amount;
	}

	public Integer getCommision() {
		return commision;
	}

	public Integer getFee() {
		return fee;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Long getRealAmount() {
		return realAmount;
	}

	public Long getLastBalance() {
		return lastBalance;
	}

	public Long getCurrentBalance() {
		return currentBalance;
	}

	public String getInfo() {
		return info;
	}

	public String getServiceType() {
		return serviceType;
	}

	public String getSubject() {
		return subject;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getTelcoType() {
		return telcoType;
	}

	public List<Card> getSerials() {
		return serials;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public void setCommision(Integer commision) {
		this.commision = commision;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setRealAmount(Long realAmount) {
		this.realAmount = realAmount;
	}

	public void setLastBalance(Long lastBalance) {
		this.lastBalance = lastBalance;
	}

	public void setCurrentBalance(Long currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setTelcoType(String telcoType) {
		this.telcoType = telcoType;
	}

	public void setSerials(List<Card> serials) {
		this.serials = serials;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"ID \t CIF \t AMOUNT \t COMMISION \t FEE \t PRICE \t QUANTITY \t REALAMOUNT \t LASTBALANCE \t CURRENTBALANCE \t DATE \n");
		sb.append(this.id).append("\t").append(this.cif).append("\t").append(this.amount).append("\t")
				.append(this.commision).append("\t").append(this.fee).append("\t").append(this.price).append("\t")
				.append(this.quantity).append("\t").append(this.realAmount).append("\t").append(this.lastBalance)
				.append("\t").append(this.currentBalance).append("\t").append(this.creationDate).append("\n");
		sb.append("DANH SACH THE \n");
		if(this.serials != null && this.serials.size() > 0){
			for(int i=0 ; i < this.serials.size() ; i++){
				sb.append(this.serials.get(i).toString()).append("\n");
			}
		}
		return sb.toString();
	}

}
