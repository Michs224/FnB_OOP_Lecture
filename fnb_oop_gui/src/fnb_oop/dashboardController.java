package fnb_oop;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class dashboardController extends navbar{
	
	@FXML
	ScrollPane mainCourseScroll,appetizerScroll,dessertScroll;
	
	public void toMainCourse(MouseEvent event){
		resetScrollPane();
		mainCourseScroll.setVisible(true);
	}
	
	public void toAppetizer(MouseEvent event){
		resetScrollPane();
		appetizerScroll.setVisible(true);
	}
	
	public void toDessert(MouseEvent event){
		resetScrollPane();
		dessertScroll.setVisible(true);
	}
	
	public void resetScrollPane() {
		mainCourseScroll.setVisible(false);
		appetizerScroll.setVisible(false);
		dessertScroll.setVisible(false);
	}
	
	@FXML
	private void addFood(ActionEvent event) {
		Button click = (Button) event.getSource();
		
		click.setStyle("-fx-background-color: black;"
				+ "-fx-background-radius:8;");
		click.setTextFill(Color.WHITE);
		
		PauseTransition pause = new PauseTransition(Duration.millis(100));
		pause.setOnFinished(e->{
			click.setStyle(
					"-fx-background-color: transparent;"
					+ "-fx-border-color:black;"
					+ "-fx-border-radius:8;"
			);
			
			click.setTextFill(Color.BLACK);
		});
		
		pause.play();
	}
}
