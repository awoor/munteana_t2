/*
•	void initTime(int period) – metoda cu ajutorul caruia stabilim timpul general;
•	String putTime() – permite introducerea timpului de sosire si de servire a clientilor la ghisee in minute si secunde;
•	void init() – metoda ce sincronizeaza time-erele si porneste in intregime programul;
•	void Start() – la activarea acesteia incep sa mearga clienti la ghisee, astfel, se incep a forma cozi;
•	void viewArchive() – se activeaza arhiva cu informatie;
•	void viewSpeed() – se activeaza fereastra ce raspunde de modificarea timpului programului;
•	void viewSettings() – se activeaza optiunile pe care utilizatorul le poate schimba;
•	void run() – porneste in mod concurent metoda init si metoda Start;
*/

package pack;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import pack.Queue;

public class Panel extends JPanel implements Runnable, ActionListener
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	protected Archive ar;
	protected Speed sp;
	private Settings st;
	private Mover move;
	private Thread th;
    @SuppressWarnings("UseOfObsoleteCollectionType")
	protected Vector<Queue> queue;
    @SuppressWarnings("UseOfObsoleteCollectionType")
	protected Vector<Customer>[] customers;
	protected int[] param;
	private Timer timer;
	private long time;
	private long stopTime;
	private boolean run;
	private boolean stop;
	private DecimalFormat dFormat;
	private int clients;
	protected int maxClients;
	private JPanel info;
	private JPanel line;
	protected JLabel number;
	private JLabel clock;
//---------------------------------------------------------------------------------------------
    @SuppressWarnings("UseOfObsoleteCollectionType")
	Panel()
	{
		ar = new Archive();
		ar.describeClass(ar);//----
		sp = new Speed();
		ar.describeClass(sp);//----
		st = new Settings();
		ar.describeClass(st);//----
		queue = new Vector<Queue>();
		param = new int[7];
		timer = new Timer(1, this);
		info = new JPanel();
		line = new JPanel();
		number = new JLabel();
		clock = new JLabel("00:00:00");
		
		setBackground(Color.WHITE);
		setLayout(null);

		add(clock);
		clock.setForeground(Color.BLACK);
		clock.setFont(new Font("Calibri", 1, 20));
		clock.setBounds(620, 0, 170, 20);
		clock.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(info);
		info.setBackground(Color.RED);
		info.setBounds(70, 20, 28, 28);
		info.add(number);
		number.setFont(new Font("Calibri", 0, 14));
		add(line);
		line.setBackground(Color.YELLOW);
		line.setBounds(0, 440, 800, 10);
		for (int i = 0; i < 14; i++)
		{	
			queue.addElement(new Queue(sp, timer, i+1));
			add(queue.lastElement());
			queue.lastElement().setX(5+i*55);
		}
		ar.describeClass(queue.elementAt(0));//----
	}
//---------------------------------------------------------------------------------------------
	private void initTime(int period)
	{
		stopTime = time-period*1000;
		stop = false;
	}
	protected String putTime()
	{
		long sec = time/1000;
		long min = sec/60;
		sec = sec-(min*60);
		long hour = min/60;
		min = min-(hour*60);
		clock.setText(dFormat.format(hour) + ":" +dFormat.format(min) + ":" + dFormat.format(sec));
		return dFormat.format(hour) + ":" +dFormat.format(min) + ":" + dFormat.format(sec);
	}
	@SuppressWarnings({ "unchecked", "static-access", "UseOfObsoleteCollectionType" })
	private void init()
	{
		param = st.param;
		clock.setForeground(Color.RED);
		dFormat = new DecimalFormat("00");
		time = param[6]*1000;
		ar.setText("Start time :"+putTime());
		customers = new Vector[param[1]+1];
		for (int i = 0; i < param[1]; i++)
		{
			queue.elementAt(i).setActive();
			customers[i] = new Vector<Customer>();
		}
		queue.elementAt(0).setReady(true);
		ar.setText("Queue #1 is ACTIVE");
		customers[param[1]] = new Vector<Customer>();
		timer.start();
		move = new Mover(this);
		ar.describeClass(move);//----
		th = new Thread(move);
	}
	private void start()
	{
		Customer c = new Customer();
		ar.describeClass(c);//----
		Random random = new Random();
		final int max = param[1]*13;
		boolean generate = true;
		
		run = true;
		clients = 0;
		maxClients = 0;
		timer.start();
		th.start();
		while (run)
		{
			if (maxClients < max && clients < param[0])
			{
				if (generate)
				{
					initTime(random.nextInt(param[4]-param[2])+param[2]);
					generate = false;
				}
				else if (stop)
					{
						clients++; maxClients++; 
						generate = true;
						c = new Customer(sp ,timer);
						add(c); 
						c.stop = true;
						c.setText(Integer.toString(clients));
						customers[param[1]].addElement(c);
						ar.setText("Client #"+clients+" ENTERED at "+putTime()+"\n");
					}
			}
		}
	}
	void viewArchive()
	{
		ar.activate();
	}
	void viewSpeed()
	{
		sp.activate();
	}
	void viewSettings()
	{
		st.activate();
	}
    @Override
	public void run()
	{
		init();
		start();
	}	
    @Override
    @SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer && run)
		{
			time -= 10+sp.speed*100;
			if (time <= 0) {run = false; time = 0; clock.setForeground(Color.BLACK); ar.setText("Time is OVER!");/*timer.stop();*/}
			putTime();	
			if (stopTime >= time && !stop) {
                        stop = true;
                    }
		}
	}
}
