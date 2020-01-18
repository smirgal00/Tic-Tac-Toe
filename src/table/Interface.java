package table;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Interface implements ActionListener {
	private JFrame tabla = new JFrame("glhf");
	private JButton[][] xo = new JButton[9][9];
	private int fill;
	private int x, y;
	private int mod;
	
	Interface(int x, int y, int mod) {
		if(x <= 0 || y <= 0 || x > 9 || y > 9 || x != y) {
			JOptionPane.showMessageDialog(tabla, "Numerele introduse trebuie sa fie egale, si in intervalul (0, 9]");
		} else {
			setButton(x, y);
			setFrame(x, y);
			this.x = x;
			this.y = y;
		}
		this.mod = mod;
		fill = 1;
	}
	
	private void setButton(int x, int y) {
		try {
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++) {
					xo[i][j] = new JButton();
					xo[i][j].setBackground(Color.BLACK);
					xo[i][j].setForeground(Color.WHITE);
					xo[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
					xo[i][j].addActionListener(this);
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(tabla, "Dimensiuni gresite");
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(tabla, "Dimnesiunile trebuie sa fie numere");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton but = (JButton)e.getSource();
		if(mod == 0) {
			fillButton(but);
		} else {
			fillButtonAI(but);
		}
		
		
		if(checkWin() == 0) {
			JOptionPane.showMessageDialog(tabla, "X wins!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			tabla.dispose();
		} else if(checkWin() == 1) {
			JOptionPane.showMessageDialog(tabla, "O wins!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			tabla.dispose();
		}
	}
	
	private void setFrame(int x, int y) {
		tabla.setResizable(false);
		tabla.setSize(800, 640);
		tabla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabla.setLocationRelativeTo(null);
		addButtons(x, y);
		try {
			tabla.setLayout(new GridLayout(x, y));
		}catch(IllegalArgumentException e) {
			System.out.println("Nu pot fi 0");
		}
	}
	
	private void addButtons(int x, int y) {
		try {
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++) {
					tabla.add(xo[i][j]);
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(tabla, "Peste limite");
		}
	}
	
	public void showTable() {
		tabla.setVisible(true);
	}
	
	private void setFill() {
		fill *= -1;
	}
	
	private void fillButtonAI(JButton but) {
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(xo[i][j].equals(but)) {
					xo[i][j].setText("X");
					break;
				}
			}
		}
		Random rand = new Random();
		int i = rand.nextInt(x);
		int j = rand.nextInt(x);
		System.out.println(i + " " + j);
		while(xo[i][j].getText() != "") {
			i = rand.nextInt(x);
			j = rand.nextInt(y);
		}
		xo[i][j].setText("O");
	}
	
	private void fillButton(JButton but) {
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(xo[i][j].equals(but)) {
					if(fill == 1) {
						xo[i][j].setText("X");
						setFill();
					} else {
						xo[i][j].setText("O");
						setFill();
					}
				}
			}
		}
	}
	
	private int checkWin() {
		int win = -1;
		
		for(int i = 0; i < x; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			for(int j = 0; j < y; j++) {
				temp.add(xo[i][j].getText());
			}
			if(temp.contains("X") && !temp.contains("O") && !temp.contains("")) {
				win = 0;
				return win;
			} else if(!temp.contains("X") && temp.contains("O") && !temp.contains("")) {
				win = 1;
				return win;
			}
		}
		
		for(int i = 0; i < y; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			for(int j = 0; j < x; j++) {
				temp.add(xo[j][i].getText());
			}
			if(temp.contains("X") && !temp.contains("O") && !temp.contains("")) {
				win = 0;
				return win;
			} else if(!temp.contains("X") && temp.contains("O") && !temp.contains("")) {
				win = 1;
				return win;
			}
		}
		ArrayList<String> temp1 = new ArrayList<String>();
		for(int i = 0; i < x; i++) {
			temp1.add(xo[i][i].getText());
		}
		if(temp1.contains("X") && !temp1.contains("O") && !temp1.contains("")) {
			win = 0;
			return win;
		} else if(!temp1.contains("X") && temp1.contains("O") && !temp1.contains("")) {
			win = 1;
			return win;
		}

		ArrayList<String> temp2 = new ArrayList<String>();
		for(int i = 0; i < x; i++) {
			temp2.add(xo[x - i - 1][i].getText());
		}
		if(temp2.contains("X") && !temp2.contains("O") && !temp2.contains("")) {
			win = 0;
			return win;
		} else if(!temp2.contains("X") && temp2.contains("O") && !temp2.contains("")) {
			win = 1;
			return win;
		}
		
		return win;
	}
		
}
