/*
•	void activati() – metoda data presupune vizualizarea ferestrei in care vom putea micsora sau mari timpul de simulare a programului;
*/

package pack;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Speed extends JFrame implements ChangeListener
{
//---------------------------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0,2,0);
	private JLabel label = new JLabel("   Speed");
	
	private Dimension d = new Dimension(200, 150);
	private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	
	protected static int speed = 0;
//---------------------------------------------------------------------------------------------
    @SuppressWarnings("LeakingThisInConstructor")
	Speed()
	{
		setTitle("Speed");
		setPreferredSize(d);
		setLocation(s.width / 3 - d.width / 2, s.height / 6);
		setResizable(false);
		setLayout(new GridLayout(2,1));
		
		add(label);
		label.setFont(new Font("calibri",Font.PLAIN,13));
		add(slider);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(0);
		slider.setPaintTicks(true);
		
		pack();
	}
//---------------------------------------------------------------------------------------------
	void activate()
	{
		setVisible(true);
    	setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
    @Override
	public void stateChanged(ChangeEvent ce)
	{
		speed = slider.getValue();
	}
}