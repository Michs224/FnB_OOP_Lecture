package fnb_oop;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class signupController extends validation{
	@FXML
	Label messageLabel;
	@FXML
	TextField nameForm;
	@FXML
	TextField usernameForm;
	@FXML
	TextField emailForm;
	@FXML
	PasswordField newPasswordForm;
	@FXML
	PasswordField confirmPasswordForm;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	mySQL mysql;
	
	public void loginButton(ActionEvent event) throws IOException, SQLException {
		
		mysql = new mySQL();
		
		String name = nameForm.getText();
		String username = usernameForm.getText();
		String email = emailForm.getText();
		String newPassword = newPasswordForm.getText();
		String confirmPassword = confirmPasswordForm.getText();
		
		boolean isNameEmpty = name.isEmpty();
		boolean isUsernameEmpty = username.isEmpty();
		boolean isEmailEmpty = email.isEmpty();
		boolean isNewPasswordEmpty = newPassword.isEmpty();
		boolean isConfirmPasswordEmpty = confirmPassword.isEmpty();
		
		//System.out.println(newPassword);
		//System.out.println(confirmPassword);
		
		if(isNameEmpty || isUsernameEmpty || isEmailEmpty || isNewPasswordEmpty || isConfirmPasswordEmpty){
			messageLabel.setText("Name,Username,Email and Password can't Empty");
		}
		else if(passwordValidation(confirmPassword) || passwordValidation(confirmPassword))
			messageLabel.setText("Password lenght must 8-16 & contains special,number and uppercase");
		else if(!newPassword.equals(confirmPassword))
			messageLabel.setText("New password and Confirm password Doesn't match");
		else{
			
			//query to database
			//insert into table users
			
			mysql.createNewUser(name, username, email, confirmPassword);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void toLogin(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
