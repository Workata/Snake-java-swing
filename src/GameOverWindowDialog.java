import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameOverWindowDialog extends JDialog implements ActionListener{
	
	JPanel panel = new JPanel();
	JLabel gameOverLabel = new JLabel("Game Over");
	JLabel scoreLabel = new JLabel();
	
	JButton playAgainButton = new JButton("Play again");
	JButton scoreboardButton = new JButton("Scoreboard");
	JButton exitButton = new JButton("Exit");
	
	Font  scoreFont  = new Font(Font.MONOSPACED, Font.BOLD,  18);
	
	Panel parentPanel;
	
	ScoreWindow scoreWindow;
	
	
	public GameOverWindowDialog(Panel parent, int score)
	{
		setTitle("Game Over");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(170,200);
		setLocationRelativeTo(parent);
		
		parentPanel = parent;
		
		
		gameOverLabel.setFont(scoreFont);
		
		scoreLabel.setText("Your Score: "+score);
		scoreLabel.setFont(scoreFont);
		
		playAgainButton.addActionListener(this);
		scoreboardButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		panel.add(gameOverLabel);	
		panel.add(scoreLabel);
		
		panel.add(playAgainButton);
		panel.add(scoreboardButton);
		panel.add(exitButton);
		
		setContentPane(panel);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		
		if(source == playAgainButton)
		{
			parentPanel.restart();
			this.dispose();
		}
		else if(source == scoreboardButton)
		{
			scoreWindow = new ScoreWindow();
			scoreWindow.refresh();
			JOptionPane.showMessageDialog(this, scoreWindow);
		}
		else if(source == exitButton)
		{
			System.exit(0);
		}
	}

}
