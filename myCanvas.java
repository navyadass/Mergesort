import java.awt.*;

class myCanvas extends Canvas
{

    Font f1;
    String str;

    public myCanvas()
    {
        str = " MERGE  SORT ";
        setSize(1025, 65);
        setVisible(true);
        setBackground(Color.lightGray);
        f1 = new Font("Serief", 1, 45);
        repaint();
    }

    public void paint(Graphics g)
    {
        g.setFont(f1);
        FontMetrics fm = g.getFontMetrics();
        Dimension dim = getSize();
        g.setColor(Color.black);
        g.drawRect(1, 1, dim.width - 2, dim.height - 2);
        g.fillRect(5, 5, dim.width - 10, dim.height - 10);
        g.setColor(Color.white);
        int i = fm.stringWidth(str);
        g.drawString(str, (dim.width - i) / 2, dim.height - 15);
    }
}
