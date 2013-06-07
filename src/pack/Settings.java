/*
•	void addComponents(Container pane) – se va aduga un container in care se va contine toate optiunile ce se pot modifica;
•	JLabel createLabel(int i, int tip) – se va crea un camp inaintea textField-urilor unde se va scrie ce trebuie de introdus in acel loc;
•	JTextField createTextField(int i) – se creeaza campul unde va trebui introdusa informatia;
•	String defaultValues(int i) – metoda data va pune in campurile testFiled-urilor niste valori predefinite;
•	void execute() – se vor lua toate datele introduse si se vor verifica daca sunt corecte;
•	void activati() – metoda data ajuta la activarea vizualizarii acestei clase;

*/

package pack;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Settings extends JFrame implements KeyListener
{
//---------------------------------------------------------------------------------------------
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private Vector<JTextField> textField;
    private JButton button = new JButton("Save");
    private Dimension d = new Dimension(440, 280);
    private Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
    private String text;
    protected static int[] param;
    private int indice;
    private int k;
//---------------------------------------------------------------------------------------------
    @SuppressWarnings("UseOfObsoleteCollectionType")
    Settings()
    {
        textField = new Vector<JTextField>();
        param = new int[7];

        setTitle("Settings");
        setPreferredSize(d);
        setLocation(s.width / 3 - d.width / 2, s.height / 6);
        setResizable(false);
        setLayout(new GridBagLayout());
        addComponents(getContentPane());
        button.addActionListener(new ActionListener()
		{
                    @Override
            public void actionPerformed(ActionEvent e)
            {
            	execute();
            }
        });
        pack();
    }
 //---------------------------------------------------------------------------------------------
    private void addComponents(Container pane)
    {
        int m = 0, n = 1, t = 1;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        indice = 0;
        c.anchor = GridBagConstraints.LINE_START;
        for (int i = 0; i < 11; i++)
        {
            if (i == 1) {m += 2; c.insets = new Insets(1, 10, 1, 1);}
            else {
                c.insets = new Insets(1, 1, 1, 1);
            }
            if (i == 4 || i == 8) {m++; c.insets = new Insets(1, 10, 1, 1);}
            else {
                c.insets = new Insets(1, 1, 1, 1);
            }
            if (i == 2) {m = 0; n += 3;}
            if (i == 6) {m = 0; n++;}
            if (i == 10) {m = 0; n += 2;}
            c.gridx = m;
            c.gridy = n;
            pane.add(createTextField(i), c);
            m++;
        }
        m = 0; n = 0;
        for (int i = 0; i < 13; i++)
        {
            if (i == 1 || i == 3 || i == 6) {
                c.insets = new Insets(1, 10, 1, 1);
            }
            else {
                c.insets = new Insets(1, 1, 1, 1);
            }
            if (i == 1 || i == 3 || i == 9 || i == 11) {
                m += 2;
            }
            if (i == 6 || i == 8) {
                m++;
            }
            if (i == 2) {m = 0; n += 2;}
            if (i == 4 || i == 12) {m = 0; n++;}
            if (i == 8 || i == 10) {m = 2; n++;}
            if (i > 3 && i < 12) {t = 2; c.gridwidth = 1;}
            else {t = 1; c.gridwidth = 3;}
            c.gridx = m;
            c.gridy = n;
            pane.add(createLabel(i, t), c);
            m++;
        }
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 2;
        c.gridy = 8;
        pane.add(button, c);
    }
    private JLabel createLabel(int i, int tip)
    {
        switch (i)
        {
            case 0: text = "Numar clienti:";
                    break;
            case 1: text = "Numarul de cozi:";
                    break;
            case 2: text = "Interval de sosire a clientilor:";
                    break;
            case 3: text = "Interval de servire:";
                    break;
            case 4: text = "min";
                    break;
            case 5: text = "sec";
                    break;
            case 6: text = "min";
                    break;
            case 7: text = "sec";
                    break;
            case 8: text = "minim";
                    break;
            case 9: text = "minim";
                    break;
            case 10:text = "maxim";
                    break;
            case 11: text = "maxim";
                     break;
            case 12: text = "Timpul de simulare(minute):";
                     break;
            default: text = "";
                     break;
        }
        JLabel label = new JLabel(text);
        if (tip == 1) {
            label.setFont(new Font("Calibri", 0, 14));
        }
        else {
            label.setFont(new Font("Calibri", 2, 13));
        }
        return label;
    }
    private JTextField createTextField(int i)
    {
        text = defaultValues(i);
        if (i < 2) {param[indice] = Integer.parseInt(text); indice++;}
        else if (i%2 == 0) {param[indice] = Integer.parseInt(text)*60; indice++;}
        textField.addElement(new JTextField(text));
        textField.lastElement().addKeyListener(this);
        textField.lastElement().setFont(new Font("Calibri", 0, 14));
        textField.lastElement().setColumns(3);
        textField.lastElement().setSelectionStart(getX());
        return textField.lastElement();
    }
    private String defaultValues(int i)
    {
        switch (i)
        {
            case 0: text = "100";
                    break;
            case 1: text = "5";
                    break;
            case 2: text = "1";
                    break;
            case 3: text = "0";
                    break;
            case 4: text = "5";
                    break;
            case 5: text = "0";
                    break;
            case 6: text = "2";
                    break;
            case 7: text = "0";
                    break;
            case 8: text = "10";
                    break;
            case 9: text = "0";
                    break;
            case 10: text = "120";
                     break;
            default: text = "";
                     break;
        }
        return text;
    }
    private void execute()
    {
        indice = 0;
        for (int i = 0; i < 12; i++)
        {
        	if (i == 10){break;}	
        	if (i == 2){i = 10; indice = 6;}
        	if (i == 11){i = 2; indice = 2;}
            try
            {
            	if (getInt(i) == 0) {
                    textField.elementAt(i).setText("0");
                }
            	if (i < 2) {
                    k = getInt(i);
                }
            	else if (i%2 == 0) {
                    k = getInt(i)*60;
                }
            	else
            	{	
            		int q = getInt(i);
            		if (q >= 60) {textField.elementAt(i-1).setText(Integer.toString(getInt(i-1)+q/60)); 
            		textField.elementAt(i).setText(Integer.toString(q%60));}
            		k += q;
            	}
            	if (i < 2 || i > 9 || i%2 != 0){
            	if (k == param[indice]) {
                        indice++;
                    }
            	else {param[indice] = k; indice++;}}
            }
            catch(Exception e)
            {
            	textField.elementAt(i).setText("0");
            	if (i < 2 || i > 9 || i%2 != 0) {param[indice] = 0; indice++;}
            }
        }
        if (param[0] <= 0) {param[1] = 1; textField.elementAt(0).setText("1");}
        if (param[1] <= 0) {param[1] = 1; textField.elementAt(0).setText("1");}
        if (param[6] <= 0) {param[1] = 1; textField.elementAt(0).setText("1");}
        if (param[0] > 9999) {param[0] = 9999; textField.elementAt(0).setText("9999");}
        if (param[1] > 14) {param[1] = 14; textField.elementAt(1).setText("14");}
        if (param[6] > 360000)
        {
        	param[6] = 359999;
        	textField.elementAt(10).setText("6000");
        }
        for (int i = 2; i < 6; i++) 
        {
        	if (param[i] > 5999){param[i] = 5999; textField.elementAt(2*(i-1)).setText("99"); textField.elementAt(2*(i-1)+1).setText("59");}
        	if (param[i] < 7) {param[i] = 7; textField.elementAt(2*(i-1)+1).setText("7");}
        }
        if (param[2] >= param[4])
        {
            param[2] = param[4]-1;
            textField.elementAt(2).setText(Integer.toString(param[2]/60));
            textField.elementAt(3).setText(Integer.toString(param[2]%60));
        }
        if (param[3] >= param[5])
        {
            param[3] = param[5]-1;
            textField.elementAt(4).setText(Integer.toString(param[3]/60));
            textField.elementAt(5).setText(Integer.toString(param[3]%60));
        }
        
        setVisible(false);
    }
    private int getInt(int i)
    {
    	return Integer.parseInt(textField.elementAt(i).getText());
    }
    void activate()
    {
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
                execute();
                setVisible(false);
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
