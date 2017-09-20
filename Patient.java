import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person implements Serializable{
	private int patientNum;
	ArrayList<Invoice> p_invoiceList = new ArrayList<Invoice>();
	Patient(String a, String b){
		super(a,b);
	}
	public int getPatientNum() {
		return patientNum;
	}
	public void setPatientNum(int patientNum) {
		this.patientNum = patientNum;
	}
	public ArrayList<Invoice> getP_InvoiceList(){
		return p_invoiceList;
	}
	public void setInvoiceList(ArrayList<Invoice> p_invoiceList){
		this.p_invoiceList = p_invoiceList;
	}
	public String toString(){
		String str= "Patient Number:" + patientNum + " Name: " + name + " Address: " + address+"Invocies:"+this.p_invoiceList;
		return str;
	}
	public void print(){
		System.out.println(toString());
	}
	public void setInvoice(Invoice new_In){
		this.p_invoiceList.add(new_In);
	}
	public void printList(){
		for(int x=0; x<p_invoiceList.size(); x++)
		{
			System.out.println(p_invoiceList.get(x));
		}
		
	}
	public int compareTo(Patient other){
		return name.compareTo(other.name);
	}

}
