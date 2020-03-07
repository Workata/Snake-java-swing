import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Window extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	
	final static String INSTRUCTION ="The game will start after you press: 'W' or 'S' or 'A' or 'D'"
			+"\n"+"'W' - go up"
			+"\n"+"'S' - go down"
			+"\n"+"'A' - go left"
			+"\n"+"'D' - go right"
			+"\n"+"The game will end when you hit either yourself or border."
			+"\n"+"\n"+"Good luck!";
	
	final static String AUTHOR = "Jakub Tolsciuk";
	ScoreWindow scoreWindow;
	

	public static void main(String args[])
	{
		new Window();		
	}
	
	Panel panel = new Panel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem howToPlayItem = new JMenuItem("How to play?");
	JMenuItem scoreboardItem = new JMenuItem("Scoreboard");
	JMenuItem authorItem = new JMenuItem("Author");
	JMenuItem restartItem = new JMenuItem("Restart");
	JMenuItem exitItem = new JMenuItem("Exit");
	

	
	public Window()
	{
		setSize(600,600);
		setTitle("Snake");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel.snakeScore = ScoreWindow.readScore(); //wczytanie poprzednich wyników
		
		menu.add(howToPlayItem);
		menu.add(scoreboardItem);
		menu.add(authorItem);
		menu.add(restartItem);
		menu.add(exitItem);
		menuBar.add(menu);
		
		howToPlayItem.addActionListener(this);
		scoreboardItem.addActionListener(this);
		authorItem.addActionListener(this);
		restartItem.addActionListener(this);
		exitItem.addActionListener(this);

		setJMenuBar(menuBar);
		setContentPane(panel);
		setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if(source == howToPlayItem)
		{
			JOptionPane.showMessageDialog(this, INSTRUCTION);
		}
		if(source == scoreboardItem)
		{
			
			scoreWindow = new ScoreWindow();
			scoreWindow.refresh();
			JOptionPane.showMessageDialog(this, scoreWindow);
		}
		if(source == authorItem)
		{
			JOptionPane.showMessageDialog(this, AUTHOR);
		}
		if(source==restartItem)
		{
			panel.restart();
		}
		if(source == exitItem)
		{
			System.exit(0);
		}
		
	}
	

	
}
