import java.awt.*;
//import java.io.PrintStream;
import java.util.Random;

class Animate extends Canvas
    implements Runnable
{
    
    Thread animation;
    messageCanvas msg;
    condition cndtn;
    Graphics gr;
    Image img;
    boolean startOut;
    Dimension d;
    String Comment;
    int arr_size;
    int arr_base_value;
    Random r;
    float ff;
    Box button[];
    Box tempb[];
    boolean run_to_finish;
    boolean fdisplaycolorterminology;
    boolean fdraw;
    boolean fdrawB;
    boolean fdisplaybarray;
    int displaybindex;
    int drawx[];
    int drawy[];
    int drawno[];
    int draw_index;
    final int sleep_time = 5;

    public Animate(messageCanvas messagecanvas, condition condition1)
    {
        startOut = true;
        r = new Random();
        button = new Box[12];
        drawx = new int[15];
        drawy = new int[15];
        drawno = new int[15];
        setSize(1025, 575);
        setVisible(true);
        setBackground(Color.white);
        msg = messagecanvas;
        cndtn = condition1;
        animation = new Thread(this);
        repaint();
    }

    public void paint(Graphics g)
    {
        update(g);
    }

    public void update(Graphics g)
    {
        if(startOut)
        {
            startOut = false;
            d = getSize();
            img = createImage(d.width, d.height);
            gr = img.getGraphics();
        }
        if(d.width != getSize().width || d.height != getSize().height)
        {
            d = getSize();
            img = createImage(d.width, d.height);
            gr = img.getGraphics();
        }
        gr.setColor(Color.lightGray);
        gr.fillRect(0, 0, d.width, d.height);
        if(fdisplaycolorterminology)
        {
            displayColorTerminology(gr);
        }
        if(fdraw)
        {
            for(int i = 0; i < draw_index; i++)
            {
                draw(gr, drawx[i], drawy[i], drawno[i]);
            }

        }
        if(fdrawB)
        {
            for(int j = 0; j < arr_size; j++)
            {
                if(button[j].value != -1)
                {
                    drawB(gr, button[j].xcoordinate, button[j].ycoordinate, button[j].value, button[j].color);
                }
            }

        }
        if(fdisplaybarray)
        {
            for(int k = 0; k < displaybindex; k++)
            {
                drawB(gr, tempb[k].xcoordinate, tempb[k].ycoordinate, tempb[k].value, tempb[k].color);
            }

        }
        g.drawImage(img, 0, 0, this);
    }

    public void drawB(Graphics g, int i, int j, int k, int l)
    {
        String s = new String("");
        Font font = new Font("Serief", 1, 20);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        s = s + k;
        switch(l)
        {
        case 0: // '\0'
            g.setColor(Color.red);
            break;

        case 1: // '\001'
            g.setColor(Color.blue);
            break;

        case 2: // '\002'
            g.setColor(Color.cyan);
            break;
        }
        g.fill3DRect(i + 5, j + 5, 30, 30, true);
        g.setColor(Color.white);
        
        int i1 = i + 5 + (30 - fm.stringWidth(s)) / 2;
        int j1 = j + 5 + fm.getAscent() + (30 - fm.getAscent()) / 2;
        g.drawString(s, i1, j1);
    }

    public void resetAll()
    {
        if(mergeSort.tf.isEnabled())
        {
            mergeSort.tf.setEnabled(false);
        }
        if(mergeSort.tf.getText().compareTo("") != 0)
        {
            mergeSort.tf.setText(null);
        }
    }

    public void run()
    {
        draw_index = 0;
        run_to_finish = fdisplaycolorterminology = fdraw = fdrawB = fdisplaybarray = false;
        fdisplaycolorterminology = true;
        Comment = new String("Press 'CONTINUE' Button");
        msg.setMessage(Comment);
        updateCanvas(false);
        mergeSort.cont.setEnabled(true);
        cndtn.WAIT();
        fdisplaycolorterminology = false;
        repaint();
        getGraphics();
        Comment = new String("Enter a number between 2 and 12(Both Inclusive)");
        msg.setMessage(Comment);
        msg.repaint();
        do
        {
            mergeSort.tf.setEnabled(true);
            cndtn.WAIT();
            try
            {
                arr_size = Integer.parseInt(mergeSort.tf.getText(), 10);
            }
            catch(NumberFormatException _ex)
            {
                msg.setMessage("Number entered is wrong..Re-enter");
                msg.repaint();
                try
                {
                    Thread.sleep(500L);
                }
                catch(InterruptedException _ex2) { }
                mergeSort.tf.setEnabled(true);
                mergeSort.tf.setText(null);
                continue;
            }
            if(arr_size >= 2 && arr_size <= 12)
            {
                break;
            }
            msg.setMessage("Number is out of range..Re-enter");
            msg.repaint();
            try
            {
                Thread.sleep(500L);
            }
            catch(InterruptedException _ex) { }
            mergeSort.tf.setEnabled(true);
            mergeSort.tf.setText(null);
        } while(true);
        mergeSort.tf.setEnabled(false);
        arr_base_value = (getSize().width - arr_size * 40) / 2;
        Comment = "Generating Random Numbers";
        msg.setMessage(Comment);
        msg.repaint();
        int i = 0;
        for(int j = arr_base_value; i < arr_size; j += 40)
        {
            button[i] = new Box();
            ff = r.nextFloat();
            button[i].value = (int)(ff * 100);
            button[i].xcoordinate = j;
            button[i].ycoordinate = 42;
            button[i].color =0;
            i++;
        }

        drawx[draw_index] = arr_base_value;
        drawy[draw_index] = 42;
        drawno[draw_index] = arr_size;
        draw_index++;
        fdraw = true;
        fdrawB = true;
        updateCanvas(true);
        sort(0, arr_size - 1, arr_base_value, 42);
        mergeSort.next.setEnabled(false);
        mergeSort.finish.setEnabled(false);
        msg.setMessage(" SORTING IS OVER");
        updateCanvas(false);
        msg.setMessage("If You Want To RERUN The Algorithm Please Press The 'RESET' Button ");
        msg.repaint();
        mergeSort.reset.setEnabled(true);
    }

    public void sort(int i, int j, int k, int l)
    {
        Comment = "DIVIDE i.e.,dividing the array into sub arrays";
        msg.setMessage(Comment);
        updateCanvas(true);
        Comment = "or dividing the problem into subproblems";
        msg.setMessage(Comment);
        updateCanvas(true);
        if(i == j)
        {
            button[i].xcoordinate = k;
            button[i].ycoordinate = l;
            button[i].color = 1;
            Comment = "Single Element Array IS Already Sorted ";
            msg.setMessage(Comment);
            updateCanvas(true);
            return;
        }
        int i1 = k;
        int j1 = i + (j - i) / 2;
        k -= 40;
        l += 80;
        //Comment = "Leaving a Spacing Of One Bigger Square";
        //msg.setMessage(Comment);
        drawx[draw_index] = k;
        drawy[draw_index] = l;
        drawno[draw_index] = (j1 - i) + 1;
        draw_index++;
        fdraw = true;
        updateCanvas(true);
        int k1 = k;
        Comment = " Bringing The Elements Down ";
        msg.setMessage(Comment);
        msg.repaint();
        int l1 = 0;
        for(int i2 = i; l1 < (j1 - i) + 1; i2++)
        {
            drawMs(i1 + l1 * 40, k1 + l1 * 40, l, i2);
            l1++;
        }

        sort(i, j1, k, l);
        Comment = "DIVIDE OR Dividing The Array Into Two Sub Arrays";
        msg.setMessage(Comment);
        msg.repaint();
        Comment = "OR DIVIDING THE PROBLEM INTO TWO SUBPROBLEMS";
        msg.setMessage(Comment);
        msg.repaint();
        k = k + ((j1 - i) + 1) * 40 + 80;
        drawx[draw_index] = k;
        drawy[draw_index] = l;
        drawno[draw_index] = j - j1;
        draw_index++;
        updateCanvas(true);
        int j2 = k;
        Comment = " Bringing The Elements Down ";
        msg.setMessage(Comment);
        msg.repaint();
        int k2 = 0;
        for(int l2 = j1 + 1; k2 < j - j1; l2++)
        {
            drawMs(i1 + ((j1 - i) + 1) * 40 + k2 * 40, j2 + k2 * 40, l, l2);
            k2++;
        }

        sort(j1 + 1, j, k, l);
        Comment = "CONQUER";
        msg.setMessage(Comment);
        updateCanvas(true);
        if(!run_to_finish)
        {
            Comment = "Press Next to merge the 2 sorted arrays or Finish to complete soting";
            msg.setMessage(Comment);
            updateCanvas(false);
            mergeSort.next.setEnabled(true);
            mergeSort.finish.setEnabled(true);
           cndtn.WAIT();
        }
        if((j1 - i) + 1 == 1 || j - j1 == 1)
        {
            Comment = "NOTE : A Single ELement Array Is Sorted";
            msg.setMessage(Comment);
            updateCanvas(true);
        }
        Merge(i, j1, j, i1, k1, j2, l);
    }

    public void Merge(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        displaybindex = 0;
        tempb = new Box[k + 1];
        for(int l1 = 0; l1 < k + 1; l1++)
        {
            tempb[l1] = new Box();
        }

        int i2 = i;
        int j2 = i;
        int k2;
        for(k2 = j + 1; i2 <= j && k2 <= k;)
        {
            Comment = "Items Under Consideration are " + button[i2].value + "And" + button[k2].value;
            button[i2].color = 2;
            button[k2].color = 2;
            msg.setMessage(Comment);
            updateCanvas(true);
            Comment = "CONQUER";
            msg.setMessage(Comment);
            msg.repaint();
            if(button[i2].value <= button[k2].value)
            {
                Comment = "Since " + button[i2].value + "Is Less Than Or Equal To " + button[k2].value;
                msg.setMessage(Comment);
                msg.repaint();
                Comment = button[i2].value + "Is Selected For The Sorted Array Being Made";
                msg.setMessage(Comment);
                button[i2].color = 1;
                updateCanvas(true);
            } else
            {
                Comment = "Since " + button[k2].value + "Is Less Than " + button[i2].value;
                msg.setMessage(Comment);
                msg.repaint();
                Comment = button[k2].value + "Is Selected For The Sorted Array Being Made";
                msg.setMessage(Comment);
                button[k2].color = 1;
                updateCanvas(true);
            }
            updateCanvas(true);
            if(button[i2].value <= button[k2].value)
            {
                tempb[displaybindex].value = button[i2].value;
                tempb[displaybindex].xcoordinate = button[i2].xcoordinate;
                tempb[displaybindex].ycoordinate = button[i2].ycoordinate;
                tempb[displaybindex].color = button[i2].color;
                button[i2].value = -1;
                displaybindex++;
                fdisplaybarray = true;
                updateCanvas(true);
                i2++;
                drawMerge(l, i1, k1, displaybindex - 1);
                l += 40;
                i1 += 40;
            } else
            {
                tempb[displaybindex].value = button[k2].value;
                tempb[displaybindex].xcoordinate = button[k2].xcoordinate;
                tempb[displaybindex].ycoordinate = button[k2].ycoordinate;
                tempb[displaybindex].color = button[k2].color;
                button[k2].value = -1;
                displaybindex++;
                fdisplaybarray = true;
                k2++;
                drawMerge(l, j1, k1, displaybindex - 1);
                l += 40;
                j1 += 40;
            }
            j2++;
        }

        if(i2 > j && k2 <= k || k2 > k && i2 <= j)
        {
            Comment = "CONQUER";
            msg.setMessage(Comment);
            msg.repaint();
            Comment = "Sincs One SubArray Is Exhausted";
            msg.setMessage(Comment);
            msg.repaint();
            Comment = " The Other Being Sorted Is Copied As It Is";
            msg.setMessage(Comment);
            msg.repaint();
        }
        if(i2 > j)
        {
            for(int l2 = k2; l2 <= k; l2++)
            {
                tempb[displaybindex].value = button[l2].value;
                tempb[displaybindex].xcoordinate = button[l2].xcoordinate;
                tempb[displaybindex].ycoordinate = button[l2].ycoordinate;
                tempb[displaybindex].color = 1;
                button[l2].value = -1;
                displaybindex++;
                drawMerge(l, j1, k1, displaybindex - 1);
                j1 += 40;
                l += 40;
            }

        } else
        {
            for(int i3 = i2; i3 <= j; i3++)
            {
                tempb[displaybindex].value = button[i3].value;
                tempb[displaybindex].xcoordinate = button[i3].xcoordinate;
                tempb[displaybindex].ycoordinate = button[i3].ycoordinate;
                tempb[displaybindex].color = 1;
                button[i3].value = -1;
                displaybindex++;
                drawMerge(l, i1, k1, displaybindex - 1);
                i1 += 40;
                l += 40;
            }

        }
        updateCanvas(true);
        int j3 = i;
        for(int k3 = 0; j3 <= k && k3 < displaybindex; k3++)
        {
            button[j3].value = tempb[k3].value;
            button[j3].xcoordinate = tempb[k3].xcoordinate;
            button[j3].ycoordinate = tempb[k3].ycoordinate;
            button[j3].color = tempb[k3].color;
            repaint();
            j3++;
        }

        fdisplaybarray = false;
        draw_index = draw_index - 2;
        updateCanvas(true);
    }

    public void drawMerge(int i, int j, int k, int l)
    {
        Graphics g = getGraphics();
        g.setColor(Color.lightGray);
        tempb[l].ycoordinate = k - 5;
        repaint();
        for(int i1 = k + 2; i1 > k - 35; i1--)
        {
            g.copyArea(j + 5, i1, 30, 30, 0, -1);
            g.drawLine(j + 5, i1 + 30, j + 35, i1 + 30);
            if(i1 == k - 30)
            {
                g.setColor(Color.black);
                g.drawLine(j + 5, i1 + 30, j + 35, i1 + 30);
                g.setColor(Color.lightGray);
            }
            try
            {
                Thread.sleep(5L);
            }
            catch(InterruptedException _ex) { }
        }

        tempb[l].ycoordinate = k - 40;
        if(i > j)
        {
            for(int j1 = j + 5; j1 < i + 5; j1++)
            {
                g.copyArea(j1, k - 35, 30, 30, 1, 0);
                g.drawLine(j1, k - 35, j1, k - 5);
                try
                {
                    Thread.sleep(5L);
                }
                catch(InterruptedException _ex) { }
            }

            tempb[l].xcoordinate = i;
        } else
        {
            for(int k1 = j + 5; k1 > i + 5; k1--)
            {
                g.copyArea(k1, k - 35, 30, 30, -1, 0);
                g.drawLine(k1 + 30, k - 35, k1 + 30, k - 5);
                try
                {
                    Thread.sleep(5L);
                }
                catch(InterruptedException _ex) { }
            }

            tempb[l].xcoordinate = i;
        }
        for(int l1 = k - 35; l1 > k - 75; l1--)
        {
            g.copyArea(i + 5, l1, 30, 30, 0, -1);
            if(l1 == k - 70)
            {
                g.drawLine(i + 5, l1 + 30, i + 35, l1 + 30);
            }
            if(l1 != k - 70)
            {
                g.drawLine(i + 5, l1 + 30, i + 35, l1 + 30);
            }
            try
            {
                Thread.sleep(5L);
            }
            catch(InterruptedException _ex) { }
        }

        tempb[l].ycoordinate = k - 80;
        repaint();
    }

    public void drawMs(int i, int j, int k, int l)
    {
        Graphics g = getGraphics();
        g.setColor(Color.lightGray);
        int i1 = k - 80;
        button[l].ycoordinate = i1 + 5;
        repaint();
        for(int j1 = i1 + 6; j1 < i1 + 45; j1++)
        {
            g.copyArea(i + 5, j1, 30, 30, 0, 1);
            g.setColor(Color.lightGray);
            g.fillRect(i + 5, j1, 31, 1);
            if(j1 == i1 + 41)
            {
                g.setColor(Color.black);
                g.drawLine(i + 5, j1 - 1, i + 35, j1 - 1);
                g.setColor(Color.lightGray);
            }
            try
            {
                Thread.sleep(5L);
            }
            catch(InterruptedException _ex) { }
        }

        button[l].ycoordinate = i1 + 40;
       // System.out.println(" loc1 is  : " + j + " loc0 is " + i);
        if(j > i)
        {
            for(int k1 = i + 5; k1 < j + 5; k1++)
            {
                g.copyArea(k1, i1 + 45, 30, 30, 1, 0);
                g.drawLine(k1, i1 + 45, k1, i1 + 75);
                try
                {
                    Thread.sleep(5L);
                }
                catch(InterruptedException _ex) { }
            }

            button[l].xcoordinate = j;
        } else
        {
            for(int l1 = i + 5; l1 > j + 5; l1--)
            {
                g.copyArea(l1, i1 + 45, 30, 30, -1, 0);
                g.drawLine(l1 + 30, i1 + 45, l1 + 30, i1 + 75);
                try
                {
                    Thread.sleep(5L);
                }
                catch(InterruptedException _ex) { }
            }

            button[l].xcoordinate = j;
        }
        for(int i2 = i1 + 45; i2 < i1 + 85; i2++)
        {
            g.copyArea(j + 5, i2, 30, 30, 0, 1);
            g.drawLine(j + 5, i2, j + 35, i2);
            try
            {
                Thread.sleep(5L);
            }
            catch(InterruptedException _ex) { }
        }

        button[l].ycoordinate = i1 + 80;
        repaint();
    }

    public void displayColorTerminology(Graphics g)
    {
        Font font = new Font("Serief", 1, 20);
        Font font1 = new Font("Serief", 1, 15);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        Dimension dim = getSize();
        g.setColor(Color.black);
        g.drawRect(5, dim.height / 2 - 150, dim.width - 10, 250);
        g.fillRect(5, dim.height / 2 - 150, dim.width - 10, 250);
        g.setColor(Color.green);
        String s = "Colour Terminology";
        int i = fm.stringWidth(s);
        g.drawString(s, (dim.width / 2 - 150) + (300 - i) / 2, (dim.height / 2 - 150) + fm.getHeight() + 10);
        g.drawLine((dim.width / 2 - 150) + (300 - i) / 2, (dim.height / 2 - 150) + fm.getHeight() + 20, (dim.width / 2 - 150) + (300 - i) / 2 + i, (dim.height / 2 - 150) + fm.getHeight() + 20);
        g.setColor(Color.red);
        g.setFont(font1);
        g.fillRect(425, 225, 20, 20);
        g.drawString("--> unsorted subsequence", 450, 240);
        g.setColor(Color.blue);
        g.fillRect(425, 275, 20, 20);
        g.drawString("--> sorted subsequence", 450, 290);
        g.setColor(Color.cyan);
        g.fillRect(425, 325, 20, 20);
        g.setColor(Color.cyan);
        g.drawString("--> under consideration", 450, 340);
        g.setColor(Color.yellow);
        g.drawString("Press Continue Button To Continue", 425, 375);
    }

    public void draw(Graphics g, int i, int j, int k)
    {
        int l = i;
        int i1 = j;
        for(int j1 = 0; j1 < k; j1++)
        {
            g.setColor(Color.black);
            g.drawRect(l, i1, 40, 40);
            l += 40;
        }

    }

    public void updateCanvas(boolean flag)
    {
        msg.repaint();
        repaint();
        if(flag)
        {
            try
            {
                Thread.sleep(1000L);
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
}
