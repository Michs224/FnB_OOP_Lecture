package fnb_oop;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class accountManagerController extends navbar implements Initializable{
	@FXML
	TextField userIdField,createdField,usernameField,nameField,emailField,addressField;
	@FXML
	PasswordField passwordField;
	@FXML
	ImageView profilePicture;
	
	public void confirm() throws SQLException {
		String userId = userIdField.getText();
		String created = createdField.getText();
		String username = usernameField.getText();
		String name = nameField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		
		String password = passwordField.getText();
		
		user updateUser = new user(userId,created,username,password,name,email,address);
		mySQL mysql = new mySQL();
		mysql.updateUserProfile(updateUser);
		myUser = updateUser;
	}
	
	public void updateProfilePicture(MouseEvent event) {
	    try {
	        ImageView transit = (ImageView) event.getSource();

	        FileChooser fileChooser = new FileChooser();
	        fileChooser.setInitialDirectory(new File("G:\\jawa\\fnb_oop\\src\\assets"));
	        File selectedFile = fileChooser.showOpenDialog((Stage)transit.getScene().getWindow());

	        if (selectedFile != null) {
	            System.out.println("Selected File: " + selectedFile.getAbsolutePath());

	            // Create a URL from the file
	            String imageUrl = selectedFile.toURI().toURL().toString();

	            // Set the image to the ImageView
	            Image imageFromPath = new Image(imageUrl);
	            profilePicture.setImage(imageFromPath);
	        } else {
	            System.out.println("No file selected.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void initializeUser(user currentUser) {
		//System.out.println("sucessfully called");
		myUser = currentUser;
		
		userIdField.setText(myUser.getUserId());
		createdField.setText(myUser.getCreated());
		usernameField.setText(myUser.getUsername());
		nameField.setText(myUser.getName());
		emailField.setText(myUser.getEmail());
		addressField.setText(myUser.getAddress());
		passwordField.setText(myUser.getPassword());
	}
	
}
