package fnb_oop;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class loginController extends validation{
	@FXML
	Label messageLabel;
	@FXML
	TextField usernameForm;
	@FXML
	PasswordField passwordForm;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	mySQL mysql;
	
	public void loginButton(ActionEvent event) throws IOException, SQLException{
		
		mysql = new mySQL();
		
		String username = usernameForm.getText();
		String password = passwordForm.getText();
		boolean isUserEmpty = username.isEmpty();
		boolean isPassEmpty = password.isEmpty();
		
		//System.out.println(stage.getScene());

		if(isUserEmpty || isPassEmpty) {
			messageLabel.setText("Username & Password can't Empty");
		}
		else if(passwordValidation(password)) {
			messageLabel.setText("Password lenght must 8-16 & contains special,number and uppercase");
		}
		else {
			String result = mysql.getPasswordQuery(username);
			System.out.println(result);
			if(password.equals(result)) {
				
				LocalDateTime currentDateTime = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedDateTime = currentDateTime.format(formatter);

		        System.out.println("Log In: " + formattedDateTime);
				
				mysql.userQuery(username);
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
				root = loader.load();
				navbar nextController = loader.getController();
				nextController.initializeUser(mysql.userQuery(username));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else {
				messageLabel.setText("Password or Username incorrect");
			}
		}
	}
	
	public void toRegister(MouseEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
}
