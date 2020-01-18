package table;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Start implements ActionListener {
	Interface table;
	
	private JFrame start;
	private JButton press;
	private JTextField sizeX;
	private JTextField sizeY;
	private JTextField mod;
	
	Start() {
		start = new JFrame("Welcome");
		press = new JButton("Start");
		press.addActionListener(this);
		sizeX = new JTextField(2);
		sizeY = new JTextField(2);
		mod = new JTextField(2);
		setFrame();
	}
	
	private void setFrame() {
		start.setLayout(new FlowLayout());
		start.add(press);
		start.add(sizeX);
		start.add(sizeY);
		start.add(mod);
		start.setResizable(false);
		start.setSize(300, 100);
		start.getContentPane().setBackground(Color.BLACK);
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setLocationRelativeTo(null);
		start.setVisible(true);
	}
	
	private int getX() {
		try {
			return Integer.valueOf(sizeX.getText());
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(start, "Trebuie sa introduci numere");
			return -1;
		}
	}
	
	private int getY() {
		try {
			return Integer.valueOf(sizeY.getText());
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(start, "Trebuie sa introduci numere");
			return -1;
		}
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(!(getX() <= 0 || getY() <= 0 || getX() > 9 || getY() > 9 || getX() != getY())) {
			table = new Interface(getX(), getY(), Integer.valueOf(mod.getText()));
			table.showTable();
			start.dispose();
			JOptionPane.showMessageDialog(press, "Have fun!");
		} else {
			table = new Interface(getX(), getY(), Integer.valueOf(mod.getText()));
		}
	}
}
