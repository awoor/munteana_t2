/* 
•	void run() – reseteaza coordonatele clientilor in coada si invoca redesenarea;
*/
package pack;

import java.util.*;

public class Mover implements Runnable
{
//---------------------------------------------------------------------------------------------
	private Panel p = new Panel();
//---------------------------------------------------------------------------------------------	
	Mover(Panel panel)
	{
		p = panel;
	}
//---------------------------------------------------------------------------------------------
	private int selectQueue()
	{
		int i = 0, min = p.customers[0].size(),index = 0;
		
		while(p.customers[i].size() >= 5 && i < p.param[1]) 
		{
			if (p.customers[i].size() < min) {min = p.customers[i].size(); index = i;}
			i++;
		}
		if (i == p.param[1]) {
                return index;
            }
		if (!p.queue.elementAt(i).ready) {p.queue.elementAt(i).setReady(true); p.ar.setText("Queue #"+(i+1)+" is ACTIVE");}
		return i;
	}
	@Override
    @SuppressWarnings({"SleepWhileInLoop", "static-access"})
	public void run()
	{
		Customer c;
		Random random = new Random();
		int index;
       	
		while (true)
		{
			for (int j = 0; j < p.customers[p.param[1]].size(); j++)
			{
				c = p.customers[p.param[1]].elementAt(j);
				if (c.getY() < 24) {
                                c.incY();
                            }
				else if (c.getY() == 24)
				{
					if (c.stop && c.run) {
                                        c.initTime();
                                    }
					if (c.run)
					{	
						index = selectQueue();
						p.number.setText(Integer.toString(index+1));
						c.incY();
						p.customers[index].addElement(c);
						p.customers[p.param[1]].remove(j);
						p.ar.setText("Client #"+c.getText()+" JIONED queue #"+(index+1)+" at "+p.putTime());
					}
				}
			}
			for(int i = 0; i < p.param[1]; i++)
			{
				for (int j = 0; j < p.customers[i].size(); j++)
				{
					c = p.customers[i].elementAt(j);
					if (c.getY() < 55){ if (!c.stop) {
                                                c.incY();
                                            }}
					else if (c.getX() <= 18+55*i) {
                                        c.incX();
                                    }
					else if (c.getY() < 460) 
					{
						if (j > 0)
						{
							if (p.customers[i].elementAt(j-1).stop || c.getY() == 420) 
							{
								if (j == 1){ if (c.getY() < 420) {
                                                                        c.incY();
                                                                    } else {
                                                                        c.stop = true;
                                                                    }}
								else {if (c.getY() < 450-j*30) {
                                                                        c.incY();
                                                                    } else {
                                                                        c.stop = true;
                                                                    }}
							}
							else {c.incY(); c.stop = false;}
						}
						else {c.incY(); c.stop = false;}
					}
					else if (c.getY() == 460)
					{
						if (!c.stop) 
						{
							c.setHappy();
							p.queue.elementAt(i).initTime(random.nextInt(p.param[5]-p.param[3])+p.param[3]);
							c.stop = true;
						}
						else if (p.queue.elementAt(i).go) {c.stop = false; c.incY();}
					}
					else if (c.getY() < 480) {
                                        c.incY();
                                    }
					else if (c.getY() == 480)
					{
						p.ar.setText("Client #"+c.getText()+" LEFT queue #"+(i+1)+" at "+p.putTime());
						p.remove(p.customers[i].elementAt(j));
						p.customers[i].remove(j);
						p.repaint();
						j--;
						p.maxClients--;
						if (i > 1) {if (p.customers[i].isEmpty()) {p.queue.elementAt(i).setReady(false); p.ar.setText("Queue #"+(i+1)+" is CLOSED");}}
					}
				}
			}
			try
			{
				Thread.sleep(20-(p.sp.speed*10-1));
			}
			catch(InterruptedException _e){}
		}
	}
}
