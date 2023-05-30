package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Aceasta clasa reprezinta fereastra principala a aplicatiei.
 * Are ca si atribute 3 butoane, pentru clienti, produse si comenzi.
 */

public class MainFrame extends JFrame {
    private JButton butonClient=new JButton("Client");
    private JButton butonProdus=new JButton("Produs");
    private JButton butonComanda=new JButton("Comenzi");

    /**
     * Constructorul ferestrei instatntiaza atributele ferestrei si o face vizibila
     */
    public MainFrame()
    {
        JPanel titluPanel=new JPanel();
        JLabel titlu=new JLabel("Ecran principal");
        titluPanel.setBounds(40,80,200,200);
        titluPanel.setBackground(Color.gray);
        titluPanel.add(titlu);
        titlu.setLayout(new FlowLayout());

        JPanel panelBtn=new JPanel();
        panelBtn.add(butonClient);
        butonClient.setBounds(100,100,80,30);
        butonClient.setBackground(Color.green);
        panelBtn.add(butonProdus);
        butonProdus.setBounds(100,100,80,30);
        butonProdus.setBackground(Color.green);
        panelBtn.add(butonComanda);
        butonComanda.setBounds(100,100,80,30);
        butonComanda.setBackground(Color.green);
        panelBtn.setLayout(new FlowLayout());

        JPanel rezPanel=new JPanel();
        rezPanel.add(titluPanel);
        rezPanel.add(panelBtn);
        rezPanel.setLayout(new BoxLayout(rezPanel,BoxLayout.Y_AXIS));

        this.setContentPane(rezPanel);
        this.setSize(700,400);
        this.setTitle("Main Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Se asigura interactiunea utilizatorului cu butonul de clienti
     */
    void addClientListener(ActionListener client) { butonClient.addActionListener(client); }

    /**
     * Se asigura interactiunea utilizatorului cu butonul de produse
     */
    void addProdusListener(ActionListener produs) { butonProdus.addActionListener(produs); }

    /**
     * Se asigura interactiunea utilizatorului cu butonul de comenzi
     */
    void addComandaListener(ActionListener comanda) { butonComanda.addActionListener(comanda); }
}
