//void activate() – metoda data ajuta la activarea vizualizarii acestei clase.
//Corect zis aceasta metoda ajuta la pornirea frame-ului care contine date despre program si autor; 

package pack;

import java.awt.*;
import javax.swing.*;

public class About extends JFrame
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	private JTextArea text = new JTextArea();
	
	private Dimension d = new Dimension(280, 200);
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
//---------------------------------------------------------------------------------------------
	About()
	{
		setTitle("About");
		setPreferredSize(d);
		setLocation(s.width / 3 - d.width / 2, s.height / 6);
		setResizable(false);
		
		add(text);
		text.setBackground(new Color(255, 196, 6));
        text.setFont(new Font("Calibri", 0, 16));
        text.setColumns(20);
        text.setRows(5);
        text.setEditable(false);
        text.setForeground(new Color(0, 0,0));
        text.setText("  \n      \n          Proiect: Cozi\n          Nume: Muntean\n          Prenume: Andrei\n          Grupa: 30227");
		
		pack();
	}
//---------------------------------------------------------------------------------------------
	void activate()
	{
		setVisible(true);
    	setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
