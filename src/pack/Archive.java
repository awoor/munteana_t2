/* •	void activate() – metoda data ajuta la activarea vizualizarii acestei clase. 
Astfel, ea porneste frame-ul ce contine informatii referitoare la simularea cozilor de clienti;
•	void describeClass(Object object) – metoda ce descrie aceasta clasa, modul de afisare a informatiei;
•	void SetText(String s) – metoda care va permite afisarea textului in frame;
*/

package pack;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.*;

public class Archive extends JFrame
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem exit = new JMenuItem("Exit");
	private JTextArea text = new JTextArea();
	private JScrollPane scroll = new JScrollPane(text);
	
	private Dimension d = new Dimension(600, 400);
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
//---------------------------------------------------------------------------------------------
	Archive()
	{
		setTitle("Log");
		setMinimumSize(d);
		setLocation(s.width / 2 - d.width / 2, 0);
		setResizable(false);
		

		setJMenuBar(menu);
		menu.add(file);
		file.setFont(new Font("calibri",Font.PLAIN,13));
		file.add(save);
		save.setFont(new Font("calibri",Font.PLAIN,13));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
               //???
            }
        });
		file.add(exit);
		exit.setFont(new Font("calibri",Font.PLAIN,13));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
		exit.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
               setVisible(false);
            }
        });
		add(scroll);
		scroll.setViewportView(text);
		text.setBackground(Color.WHITE);
		text.setForeground(new Color(20, 26, 222));
		text.setFont(new Font("Calibri", 1, 14));
		text.setMargin(new Insets(1, 3, 0, 0));
		text.setColumns(20);
		text.setRows(5);
		text.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		text.setEditable(false);
	}
//---------------------------------------------------------------------------------------------
	void activate()
	{
		setVisible(true);
    	setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	@SuppressWarnings("unchecked")
	void describeClass(Object object)
	{
		try 
		{
			text.append("\n====================================================");
			Class c = object.getClass();
			text.append("\nNew Class Invoked:  "+c.toString());
			
			text.append("\n\nConstructors:\n");
			//textBox.append("\n-----------------------");
			Constructor ct[] = c.getDeclaredConstructors();
            for (int i = 0; i < ct.length; i++) {
                        text.append("\n"+ct[i].toString());
                    }
            
            
			text.append("\n\nMethods:\n");
			//textBox.append("\n-----------------------");
            Method m[] = c.getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                        text.append("\n"+m[i].toString());
                    }
            
            text.append("\n\nFields:\n");
			//textBox.append("\n-----------------------");
            Field f[] = c.getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                        text.append("\n"+f[i].toString());
                    }
            text.append("\n====================================================\n");
            text.setCaretPosition(text.getDocument().getLength());
         }
         catch (Throwable e) {
            System.err.println(e);
         }
	}
	void setText(String s)
	{
		text.append("\n"+s);
		text.setCaretPosition(text.getDocument().getLength());
	}
}
