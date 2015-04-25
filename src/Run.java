package softeng;
import javax.swing.*;

public class Run {

	public static void main ( String [ ] args ) {

		String [ ] mainMenuKeyLabels = { 
										"Calendar", 
										"Register", 
										"Inventory", 
										"Financial", 
										"People", 
										"Sign Out", 
										"Exit" 
										};
		Home salonHome = new Home ( "Home", mainMenuKeyLabels, 7, 1, 1, JFrame.EXIT_ON_CLOSE );
		salonHome.setVisible ( true );

	}

}
