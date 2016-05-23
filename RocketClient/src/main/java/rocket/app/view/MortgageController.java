package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	@FXML
    private TextField txtIncome;
	//		TextBox  - 	txtExpenses
	@FXML
	private TextField txtExpenses;
	//		TextBox  - 	txtCreditScore
	@FXML
	private TextField txtCreditScore;
	//		TextBox  - 	txtHouseCost
	@FXML
	private TextField txtHouseCost;
	//		ComboBox -	loan term... 15 year or 30 year
	
	//@FXML
	//private ObservableList<String> terms = FXCollections.observableArrayList("15","30");
	@FXML
	private ComboBox comboboxTerm;
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblTerm;
	//		Button   -  button to calculate the loan payment
	@FXML
	private Button buttonCalculatePayment;
	//		Label    -  to show error messages (exception throw, payment exception)
	@FXML
	private Label lblResultOrError;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) throws NumberFormatException, RateException
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		lq.setdIncome(Integer.parseInt(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		
		lq.setiTerm(Integer.parseInt((String) comboboxTerm.getValue()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.assigndRate();

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		
		/**double dPayment = lRequest.calculatedPayment();
		double monthlyPITI = 0;
		if(lRequest.getdExpenses()==0){
			monthlyPITI = lRequest.getdIncome()*0.28;
		}
		else{
			monthlyPITI = (lRequest.getdIncome()*0.36)-lRequest.getdExpenses();
		}
		
		String rateAsPercentage = ((int)(100*lRequest.getdRate()))+"%";
		
		if(lRequest.getdRate()==0){
			lblResultOrError.setText("ERROR: Credit Score is invalid");
		}
		else if(monthlyPITI>=dPayment){
			String payment = String.format("%.2f", dPayment);
			lblResultOrError.setText("Payment is: $"+payment+" at "+rateAsPercentage+" interest");
		}
		else{
			String payment = "ERROR: You cannot afford this Mortgage";
			lblResultOrError.setText(payment);
		}
		**/
		lblResultOrError.setText(lRequest.createResultMessage());
		
	}

	@FXML
	public void initialize() {
			comboboxTerm.getItems().addAll("15","30");
	}
}
