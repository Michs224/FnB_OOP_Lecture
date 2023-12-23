package fnb_oop;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class navbar implements Initializable{
	@FXML
	VBox navbarLeft;
	@FXML
	ImageView menuIcon,navbar1,navbar2,navbar3,navbar4,navbar5,navbar6;
	@FXML
	Rectangle hover1,hover2,hover3,hover4,hover5,hover6;
	
	protected user myUser;
	
	Image cross = new Image(getClass().getResourceAsStream("/assets/Silang.png"));
	Image menu = new Image(getClass().getResourceAsStream("/assets/Garis3.png"));
	
	ImageView[] imageViewArray = new ImageView[6];
	Rectangle[] rectangleArray = new Rectangle[6]; 
	
	private boolean waiting = true,isNavbarVisible = false;
	private static boolean[] currentScene;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	protected void commonInitialize() {
		imageViewArray[0] = navbar1;imageViewArray[1] = navbar2;imageViewArray[2] = navbar3;
		imageViewArray[3] = navbar4;imageViewArray[4] = navbar5;imageViewArray[5] = navbar6;
		
		rectangleArray[0] = hover1;rectangleArray[1] = hover2;rectangleArray[2] = hover3;
		rectangleArray[3] = hover4;rectangleArray[4] = hover5;rectangleArray[5] = hover6;
	}
	
	public void showNavbarLeft(){
	
		if(navbarLeft.isVisible() && waiting) {
			waiting = false;
			isNavbarVisible = false;
			
			menuIcon.setDisable(true);
			menuIcon.setImage(menu);
			
			TranslateTransition translate = new TranslateTransition();
			translate.setNode(navbarLeft);
			translate.setDuration(Duration.millis(350));
			translate.setByX(-145);
			translate.play();
			
			translate.setOnFinished(event->{
				//System.out.println("first");
				navbarLeft.setVisible(false);
				//menuIcon.setDisable(false);
				waiting = true;
				
				System.out.println("value of "+isNavbarVisible);
			});
		}
		else if(!navbarLeft.isVisible() && waiting){
			//System.out.println("2");
			isNavbarVisible = true;
			waiting = false;
			//menuIcon.setDisable(true);
			menuIcon.setImage(cross);
			//System.out.println("off call");
			navbarLeft.setVisible(true);
			TranslateTransition translate = new TranslateTransition();
			translate.setNode(navbarLeft);
			translate.setDuration(Duration.millis(350));
			translate.setByX(145);
			translate.play();
			translate.setOnFinished(event->{
				//System.out.println("second");
				//menuIcon.setDisable(false);
				waiting = true;
				//System.out.println("value of "+isNavbarVisible);
			});
		}
	}
	
	public void initUser(user currentUser) {
		myUser = currentUser;
	}
	
	public void toDashboard(MouseEvent event) throws IOException {
		FXMLLoad(event,"/fxml/dashboard.fxml");
	}
	
	public void toBeverage(MouseEvent event) throws IOException {
		FXMLLoad(event,"/fxml/beverage.fxml");
	}
	
	public void toOrder(MouseEvent event) throws IOException {
		FXMLLoad(event,"/fxml/order.fxml");
	}
	
	public void toWallet(MouseEvent event) throws IOException {
		FXMLLoad(event,"/fxml/wallet.fxml");
	}
	
	public void toManageAccount(MouseEvent event) throws IOException {
		FXMLLoad(event,"/fxml/accountManager.fxml");
	}
	
	public void toLogout(MouseEvent event) throws IOException {
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        System.out.println("Log Out: " + formattedDateTime);
        
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		if(event.getSource() instanceof Button) {
			//System.out.println("button");
			Button transit = (Button) event.getSource();
			String id = transit.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			//System.out.println(currentScene[idx-1]);
			if(currentScene[idx-1]) {
				//System.out.println("pass");
				resetSceneCurrent();
				currentScene[idx-1] = false;
				stage.setScene(scene);
				stage.show();
			}
		}
		else if(event.getSource() instanceof ImageView) {
			//System.out.println("img view");
			ImageView transit = (ImageView) event.getSource();
			String id = transit.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			//System.out.println(currentScene[idx-1]);
			if(currentScene[idx-1]) {
				//System.out.println("pass");
				resetSceneCurrent();
				currentScene[idx-1] = false;
				stage.setScene(scene);
				stage.show();
			}
		}
	}
	
	private void FXMLLoad(MouseEvent event,String path) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
			
		navbar nextController = loader.getController();
		nextController.initializeUser(myUser);
		
		if(event.getSource() instanceof Button) {
			//System.out.println("button");
			Button transit = (Button) event.getSource();
			String id = transit.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			//System.out.println(currentScene[idx-1]);
			if(currentScene[idx-1]) {
				//System.out.println("pass");
				resetSceneCurrent();
				currentScene[idx-1] = false;
				stage.setScene(scene);
				stage.show();
			}
		}
		else if(event.getSource() instanceof ImageView) {
			//System.out.println("img view");
			ImageView transit = (ImageView) event.getSource();
			String id = transit.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			//System.out.println(currentScene[idx-1]);
			if(currentScene[idx-1]) {
				//System.out.println("pass");
				resetSceneCurrent();
				currentScene[idx-1] = false;
				stage.setScene(scene);
				stage.show();
			}
		}
	}
	
	public void initializeUser(user currentUser) {
		System.out.println("sucessfully called");
		myUser = currentUser;
	}
	
	private void resetSceneCurrent() {
		for(int i=0;i<6;i++) {
			currentScene[i] = true;
		}
	}
	
	@FXML
	private void enter(MouseEvent event) {
		if(event.getSource() instanceof Button) {
			Button hover = (Button) event.getSource();
			String id = hover.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			rectangleArray[idx-1].setVisible(true);
			imageViewArray[idx-1].setX(7);
		}
		else if(event.getSource() instanceof ImageView) {
			ImageView hover = (ImageView) event.getSource();
			String id = hover.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			rectangleArray[idx-1].setVisible(true);
			imageViewArray[idx-1].setX(7);
		}
	}
	@FXML
	private void left(MouseEvent event) {
		if(event.getSource() instanceof Button) {
			Button clicked = (Button) event.getSource();
			String id = clicked.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			rectangleArray[idx-1].setVisible(false);
			imageViewArray[idx-1].setX(-2);
		}
		else if(event.getSource() instanceof ImageView) {
			ImageView hover = (ImageView) event.getSource();
			String id = hover.getId();
			char num = id.charAt(id.length()-1);
			int idx = num - '0';
			rectangleArray[idx-1].setVisible(false);
			imageViewArray[idx-1].setX(-2);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("value of "+isNavbarVisible);
		if(!isNavbarVisible) {
			navbarLeft.setVisible(isNavbarVisible);
			TranslateTransition translate = new TranslateTransition();
			translate.setNode(navbarLeft);
			translate.setByX(-145);
			translate.play();
		}
		else {
			navbarLeft.setVisible(isNavbarVisible);
			TranslateTransition translate = new TranslateTransition();
			translate.setNode(navbarLeft);
			translate.setByX(145);
			translate.play();
		}
		
		commonInitialize();
		
		if(currentScene == null) {
			currentScene = new boolean[6];
			for(int i=0;i<6;i++) {
				currentScene[i] = true;
			}
		}
	}
}
