/*
•	void putTime() – se introduce timpul in minute si secunde;
•	void initTime(int period) – se introduce timpul total de simulare a cozilor;
•	void setActive() – se activeaza ghiseele;
•	void setReady() – daca ghiseele au clienti atunci ele sunt active, in caz contrar nu;
•	void setX(int x) – sunt date coordonatele ghiseelor;

*/

package pack;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Queue extends JPanel implements ActionListener
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel();
	private JLabel clock = new JLabel();

	private DecimalFormat dFormat = new DecimalFormat("00");
	private Speed sp;
	private Timer timer;
	private long time;
	private long stopTime;
	protected boolean ready;
	protected boolean go;
//---------------------------------------------------------------------------------------------
    @SuppressWarnings("LeakingThisInConstructor")
	Queue(Speed sp, Timer timer, int i)
	{
		this.sp = sp;
		this.timer = timer;
		timer.addActionListener(this);
		
		setBackground(Color.LIGHT_GRAY);
		add(label);
		label.setFont(new Font("Calibri", 1, 12));
		label.setText(Integer.toString(i));
	}
//---------------------------------------------------------------------------------------------
	private void putTime()
	{
		long sec = time/1000;
		long min = sec/60;
		sec = sec-(min*60);
		clock.setText(dFormat.format(min) + ":" + dFormat.format(sec));
	}
	void initTime(int period)
	{
		time = 0;
		putTime();
		stopTime = time + period*1000;
		go = false;
	}
	void setActive()
	{
		setBackground(new Color(248, 1, 7));
		
		add(clock);
		clock.setFont(new Font("Calibri", 0, 12));
		clock.setForeground(Color.WHITE);
		clock.setText("00:00");
	}
	void setReady(boolean g)
	{
		if (g) {
                setBackground(new Color(10, 134, 10));
            }
		else {
                setBackground(new Color(120, 1, 7));
            }
		ready = g;
	}
	void setX(int x)
	{
		setBounds(x, 490, 50, 50);
	}
	
    @Override
    @SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer && !go)
		{
			time += 10+sp.speed*100; putTime();
			if (time >= stopTime) {go = true; time = 0; putTime();}
		}
	}
}
