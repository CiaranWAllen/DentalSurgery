import java.io.Serializable;
import java.util.Date;
public class Payment implements Serializable{
	private int paymentNum;
	private double paymentAmt;
	Date paymentDate;
	
	public int getPaymentNum() {
		return paymentNum;
	}
	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}
	public double getPaymentAmt(){
		return paymentAmt;
	}
	public void setPaymentAmt(double paymentAmt){
		this.paymentAmt = paymentAmt;
	}
	public Date getPaymentDate(){
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate){
		this.paymentDate = paymentDate;
	}
	public String toString(){
		String str= "Date: " + paymentDate + " Payment Number: " + paymentNum + " Payment Amount: " + paymentAmt;
		return str;
	}
	void print(){
		System.out.println(toString());
	}
}
