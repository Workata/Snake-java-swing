import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreWindow extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private DefaultTableModel tableModel;
	
	List<Integer> snakeScore;
	
	ScoreWindow() //List<Integer> snakeScore
	{
		String[] columns = {"Place","Score"};
		tableModel = new DefaultTableModel(columns,0);
		table = new JTable(tableModel);
		table.setRowSelectionAllowed(false);
		snakeScore = readScore();
		
		setViewportView(table); //scrollable child that's goingto be displayed in the scrollpane
		setPreferredSize(new Dimension(150,200));
		setBorder(BorderFactory.createTitledBorder("Scoreboard"));
	}
	
	void refresh()
	{
		tableModel.setRowCount(0);
		Collections.sort(snakeScore, new Comparator<Integer>() {

			@Override
			public int compare(Integer i1, Integer i2) 
			{
				if(i1>i2)
					return -1;
				else if(i1<i2)
					return 1;
				else return 0;
			}
			
		});
		int i=0;
		for(Integer score: snakeScore)
		{
			i++;
			String[] s = {""+ i,""+score};
			tableModel.addRow(s);
		}
		
	}
	
	public static List<Integer> readScore()
	{
		List<Integer> snakeScore = new LinkedList<Integer>();
		
		try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Panel.SNAKE_SCORE_FILE)))
		{
			snakeScore = (List<Integer>) inputStream.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return snakeScore;
	}
	

}
