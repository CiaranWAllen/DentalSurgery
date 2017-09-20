import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
public class Invoice implements Serializable{
	private int invoiceNo;
	private double invoiceAmt;
	private Date invoiceDate;
	private boolean isPaid;
	ArrayList<Procedure> inProcList = new ArrayList<Procedure>();
	ArrayList<Payment> inPaymentList = new ArrayList<Payment>();
	public Invoice(Date d){
		invoiceDate = d;
	}
	public int getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public double getInvoiceAmt(){
		return invoiceAmt;
	}
	public void setInvoiceAmt(double invoiceAmt){
		this.invoiceAmt = invoiceAmt;
	}
	public ArrayList<Procedure> getInProcList(){
		return inProcList;
	}
	public void setInProcList(ArrayList<Procedure> inProcList){
		this.inProcList = inProcList;
	}
	public ArrayList<Payment> getInPaymentList(){
		return inPaymentList;
	}
	public void setInPaymentList(ArrayList<Payment> inPaymentList){
		this.inPaymentList = inPaymentList;
	}
	public Date getInvoiceDate(){
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate){
		this.invoiceDate = invoiceDate;
	}
	public boolean getIsPaid(){
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid){
		this.isPaid = isPaid;
	}
	public String toString(){
		String str= "Date: " + invoiceDate + " Invoice Number: " + invoiceNo +  " "
				+ "Invoice Amount : " + invoiceAmt + " Paid? " + isPaid+""
						+ "\nProcedures:\t"+inProcList+"\nPayments:\t"+inPaymentList;
		return str;
	}
	void print(){
		System.out.println(toString());
	}
	public void setPayment(Payment new_In){
		this.inPaymentList.add(new_In);
	}
	public void setProcess(Procedure new_In){
		this.inProcList.add(new_In);
	}
	public void printPayment(){
		for(int x=0; x<inPaymentList.size(); x++)
		{
			System.out.println(inPaymentList.get(x));
		}
		
	}public void printProc(){
		for(int x=0; x<inProcList.size(); x++)
		{
			System.out.println(inProcList.get(x));
		}
		
	}
}
