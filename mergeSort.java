import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/* <applet code = "mergeSort" width = 850 height = 850> </applet> */
public class mergeSort extends Applet
    implements ActionListener, TextListener
{

    static Button next = new Button(" N E X T ");
    static Button start=new Button(" S T A R T ");
    static Button finish = new Button(" F I N I S H ");
    static Button cont = new Button(" C O N T I N U E ");
    static Button reset = new Button(" R E S E T ");
    static TextField tf = new TextField(2);
    Label inpl=new Label("Enter the number of elemnts");
    condition cndtn;
    myCanvas mc;
    messageCanvas msg;
    Animate anim;
    
    public void init()
    {
       
         int x = 5;
        int y = 70;
        int wd = 100;
        int ht = 25;
        int gap = 50;
        setLayout(null);
        setBackground(Color.lightGray);
        add(mc);
        add(start);
        start.setBounds(x, y,wd,ht);
        x=x+wd+gap;
        if(!start.isEnabled())
        {
            start.setEnabled(true);
        }
        start.addActionListener(this);
        add(cont);
        cont.setBounds(x, y,wd,ht);
         x=x+wd+gap;
        if(cont.isEnabled())
        {
            cont.setEnabled(false);
        }
        cont.addActionListener(this);
        add(next);
        next.setBounds(x, y,wd,ht);
         x=x+wd+gap;
        if(next.isEnabled())
        {
            next.setEnabled(false);
        }
        next.addActionListener(this);
        add(finish);
        finish.setBounds(x, y,wd,ht);
        x=x+wd+gap;
        if(finish.isEnabled())
        {
            finish.setEnabled(false);
        }
        finish.addActionListener(this);
        add(reset);
        reset.setBounds(x, y,wd,ht);
        x=x+wd+gap;
        if(reset.isEnabled())
        {
            reset.setEnabled(false);
        }
        reset.addActionListener(this);
        add(inpl);
        inpl.setBounds(x, y,wd+105,ht);
          x=x+wd+gap+40;
        inpl.setBackground(Color.lightGray);
        inpl.setFont(new Font("Callibri", 1, 15));
        add(tf);
        tf.setBounds(x+22, 66, 50, 34);
        x = x + wd / 2 + gap;
       tf.setBackground(Color.white);
       tf.setFont(new Font("Callibri", 1, 17));
        if(tf.isEnabled())
        {
            tf.setEnabled(false);
        }
        if(tf.getText().compareTo("") != 0)
        {
           tf.setText(null);
        }
        tf.addTextListener(this);
        add(msg);
        msg.setLocation(0, 100);
        add(anim);
        anim.setLocation(0, 205);
    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(" N E X T ".compareTo(ae.getActionCommand()) == 0)
            {
                next.setEnabled(false);
                finish.setEnabled(false);
                cndtn.NOTIFY();
            }
        }
        catch(IllegalThreadStateException _ex)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException _ex2) { }
        }
        try
        {
            if(" C O N T I N U E ".compareTo(ae.getActionCommand()) == 0)
            {
                cont.setEnabled(false);
                cndtn.NOTIFY();
            }
        }
        catch(IllegalThreadStateException _ex)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException _ex2) { }
        }
        try
        {
            if(" F I N I S H ".compareTo(ae.getActionCommand()) == 0)
            {
                next.setEnabled(false);
                finish.setEnabled(false);
                anim.run_to_finish = true;
                cndtn.NOTIFY();
            }
        }
        catch(IllegalThreadStateException _ex)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException _ex2) { }
        }
        try
        {
            if(" S T A R T ".compareTo(ae.getActionCommand()) == 0)
            {
                start.setEnabled(false);
                anim.animation.start();
            }
        }
        catch(IllegalThreadStateException _ex)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException _ex2) { }
        }
        if(" R E S E T ".compareTo(ae.getActionCommand()) == 0)
        {
            reset.setEnabled(false);
            try
            {
                anim.resetAll();
                anim.animation = new Thread(anim);
                anim.animation.start();
                return;
            }
            catch(IllegalThreadStateException _ex) { }
            try
            {
                Thread.sleep(1000);
                return;
            }
            catch(InterruptedException _ex)
            {
                return;
            }
        } else
        {
            return;
        }
    }

    public void textValueChanged(TextEvent te)
    {
        if(tf.getText().compareTo("") != 0)
        {
            msg.setMessage(" press 'NEXT' button after entering size");
            msg.repaint();
            next.setEnabled(true);
            if(tf.getText().length() == 2)
            {
               tf.setEnabled(false);
            }
        }
    }

    public mergeSort()
    {
        
        cndtn = new condition();
        mc = new myCanvas();
        msg = new messageCanvas();
        anim = new Animate(msg, cndtn);
    }

}
