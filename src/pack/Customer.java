/* 
 •	void setNervous() – metoda folosita pentru afisarea clientilor care asteapta foarte mult timp 
in coada cu ajutorul unei „smile” care este nervos;
•	void setAngry() – metoda folosita pentru desemnarea clientilor care sunt suparati din cauza timpului pierdut in coada, 
care sunt la limita de nervozitate;
•	void setHappy() – metoda de afisare a clientilor care sunt bine dispusi care petrec in coada foarte putin timp;
•	void incX() si void incY() – metoda pentru stabilirea coordonatelor clientului in sala;
•	void initTime() – metoda pentru stabilirea timerului;

*/
package pack;

import java.awt.event.*;
import javax.swing.*;

public class Customer extends JLabel implements ActionListener
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private Speed sp;
	private Timer timer;
	private long time;
	private long stopTime;
	private int x, y;
	private boolean _run;
	protected boolean run;
	protected boolean stop;
//---------------------------------------------------------------------------------------------
	Customer()
	{
	}
    @SuppressWarnings("LeakingThisInConstructor")
	Customer(Speed sp, Timer timer)
	{
		this.sp = sp;
		this.timer = timer;
		timer.addActionListener(this);
		time = 0;
		stopTime = -1;
		x = 20;
		y = 20;
		_run = true;
		run = true;
		stop = false;
		
		setIcon(new ImageIcon("Images/smile.gif"));
		setBounds(x, y, 60, 20);
	}
//---------------------------------------------------------------------------------------------
	private void setNervous()
	{
		setIcon(new ImageIcon("Images/nervous.gif"));
	}
	private void setAngry()
	{
		setIcon(new ImageIcon("Images/angry.gif"));
	}
	void setHappy()
	{
		setIcon(new ImageIcon("Images/happy.gif"));
	}
	void incX()
	{
		x++;
		setBounds(x, y, 60, 20);
	}
	void incY()
	{
		y++;
		setBounds(x, y, 60, 20);
	}
	void initTime()
	{
		stopTime = time+1000;
		run = false;
		stop = true;
	}
	void setNumber(int number)
	{
		setText(Integer.toString(number));
	}
	//@SuppressWarnings("static-access")
    @Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer && _run)
		{
			time += 10*100;
			if (time >= stopTime && stopTime > 0) 
			{run = true; stop = false; stopTime = -1;}
			
			if (time > 900000) 
			{ _run = false;}
			
			 if (time > 480000 && time<800000) 
			{setNervous();} 
			
			else if ( time >800000)
			{ setAngry(); }
		}
	}
}
