import java.awt.Color;//Imports used
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public interface Hasher {//Interface for the whole program

	public static void main(String[] args) {//Main method
		JFrame frame = new JFrame("Hasher");// Title of the application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Closes the program when shutdown

		JPanel masterPanel = new JPanel();// Creating a new panel
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));// Showing the layout of the program
		masterPanel.setBackground(Color.LIGHT_GRAY);//Background colour

		MainPanel panel = new MainPanel();// Creating a new panel
		frame.setJMenuBar(panel.setupTaskBar());// Adds the taskbar to the panel
		masterPanel.add(panel);// adding both the panels to the masterpanel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));// Showing the layout of the program
		panel.setBackground(Color.LIGHT_GRAY);//Background colour

		frame.getContentPane().add(masterPanel);// shows the content within the masterPanel

		frame.pack();// The whole frame
		frame.setVisible(true);// Allow you to see
	}// End of main method
}// End of the interface
