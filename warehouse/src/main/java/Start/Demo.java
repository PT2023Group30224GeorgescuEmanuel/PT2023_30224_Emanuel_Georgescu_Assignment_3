package Start;

import GUI.Controller;
import GUI.MainFrame;

public class Demo {
    public static void main(String args[])
    {
        MainFrame frame=new MainFrame();
        Controller controller=new Controller(frame);
        frame.setVisible(true);
    }
}
