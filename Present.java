public class Present {
    Model m;
    View v;
    boolean loop;

    public Present()
    {
        m = new Model();
        v = new View();
        loop = true;
    }

    public void Run()
    {
        while (loop)
        {
            try {
                m.Read(v.GetString("Введите чебурека:"));
                
            } catch (Exception e) {
                System.out.println(e);
            }
            loop = v.GetConfirm();
        }

    }
}