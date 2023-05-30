package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Realizeaza conexiunea intre interfata principala si butoane
 */
public class Controller {
    private MainFrame frame;

    public Controller(MainFrame frame)
    {
        this.frame=frame;
        frame.addClientListener(new ClientListener());
        frame.addProdusListener(new ProdusListener());
        frame.addComandaListener(new ComandaListener());
    }

    /**
     * Creeaza fereastra pentru clienti dupa apasarea butonului
     */
    class ClientListener implements ActionListener {
        public void actionPerformed(ActionEvent event)
        {
            ClientFrame frame=new ClientFrame();
            frame.setVisible(true);
        }
    }

    /**
     * Creeaza fereastra pentru produse dupa apasarea butonului
     */
    class ProdusListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            ProductFrame frame=new ProductFrame();
            frame.setVisible(true);
        }
    }

    /**
     * Creeaza fereastra pentru comenzi dupa apasarea butonului
     */
    class ComandaListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            OrderFrame frame=new OrderFrame();
            frame.setVisible(true);
        }
    }
}
