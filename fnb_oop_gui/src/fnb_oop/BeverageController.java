package fnb_oop;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BeverageController extends navbar{
	@FXML
	ScrollPane softDrinkPane,juicePane;
	
	public void toSoftDrink(MouseEvent event) {
		resetPane();
		softDrinkPane.setVisible(true);
	}
	
	public void toJuice(MouseEvent event) {
		resetPane();
		juicePane.setVisible(true);
	}
	
	public void resetPane() {
		softDrinkPane.setVisible(false);
		juicePane.setVisible(false);
	}
	
	@FXML
	private void addBeverage(ActionEvent event) {
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
