package swingGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LogIn {
	
	private JFrame frame;
	private Container pane;
	private JPanel panel, panel2, panel3;
	private JButton logBtn, regBtn;
	private JLabel label, label2;
	private JTextField loginText;

		
	private void initElems() {
		frame = new JFrame ("Welcome to Healthy Family App");	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		label = new JLabel("Enter your login");
		panel.add(label);
		
		loginText = new JTextField(15);
		panel.add(loginText);
		
		logBtn = new JButton("Log In");
		logBtn.addActionListener(new LoginEvent());
		panel.add(logBtn); 
		
		regBtn = new JButton ("Create new user");
		label2 = new JLabel("Not registered yet?");
		
		panel2 = new JPanel(); panel2.setBackground(Color.white);
		panel2.add(label2);
		
		panel3 = new JPanel(); panel3.setBackground(Color.white);
		panel3.add(regBtn);

		pane.add(panel); pane.add(panel2); pane.add(panel3);
		frame.pack();
		frame.setVisible(true);
	}

	public LogIn() {
		initElems();
	}
	
	private class LoginEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String user = loginText.getText();
			if (user.equals("")) {} 			// do nothing
			else if (!userExists(user)) {
				JOptionPane.showMessageDialog(logBtn, "No such user!", 
						"Invalid user", JOptionPane.ERROR_MESSAGE);
				loginText.setText("");
			} else {
				frame.dispose();
				handleUser(user);
			}
		}
		
		private boolean userExists(String user) {
			// TODO: insert a real user checker here
			if (user.equals("vaka")) {
				return true;
			} 
			return false;
		}
		
		private void handleUser(String user) {
			// TODO Handle a valid user here
			System.out.println("User "+ user + " logged in!");
		}
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LogIn();
			}
		});
		
	}
}
