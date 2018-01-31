class condition
{

    public synchronized void WAIT()
    {
        try
        {
            wait();
            return;
        }
        catch(InterruptedException _ex)
        {
            return;
        }
    }

    public synchronized void NOTIFY()
    {
        notify();
    }

    condition()
    {
    }
}
