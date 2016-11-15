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

import FMlogic.App;

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
		regBtn.addActionListener(new CreateUserEvent());
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
			String userName = loginText.getText();
			if (userName.equals("")) {} 			// do nothing
			else if (!App.userExists(userName)) {
				JOptionPane.showMessageDialog(logBtn, "No such user!", 
						"Invalid user", JOptionPane.ERROR_MESSAGE);
				loginText.setText("");
			} else {
				App.setCurrentUser(userName);
				//frame.dispose();
				handleUser(userName);
			}
		}
		
		private void handleUser(String name) {
			// TODO Handle a valid user here
			
			App.setCurrentUser(name);
			System.out.println("User is registered");
			//System.out.println(App.getCurrentUser().toString());
			new Options();
			frame.dispose();			
		}
	}
	
	private class CreateUserEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//frame.dispose();
			new Register();
				
		}
	}
}
