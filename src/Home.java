package softeng;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* 
 * Home Class 
 * Constructs and displays a Home using Java Swing
 */
public class Home extends JFrame {

	/*
	 * Home Constructor
	 * Creates new Home object
	 */
	public Home ( String newMenuTitle,
				  	//	Displayed at top of window
				  String [ ] newKeyLabels,
				  	//	Each String should be the label of a single button on the Menu
				  	//	Array will label keys in top-to-bottom, left-to-right order
				  int newKeyPanelHeight,
				  	//	Should be the number of buttons the Menu is to contain vertically
				  int newKeyPanelWidth,
				  	//	Should be the number of buttons the Menu is to contain horizontally
				  int newTitleFieldHeight,
				  	//	Should be the desired height of the Title Panel
				  	//	(I think this is in pixels, but I'm not sure. My default is 25.)
				  int newDefaultClose 
				) {

		titleFieldHeight = newTitleFieldHeight;
		menuTitle = newMenuTitle;
		keyLabels = newKeyLabels;
		keyPanelHeight = newKeyPanelHeight;
		keyPanelWidth = newKeyPanelWidth;
		defaultClose = newDefaultClose;

		JPanel titlePanel = new JPanel ( );
		titlePanel.setLayout ( new BorderLayout ( ) );
		titlePanel.add ( new JLabel ( "Salon" ), BorderLayout.NORTH );
		titleField = new JTextArea ( titleFieldHeight, 25 );
		titleField.setText ( menuTitle );
		titlePanel.add ( titleField, BorderLayout.CENTER );

		JPanel keyPanel = new JPanel ( );
		keyPanel.setLayout ( new GridLayout ( keyPanelHeight, keyPanelWidth ) );
		ButtonListener buttonList = new ButtonListener ( );
		for ( int i = 0; i < keyLabels.length; i++ ) {
			final String label = keyLabels [ i ];
			JButton keyButton = new JButton ( label );
			keyPanel.add ( keyButton );
			keyButton.addActionListener ( buttonList );
		}

		JFrame frame = new JFrame ( );
		frame.setDefaultCloseOperation ( defaultClose );
		frame.add ( titlePanel, BorderLayout.NORTH );
		frame.add ( keyPanel, BorderLayout.CENTER );
		frame.pack ( );
		frame.setVisible ( true );
		frame.setSize ( 1000, 750 );
		frame.setLocationRelativeTo ( null );

	}

	public static void Exit ( ) {
		System.exit ( 0 );
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed ( ActionEvent event ) {
			JButton button = ( JButton ) event.getSource ( );
			String label = button.getText ( );
			titleField.setText ( " " + label + " button clicked" );
				// This is just for checking that the button works even
				//  if the code for what the button does is incomplete
			if ( label == "Calendar" ) {
				String [ ] calendarHomeKeyLabels = { 
											"Previous Week", 
											"Next Week", 
											"Create Appointment", 
											"Cancel Appointment", 
											"Schedule Employee Shift", 
											"View Employee Schedule",
											"Home" 
											};
				Home calendarHome = new Home ( "Calendar", calendarHomeKeyLabels, 7, 1, 1, JFrame.DISPOSE_ON_CLOSE );

				calendarHome.titleField.setSize ( 10, 25 );
				calendarHome.setVisible ( true );
			}
			if ( label == "Register" ) {
				String [ ] registerHomeKeyLabels = { 
											"New Transaction", 
											"Add Product/Service", 
											"Complete Transaction", 
											"Home" 
											};
				Home registerHome = new Home ( "Register", registerHomeKeyLabels, 4, 1, 1, JFrame.DISPOSE_ON_CLOSE );
				registerHome.titleField.setSize ( 10, 25 );
				registerHome.setVisible ( true );
			}
			if ( label == "Inventory" ) {
				String [ ] inventoryHomeKeyLabels = { 
											"View Inventory", 
											"Update Inventory", 
											"Home" 
											};
				Home inventoryHome = new Home ( "Inventory", inventoryHomeKeyLabels, 3, 1, 1, JFrame.DISPOSE_ON_CLOSE );
				inventoryHome.titleField.setSize ( 10, 25 );
				inventoryHome.setVisible ( true );
			}
			if ( label == "People" ) {
				String [ ] peopleHomeKeyLabels = { 
											"Customers", 
											"Employees", 
											"Home" 
											};
				Home peopleHome = new Home ( "People", peopleHomeKeyLabels, 3, 1, 1, JFrame.DISPOSE_ON_CLOSE );
				peopleHome.titleField.setSize ( 10, 25 );
				peopleHome.setVisible ( true );
			}
			if ( label == "Exit" ) {
				Home.Exit ( );
			}
		}

	}

	public static void setMenuTitle ( String title ) {
		menuTitle = title;
	}

	public static void setKeyLabels ( String [ ] args ) {
		keyLabels = args;
	}

	public static void setKeyPanelHeight ( int height ) {
		keyPanelHeight = height;
	}

	public static void setKeyPanelWidth ( int width ) {
		keyPanelWidth = width;
	}

	public static void setTitleFieldHeight ( int tfHeight ) {
		titleFieldHeight = tfHeight;
	}

	private JTextArea titleField;
	public static String menuTitle;
	public static String [ ] keyLabels;
	public static int keyPanelHeight;
	public static int keyPanelWidth;
	public static int titleFieldHeight;
	public static int defaultClose;
	private static final long serialVersionUID = 1L;

}
