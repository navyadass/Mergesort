import java.awt.*;

class messageCanvas extends Canvas
{

    Font f1,f2;
    String str;
    Graphics gr;
    Image img;
    boolean start;
    Dimension d;

    public messageCanvas()
    {
        str = "";
        start = true;
        setSize(1025, 100);
        setVisible(true);
        setBackground(Color.lightGray);
        f1 = new Font("Serief", 1, 25);
        f2 = new Font("Serief", 2, 20);
        repaint();
    }

    public void setMessage(String s)
    {
        str = s;
    }

    public void paint(Graphics g)
    {
        update(g);
    }

    public void update(Graphics g)
    {
        if(start)
        {
            start= false;
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
        gr.setColor(Color.black);
        gr.fillRect(0, 0, d.width, d.height);
        Dimension dim = getSize();
        gr.setColor(Color.black);
        gr.setFont(f1);
        FontMetrics fm = g.getFontMetrics();
        gr.drawRoundRect(5, 5, dim.width - 5, dim.height - 5, 10, 10);
        gr.setColor(Color.white);
        gr.fillRoundRect(5, 5, dim.width - 9, dim.height - 9, 10, 10);
        int i = fm.stringWidth("MESSAGE");
        gr.setColor(Color.black);
        gr.drawRoundRect(dim.width / 2 - i, 10, 2 * i + 55, 40, 5, 5);
        gr.setColor(Color.lightGray);
        gr.fillRoundRect((dim.width / 2 - i) + 2, 12, 2 * i + 51, 36, 5, 5);
        gr.setColor(Color.black);
        gr.drawString("MESSAGE", (dim.width - i) / 2, 40);
        gr.setFont(f2);
        fm = gr.getFontMetrics();
        i = fm.stringWidth(str);
        gr.setColor(Color.blue);
        gr.drawString(str, (dim.width - i) / 2, 80);
        g.drawImage(img, 0, 0, this);
    }
}
