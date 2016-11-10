package swingGUI;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListSelector {
	JFrame frame;
	JList<String> list;
	DefaultListModel<String> model;
	JLabel label;
	JPanel panel;
	JSplitPane splitPane;
	
	public ListSelector() {
		frame = new JFrame();
		list = new JList<>();
		model = new DefaultListModel<>();
		label = new JLabel();
		panel = new JPanel();
		splitPane = new JSplitPane();
		
		list.setModel(model);
		splitPane.setLeftComponent(new JScrollPane(list));
		model.addElement("Element 1");
		model.addElement("Element 2"); 
		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selected = list.getSelectedValue();
				label.setText(selected + ", whatever the description is");
			}
		});
		
		panel.add(label);
		splitPane.setRightComponent(panel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(splitPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ListSelector();
			}
		});
	}
}
