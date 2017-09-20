import javafx.scene.text.Font;
import javafx.geometry.Insets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MainApplication extends Application{

	ArrayList<Patient> patientList = new ArrayList<Patient>();
	Tab tab1 = new Tab("Add Patient");
	Tab tab2 = new Tab("Remove Patient");
	Tab tab3 = new Tab("Display Patient");
	Tab tab4 = new Tab("Add Procedure");
	Tab tab5 = new Tab("Delete Procedure");
	Tab tab6 = new Tab("List Procedure");
	Tab tab7 = new Tab("Add Payment");
	Tab tab8 = new Tab("Manage Invoices");
	Tab tab9 = new Tab("Save and/or Quit");
	Tab tab10 = new Tab("Test");
	GridPane grid = new GridPane();

	int patientID = 0;
	int invoiceID = 0;
	int procedureID = 0;
	int paymentID = 0;
	Stage primary; //Stage declared globally to allow for closing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) { //Here I use a grid as this was how I intended to have the layout at the beginning. I later decided a vbox and borderpane method of GUI is better
		primary=primaryStage; 
		primaryStage.setTitle("Welcome to the Dentist Surgery");

		grid.setAlignment(Pos.CENTER);
		

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);


		Scene loginScene = new Scene(grid, 300, 275);
		primaryStage.setScene(loginScene);
		primaryStage.show();
		primaryStage.setMinHeight(750);
		primaryStage.setMinWidth(950);
		TabPane tab = new TabPane();
		tab.getTabs().addAll(tab1,tab2,tab3,tab4,tab5,tab6,tab7,tab8,tab9,tab10);
		Scene tabScene = new Scene(tab, 300, 275);

		btn.setOnAction(e->
		{
//			if(pw.getText)
//					{
//				
//					}
			primaryStage.setScene(tabScene);
		});
		loadList(); //My methods
		addPatient();
		deletePatient();
		listPatient();
		addProcedure();
		listProcedure();
		deleteProcedure();
		addPayment();
		invoiceManagement();
		saveAndQuit();
		test();
	}
	public void loadList(){ //Loads in patientList file
		try {
			FileInputStream fileIn = new FileInputStream("./patientlist.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			patientList = (ArrayList<Patient>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException a) {
			//a.printStackTrace();

		}catch(ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();

		}
	}
	public void addPatient(){ 
		//Add patient. I use a different layout here because it's easier
		BorderPane bp1 = new BorderPane();
		VBox vb1 = new VBox();

		Label patientNameLabel = new Label("Patient Name ");
		Label patientAddressLabel = new Label("Patient Address ");
		TextField patientNameField = new TextField();
		TextField patientNumField = new TextField();
		TextField patientAddressField = new TextField();
		Button addPatientButton = new Button("Add Patient");

		vb1.setAlignment(Pos.CENTER);
		vb1.getChildren().addAll(patientNameLabel, patientNameField, patientAddressLabel, patientAddressField, addPatientButton);
		vb1.setSpacing(20);
		patientNameField.setMaxWidth(150);
		patientNumField.setMaxWidth(150);
		patientAddressField.setMaxWidth(150);
		bp1.setCenter(vb1);
		tab1.setContent(bp1);
		addPatientButton.setOnAction(e->{ //here I add a patient and generate a patient number

			int size = patientList.size();

			try{
				patientID = patientList.get(size-1).getPatientNum()+1;

			}catch(IndexOutOfBoundsException c){
				patientID = 1;
			}
			try{
				Patient p = new Patient(patientNameField.getText(), patientAddressField.getText());
				p.setPatientNum(patientID);
				patientList.add(p);
			}catch(IndexOutOfBoundsException f){
				System.out.println("Failed to add patient");
			}

		});
	}
	public void deletePatient(){
		//Tab 2 delete
		BorderPane bp2 = new BorderPane();
		VBox vb2 = new VBox();
		Label patientNameLabelRemove = new Label("Patient Name ");
		Label patientNumLabelRemove = new Label("Patient Number ");
		TextField patientNameFieldRemove = new TextField();
		TextField patientNumFieldRemove = new TextField();
		Button removePatientButton = new Button("Remove Patient");

		vb2.setAlignment(Pos.CENTER);
		vb2.getChildren().addAll(patientNameLabelRemove, patientNameFieldRemove, patientNumLabelRemove, patientNumFieldRemove, removePatientButton);
		vb2.setSpacing(20);
		patientNameFieldRemove.setMaxWidth(150);
		patientNumFieldRemove.setMaxWidth(150);
		bp2.setCenter(vb2);
		tab2.setContent(bp2);
		removePatientButton.setOnAction(e->{
			int numRemove =0;
			String patientStringRemove = "";
			patientStringRemove = patientNumFieldRemove.getText();
			numRemove = Integer.parseInt(patientStringRemove);
			for (int x=0; x<patientList.size(); x++)
			{

				if (patientList.get(x).getPatientNum() == numRemove)
				{
					patientList.remove(x);
				}
				else if (patientNameFieldRemove.getText().equalsIgnoreCase(patientList.get(x).name))
				{
					patientList.remove(x);
				}
			}
		});
	}
	public void listPatient(){
		//Tab 3 List
		BorderPane bp3 = new BorderPane();
		VBox vb3 = new VBox();
		Label patientNameLabelList = new Label("Patient Name ");
		Label patientNumLabelList = new Label("Patient Number ");
		TextField patientNameFieldList = new TextField();
		TextField patientNumFieldList = new TextField();
		Button listPatientButton = new Button("List Patient");
		Button clearList = new Button("Clear List");
		Button listAll = new Button("List all Patients");
		TextArea ta = new TextArea();
		//ta.getBackground();
		bp3.setBottom(ta);
		vb3.setAlignment(Pos.CENTER);
		vb3.getChildren().addAll(patientNameLabelList, patientNameFieldList, patientNumLabelList, patientNumFieldList, listPatientButton, listAll, clearList);
		vb3.setSpacing(20);
		patientNameFieldList.setMaxWidth(150);
		patientNumFieldList.setMaxWidth(150);
		bp3.setCenter(vb3);
		tab3.setContent(bp3);
		listPatientButton.setOnAction(e->{ //List individual patient
			int num =0;
			String patientString = "";
			patientString = patientNumFieldList.getText();
			try{
				num = Integer.parseInt(patientString);
			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}
			for (int x=0; x<patientList.size(); x++)
			{

				if (patientList.get(x).getPatientNum() == num)
				{
					patientList.get(x).print();
					ta.setText(patientList.get(x).toString());
				}
				else if (patientNameFieldList.getText().equalsIgnoreCase(patientList.get(x).name))
				{
					ta.setText(patientList.get(x).toString());
				}


			}
		});
		listAll.setOnAction(e->{ //List all
			String y ="";
			for(int x=0; x<patientList.size(); x++){
				y += patientList.get(x).toString() + "\n";
				ta.setText(y);
			}
		});
		clearList.setOnAction(e->{ //Clear the list
			ta.setText("");
		});
	}
	public void addProcedure(){ //add procedure
		BorderPane bp4 = new BorderPane();
		VBox vb4 = new VBox();

		Label procedureNameLabel = new Label("Procedure Name ");
		Label procedureCostLabel = new Label("Procedure Cost ");
		Label patientIDLabel = new Label("Patient ID");
		Label invoiceIDLabel = new Label("Invoice ID");

		TextField procedureNameField = new TextField();
		TextField procedureCostField = new TextField();
		TextField patientIDField = new TextField();
		TextField invoiceIDField = new TextField();
		Button addProcedureButton = new Button("Add Procedure");

		vb4.setAlignment(Pos.CENTER);
		vb4.getChildren().addAll(procedureNameLabel, procedureNameField, procedureCostLabel, procedureCostField, patientIDLabel, patientIDField, invoiceIDLabel, invoiceIDField, addProcedureButton);
		vb4.setSpacing(20);
		procedureNameField.setMaxWidth(150);
		procedureCostField.setMaxWidth(150);
		patientIDField.setMaxWidth(150);
		invoiceIDField.setMaxWidth(150);
		bp4.setCenter(vb4);
		tab4.setContent(bp4);

		addProcedureButton.setOnAction(e->{
			String procedureCostString = procedureCostField.getText();
			double procedureCostDouble = 0;
			int invId =0;
			int pid = 0;
			int size = 0;
			try{
				procedureCostDouble = Double.parseDouble(procedureCostString);
				String invIdString = invoiceIDField.getText();
				String patIdString = patientIDField.getText();

				invId = Integer.parseInt(invIdString);
				pid = Integer.parseInt(patIdString);

				// set the procedure id, will be unique to to different invoices, starts at 1 and incremements
			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}catch(IndexOutOfBoundsException f){
				System.out.println("Indexoutofboundsexception caught");
			}

			int patientPos = findPatientID(pid);
			int invPos = findInvoicePos(patientPos, invId);

			try{ // set the values of the procedure ID
				size = patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.size();
				procedureID =  patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.get(size-1).getProcNum()+1;
			}catch(IndexOutOfBoundsException f){
				procedureID = 1;	
			}

			try{ // add the procedure
				Procedure pr = new Procedure(procedureNameField.getText(), procedureCostDouble);
				patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.add(pr);

				pr.setProcNum(procedureID);
			}catch(IndexOutOfBoundsException f){
				System.out.println("Procedure failed to add");
			}
		});
	}
	public void deleteProcedure(){ //delete
		BorderPane bp5 = new BorderPane();
		VBox vb5 = new VBox();
		Label procedureNumLabelDelete = new Label("Procedure Number ");
		Label patientIDLabel = new Label("Patient ID");
		Label invoiceIDLabel = new Label("Invoice ID");
		TextField procedureNumFieldDelete = new TextField();
		TextField patientIDField = new TextField();
		TextField invoiceIDField = new TextField();
		Button deleteProcedureButton = new Button("Delete Procedure");

		vb5.setAlignment(Pos.CENTER);
		vb5.getChildren().addAll( procedureNumLabelDelete, procedureNumFieldDelete, patientIDLabel, patientIDField, invoiceIDLabel, invoiceIDField, deleteProcedureButton);
		vb5.setSpacing(20);
		procedureNumFieldDelete.setMaxWidth(150);
		patientIDField.setMaxWidth(150);
		invoiceIDField.setMaxWidth(150);
		bp5.setCenter(vb5);
		tab5.setContent(bp5);
		deleteProcedureButton.setOnAction(e->{

			int invId =0;
			int pid = 0;
			int procId =0;
			try{
				String invIdString = invoiceIDField.getText();
				String patIdString = patientIDField.getText();
				String procIdString = procedureNumFieldDelete.getText();
				invId = Integer.parseInt(invIdString);
				pid = Integer.parseInt(patIdString);
				procId = Integer.parseInt(procIdString);
				// set the procedure id, will be unique to to different invoices, starts at 1 and incrememnts
			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}catch(IndexOutOfBoundsException f){
				System.out.println("Indexoutofboundsexception caught");
			}
			int patientPos = findPatientID(pid);
			int invPos = findInvoicePos(patientPos, invId);
			int procPos = findProcPos(patientPos, invPos, procId);
			try{ // set the values of the procedure ID
				patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.remove(procPos);
			}catch(IndexOutOfBoundsException f){
			}

		});
	}
	public void listProcedure(){
		BorderPane bp6 = new BorderPane();
		VBox vb6 = new VBox();
		Label procedureNumLabelList = new Label("Procedure Number ");
		Label patientIDLabel = new Label("Patient ID");
		Label invoiceIDLabel = new Label("Invoice ID");
		TextField procedureNumFieldList = new TextField();
		TextField patientIDField = new TextField();
		TextField invoiceIDField = new TextField();
		Button listProcedureButton = new Button("List Procedure");
		TextArea ta = new TextArea();
		bp6.setBottom(ta);
		vb6.setAlignment(Pos.CENTER);
		vb6.getChildren().addAll(procedureNumLabelList, procedureNumFieldList, patientIDLabel, patientIDField, invoiceIDLabel, invoiceIDField, listProcedureButton);
		vb6.setSpacing(20);

		procedureNumFieldList.setMaxWidth(150);
		patientIDField.setMaxWidth(150);
		invoiceIDField.setMaxWidth(150);
		bp6.setCenter(vb6);
		tab6.setContent(bp6);

		listProcedureButton.setOnAction(e->{

			int invId =0;
			int pid = 0;
			int procId =0;
			try{
				String invIdString = invoiceIDField.getText();
				String patIdString = patientIDField.getText();
				String procIdString = procedureNumFieldList.getText();
				invId = Integer.parseInt(invIdString);
				pid = Integer.parseInt(patIdString);
				procId = Integer.parseInt(procIdString);
				// set the procedure id, will be unique to to different invoices, starts at 1 and increments
			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}catch(IndexOutOfBoundsException f){
				System.out.println("Indexoutofboundsexception caught");
			}
			int patientPos = findPatientID(pid);
			int invPos = findInvoicePos(patientPos, invId);
			int procPos = findProcPos(patientPos, invPos, procId);
			try{ // set the values of the procedure ID
				patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.get(procPos).print();
				ta.setText(patientList.get(patientPos).p_invoiceList.get(invPos).inProcList.get(procPos).toString());
			}catch(IndexOutOfBoundsException f){
			}

		});
	}
	public void addPayment(){
		BorderPane bp7 = new BorderPane();
		VBox vb7 = new VBox();
		Label paymentCostLabel = new Label("Payment Amount ");
		Label patientIDLabel = new Label("Patient ID");
		Label invoiceIDLabel = new Label("Invoice ID");
		TextField paymentCostField = new TextField();
		TextField patientIDField = new TextField();
		TextField invoiceIDField = new TextField();
		Button addPaymentButton = new Button("Add Payment");
		vb7.setAlignment(Pos.CENTER);
		vb7.getChildren().addAll(paymentCostLabel, paymentCostField, patientIDLabel, patientIDField, invoiceIDLabel, invoiceIDField, addPaymentButton);
		vb7.setSpacing(20);
		paymentCostField.setMaxWidth(150);
		patientIDField.setMaxWidth(150);
		invoiceIDField.setMaxWidth(150);
		bp7.setCenter(vb7);
		tab7.setContent(bp7);
		addPaymentButton.setOnAction(e->{
			//Payment pa = new Payment();
			String payCostString = paymentCostField.getText();
			String patId = patientIDField.getText();
			String invfield = invoiceIDField.getText();

			//System.out.println("patient string is: "+patId);
			double payCostDouble = 0;
			int pid = 0;
			int invId = 0;

			try{
				payCostDouble = Double.parseDouble(payCostString);
				pid = Integer.parseInt(patId);
				invId = Integer.parseInt(invfield);

			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}
			//System.out.println("Reached here three!");

			int patientPos = 0;
			int invPos = 0;
			System.out.println("Reached here three!");
			int size = 0;
			try{ // set the values of the procedure ID
				patientPos = findPatientID(pid);
				//System.out.println("patient found is: "+patientPos);

				invPos = findInvoicePos(patientPos, invId);

				size = patientList.get(patientPos).p_invoiceList.get(invPos).inPaymentList.size();
				paymentID =  patientList.get(patientPos).p_invoiceList.get(invPos).inPaymentList.get(size-1).getPaymentNum()+1;
			}catch(IndexOutOfBoundsException f){
				paymentID = 1;	
			}
			//int size = patientList.
			Payment pay = new Payment();
			try{ // add the procedure
				pay.setPaymentAmt(payCostDouble);
				pay.setPaymentNum(paymentID);
				pay.setPaymentDate(new Date());
				//System.out.println("Patient position is: "+patientPos);
				//System.out.println("Invoice position is: "+invPos);

				patientList.get(patientPos).p_invoiceList.get(invPos).inPaymentList.add(pay);

			}catch(IndexOutOfBoundsException f){
				System.out.println("Payment failed to add");
			}

		});
	}
	public void invoiceManagement(){
		BorderPane bp8 = new BorderPane();
		VBox vb8 = new VBox();
		Label patientNumLabel = new Label("Patient Number ");
		TextField patientNumField = new TextField();
		Button addInvoiceButton = new Button("Add Invoice to patient");
		vb8.setAlignment(Pos.CENTER);
		vb8.getChildren().addAll(patientNumLabel, patientNumField, addInvoiceButton);
		vb8.setSpacing(20);
		patientNumField.setMaxWidth(150);
		bp8.setCenter(vb8);
		tab8.setContent(bp8);

		addInvoiceButton.setOnAction(e->{

			String patientIdString = patientNumField.getText();
			int patientId=0;
			try{
				patientId = Integer.parseInt(patientIdString);
			}catch(NumberFormatException f){
				System.out.println("Invalid input");
			}
			int findPatientPos = findPatientID(patientId);
			Invoice inv = new Invoice(new Date());
			try{
				int size = patientList.get(findPatientPos).p_invoiceList.size();
				invoiceID = patientList.get(findPatientPos).p_invoiceList.get(size-1).getInvoiceNo()+1;
			}catch(IndexOutOfBoundsException c){
				invoiceID = 1;
			}

			try{
				inv.setInvoiceNo(invoiceID);
				inv.setInvoiceAmt(0);

			}catch(IndexOutOfBoundsException c){
				System.out.println("Failed");
			}
			try{
				if(findPatientPos >= 0){
					patientList.get(findPatientPos).p_invoiceList.add(inv);
				}
				else{
					System.out.println("Error");

				}
				System.out.println("Patient position is: "+findPatientPos);
			}catch(IndexOutOfBoundsException f){
				System.out.println("INdexoutofbounds exception caught");
			}

		});
	}
	public void saveAndQuit(){
		BorderPane bp9 = new BorderPane();
		VBox vb9 = new VBox();
		Button saveAndQuit = new Button("Save and quit");
		Button quit = new Button("Quit");
		vb9.setAlignment(Pos.CENTER);
		vb9.getChildren().addAll(saveAndQuit, quit);
		vb9.setSpacing(20);
		bp9.setCenter(vb9);
		tab9.setContent(bp9);

		saveAndQuit.setOnAction(e->{ //Saves patient arraylist to text file and closes
			try {
				FileOutputStream fileOut =
						new FileOutputStream("./patientlist.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(patientList);
				out.close();
				fileOut.close();
			}catch(IOException a) {
				a.printStackTrace();
			}
			primary.close();
		});
		quit.setOnAction(e->{ //Just closes
			primary.close();
		});

	}
	public void generateReport(){
		BorderPane bp10 = new BorderPane();
		VBox vb10 = new VBox();
		TextArea ta = new TextArea();
		bp10.setBottom(ta);
		Button generateByName = new Button("Generate and sort report by name");
		vb10.setAlignment(Pos.CENTER);
		vb10.getChildren().addAll(generateByName);
		vb10.setSpacing(20);
		bp10.setCenter(vb10);
		tab10.setContent(bp10);

		generateByName.setOnAction(e->{ 

		});
	}
	public int findPatientID(int id){ //finds patient position instead of id so it is correct in array
		int position = 0;
		for(int index = 0; index < patientList.size(); index++){
			if(patientList.get(index).getPatientNum() == id){
				position = index;
				break;
			}
			else{
				position = -1;
			}
		}

		return position;
	}

	public int findInvoicePos(int pid, int invId){ //finds invoice position for similar reasons
		int position = 0;
		for(int index = 0; index < patientList.get(pid).p_invoiceList.size(); index++){
			if(patientList.get(pid).p_invoiceList.get(index).getInvoiceNo() == invId){
				position = index;
				break;
			}
			else{
				position = -1;
			}
		}

		return position;
	}
	public int findProcPos(int pid, int invId, int procId){ //finds procedure position for same reasons
		int position = 0;
		for(int index = 0; index < patientList.get(pid).p_invoiceList.get(invId).inProcList.size(); index++){
			if(patientList.get(pid).p_invoiceList.get(invId).inProcList.get(index).getProcNum() == procId){
				position = index;
				break;
			}
			else{
				position = -1;
			}
		}

		return position;
	}
	public void test(){
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);
		//addPatientButton.setOnAction(e->{ //here I add a patient and generate a patient number

		//	int size = patientList.size();

		//	try{
		//		patientID = patientList.get(size-1).getPatientNum()+1;

		//	}catch(IndexOutOfBoundsException c){
		//		patientID = 1;
		//	}
			

		//});
	}
}
