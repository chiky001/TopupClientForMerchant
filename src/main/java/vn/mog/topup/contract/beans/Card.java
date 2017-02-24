package vn.mog.topup.contract.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Card {
	private String serial;
	private String pin;
	private Integer price;
	private String cardType;
	private Date expiredDate;

	public Card() {

	}

	public String getSerial() {
		return serial;
	}

	public String getPin() {
		return pin;
	}

	public Integer getPrice() {
		return price;
	}

	public String getCardType() {
		return cardType;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	@Override
	public String toString(){
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return this.cardType + " - " + this.price + " - " + this.serial + " - " + this.pin + " - " + df.format(this.expiredDate);
	}

}
