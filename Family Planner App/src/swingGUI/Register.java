package swingGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FMlogic.App;
import FMlogic.User;


public class Register {
	private JFrame frame;
	private Container pane;
	private JPanel panel, panel2;
	private JButton regBtn, backBtn;
	private JLabel label;
	private JTextField userName;

	private void initElems() {
		frame = new JFrame("Register");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		panel = new JPanel();
		label = new JLabel("Register a new user");
		panel.add(label);

		userName = new JTextField(15);
		panel.add(userName);

		regBtn = new JButton("Register");
		regBtn.addActionListener(new RegisterEvent());

		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		
		panel2 = new JPanel();
		panel2.add(backBtn); panel.add(regBtn); 

		pane.add(panel);
		pane.add(panel2);
		frame.pack();
		frame.setVisible(true);
	}

	public Register() {
		initElems();
	}
	
	private class RegisterEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = userName.getText();
			if (App.userExists(name)) {
				//TODO: Handle invalid name (already exists)
				System.out.println("User already exists!");
			}
			App.addUser(name);
			System.out.println(name);
			frame.dispose();
			
			
		}
			
			
	}

}
