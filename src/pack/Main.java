/* 
•	void init() – metoda data permite afisarea frame-ului  de baza, 
care va contine o bara de instrumente din care se vor accesa celelalte ferestre;
*/
package pack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import pack.Panel;

public class Main extends JFrame
{
//---------------------------------------------------------------------------------------------	
	private static final long serialVersionUID = 1L;
	private Panel panel;
	private Thread th;
	private About ab;
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem log;
	private JMenuItem exit;
	private JMenu edit;
	private JMenuItem start;
	//private JMenuItem resume;
	private JMenuItem speed;
	private JPopupMenu.Separator separator;
	private JMenuItem settings;
	private JMenu help;
	private JMenuItem about;
//---------------------------------------------------------------------------------------------
	Main()
	{
		init();
	}
//---------------------------------------------------------------------------------------------	
	private void init()
	{
		panel = new Panel();
		panel.ar.describeClass(this);//-----
		panel.ar.describeClass(panel);//----
		th = new Thread(panel);
		ab = new About();
		panel.ar.describeClass(ab);//----
		menu = new JMenuBar();
		file = new JMenu("File");
		log = new JMenuItem("Log");
		exit = new JMenuItem("Exit");
		edit = new JMenu("Edit");
		start = new JMenuItem("Start");
		//resume = new JMenuItem("Resume");
		speed = new JMenuItem("Speed");
		separator = new JPopupMenu.Separator();
		settings = new JMenuItem("Options");
		help = new JMenu("?");
		about = new JMenuItem("About");
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Queue");
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		
		add(panel);
		setJMenuBar(menu);
		menu.add(file);
		file.setFont(new Font("Calibri",Font.PLAIN,13));
		file.add(log);
		log.setFont(new Font("Calibri",Font.PLAIN,13));
		log.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0));
		log.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
            	panel.viewArchive();
            }
        });
		file.add(exit);
		exit.setFont(new Font("Calibri",Font.PLAIN,13));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
		exit.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
               System.exit(0);
            }
        });
		menu.add(edit);
		edit.setFont(new Font("Calibri",Font.PLAIN,13));
		edit.add(start);
		start.setFont(new Font("Calibri",Font.PLAIN,13));
		start.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, 0));
		start.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
               if (!th.isAlive()) {
                    th.start();
                }
            }
        });
		//edit.add(resume);
	//	resume.setFont(new Font("Calibri",Font.PLAIN,13));
		//resume.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_R, ActionEvent.ALT_MASK));
		/*	resume.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e)
            {
            	//init();???
            }
        }); */
		edit.add(speed);
		speed.setFont(new Font("Calibri",Font.PLAIN,13));
		speed.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		speed.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
            	panel.viewSpeed();
            }
        });
		edit.add(separator);
		edit.add(settings);
		settings.setFont(new Font("Calibri",Font.PLAIN,13));
		settings.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, 0));
		settings.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
            	panel.viewSettings();
            }
        });
		menu.add(help);
		help.setFont(new Font("Calibri",Font.PLAIN,13));
		help.add(about);
		about.setFont(new Font("Calibri",Font.PLAIN,13));
		about.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
            	ab.activate();
            }
        });
		
		pack();
	}
	public static void main(String[] args)
	{
        Main main = new Main();
	}
}
