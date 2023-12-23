package fnb_oop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.effect.Effect;

public class orderController extends navbar{
	@FXML
	AnchorPane onGoingAnchor,bookedAnchor,historyAnchor;
	@FXML
	Label onGoingLabel,bookedLabel,historyLabel;
	
	public void showOnGoing() {
		changeShow(onGoingAnchor,onGoingLabel);
	}
	public void showBooked() {
		changeShow(bookedAnchor,bookedLabel);
	}
	public void showHistory() {
		changeShow(historyAnchor,historyLabel);
	}
	
	public void changeShow(AnchorPane anchor,Label label) {
		onGoingAnchor.setVisible(false);
		bookedAnchor.setVisible(false);
		historyAnchor.setVisible(false);
		onGoingLabel.setUnderline(false);
		bookedLabel.setUnderline(false);
		historyLabel.setUnderline(false);
		
		label.setUnderline(true);
		anchor.setVisible(true);
	}
}
