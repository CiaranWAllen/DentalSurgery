import java.io.Serializable;

public class Procedure implements Serializable{
	private int procNum;
	private String procName;
	private double procCost;
	public Procedure(String pN, double pC){
		//pN = procName;
		//pC = procCost;
		this.procName = pN;
		this.procCost = pC;
	}
	public int getProcNum() {
		return procNum;
	}
	public void setProcNum(int procNum) {
		this.procNum = procNum;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName){
		this.procName = procName;
	}
	public double getProcCost(){
		return procCost;
	}
	public void setProcCost(double procCost){
		this.procCost = procCost;
	}
	public String toString(){
		String str= "Procedure Number:" + procNum +" Procedure Name:" + procName + " Procedure Cost: " + procCost;
		return str;
	}
	void print(){
		System.out.println(toString());
	}
	
}
