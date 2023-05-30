
package GUI;

import BLL.OrderBLL;
import Model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Aceata clasa reprezinta fereastra din interfata grafica pentru clienti
 * Aceasta are ca atribute 5 butoane pentru interactiunea cu utilizatorul , 5 textfield-uri pentru datele introduse de utilizator
 * Are si un combo box pentru selectia operatiei dorite
 */
public class OrderFrame extends JFrame implements ItemListener{
    private JButton butonAdaugare = new JButton("Adauga comanada");
    private JButton butonEditare= new JButton("Editeaza comanda");
    private JButton butonStergere = new JButton("Sterge comanda");
    private JButton butonVizualizare = new JButton("Vizualizare comenzi");
    private JButton butonCautare = new JButton("Cauta comanda");

    private JTextField id = new JTextField(15);
    private JTextField client = new JTextField(15);
    private JTextField produs = new JTextField(15);
    private JTextField cantitate = new JTextField(15);
    private JComboBox comboBox;

    /**
     * Aici avem constructorul clasei care are rolul de a creea panel-r=urile si de le conecta
     */
    public OrderFrame() {

        this.addComandaListener(new AddComandaListener());
        this.vizualizareListener(new AddVizualizareListener());
        this.cautareListener(new AddCautareListener());
        this.editareListener(new AddEditareListener());
        this.stergereListener(new AddStergereListener());

        JPanel titluPanel = new JPanel();
        JLabel titlu = new JLabel("Comenzi");
        titluPanel.setBounds(40,80,200,200);
        titluPanel.setBackground(Color.gray);
        titluPanel.add(titlu);
        titluPanel.setLayout(new FlowLayout());

        String[] op= {"Adaugare", "Editare", "Stergere", "Cautare"};
        comboBox = new JComboBox(op);
        comboBox.addItemListener(this);
        JPanel operationsPanel = new JPanel();
        operationsPanel.add(comboBox);

        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("ID: ");
        idPanel.setBounds(40,80,200,200);
        idPanel.setBackground(Color.gray);
        idPanel.add(idLabel);
        idPanel.add(id);
        id.setEditable(false);
        idPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        JLabel clientLabel = new JLabel("Client: ");
        clientPanel.setBounds(40,80,200,200);
        clientPanel.setBackground(Color.gray);
        clientPanel.add(clientLabel);
        clientPanel.add(client);
        clientPanel.setLayout(new FlowLayout());

        JPanel produsPanel = new JPanel();
        JLabel produsLabel = new JLabel("Produs: ");
        produsPanel.setBounds(40,80,200,200);
        produsPanel.setBackground(Color.gray);
        produsPanel.add(produsLabel);
        produsPanel.add(produs);
        produsPanel.setLayout(new FlowLayout());

        JPanel cantitatePanel = new JPanel();
        JLabel cantitateLabel = new JLabel("Cantitate: ");
        cantitatePanel.setBounds(40,80,200,200);
        cantitatePanel.setBackground(Color.gray);
        cantitatePanel.add(cantitateLabel);
        cantitatePanel.add(cantitate);
        cantitatePanel.setLayout(new FlowLayout());

        JPanel btnPanel = new JPanel();
        btnPanel.add(butonAdaugare);
        butonAdaugare.setBounds(100,100,80,30);
        butonAdaugare.setBackground(Color.green);
        btnPanel.add(butonEditare);
        butonEditare.setBounds(100,100,80,30);
        butonEditare.setBackground(Color.green);
        btnPanel.add(butonStergere);
        butonStergere.setBounds(100,100,80,30);
        butonStergere.setBackground(Color.green);
        btnPanel.add(butonCautare);
        butonCautare.setBounds(100,100,80,30);
        butonCautare.setBackground(Color.green);
        btnPanel.add(butonVizualizare);
        butonVizualizare.setBounds(100,100,80,30);
        butonVizualizare.setBackground(Color.green);
        btnPanel.setLayout(new FlowLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.add(titluPanel);
        contentPanel.add(operationsPanel);
        contentPanel.add(idPanel);
        contentPanel.add(clientPanel);
        contentPanel.add(produsPanel);
        contentPanel.add(cantitatePanel);
        contentPanel.add(btnPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        this.setTitle("Comenzi");
        this.setContentPane(contentPanel);
        this.setSize(600, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



    public void itemStateChanged(ItemEvent event) {
        if (event.getSource() == comboBox) {
            if (Objects.equals(comboBox.getSelectedItem(), "Adaugare")) {
                id.setEditable(false);
                client.setEditable(true);
                produs.setEditable(true);
                cantitate.setEditable(true);
            } else {
                if(Objects.equals(comboBox.getSelectedItem(), "Stergere")) {
                    id.setEditable(true);
                    client.setEditable(false);
                    produs.setEditable(false);
                    cantitate.setEditable(false);
                }else{
                    if(Objects.equals(comboBox.getSelectedItem(), "Editare")) {
                        id.setEditable(true);
                        client.setEditable(true);
                        produs.setEditable(true);
                        cantitate.setEditable(true);
                    }else{
                        id.setEditable(true);
                        client.setEditable(false);
                        produs.setEditable(false);
                        cantitate.setEditable(false);
                    }
                }
            }
        }
        id.setText("");
        client.setText("");
        produs.setText("");
        cantitate.setText("");
    }

    void addComandaListener(ActionListener aal) { butonAdaugare.addActionListener(aal); }
    void editareListener(ActionListener aul) { butonEditare.addActionListener(aul);}

    void stergereListener(ActionListener adl) { butonStergere.addActionListener(adl);}

    void vizualizareListener(ActionListener avl) { butonVizualizare.addActionListener(avl);}
    void cautareListener(ActionListener afl) { butonCautare.addActionListener(afl);}

    void afisareEroare(String errorMessage) { JOptionPane.showMessageDialog(this,errorMessage); }
    void Success(String message) { JOptionPane.showMessageDialog(this,message); }
    /**
     * are rolul de adauga o noua comanda in baza de date dupa apasarea butonului Adaugare
     * Se realizeaza doar daca in combo box este selectat butonul de "Adaugare"
     */
    class AddComandaListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Adaugare")) {
                try {
                    String client1 = client.getText();
                    String produs1 = produs.getText();
                    String cantitate1 = cantitate.getText();
                    int client2 = Integer.parseInt(client1);
                    int produs2 = Integer.parseInt(produs1);
                    int cantitate2 = Integer.parseInt(cantitate1);
                    Orders comanda = new Orders(0,client2,produs2,cantitate2,0);
                    OrderBLL comandabll=new OrderBLL();
                    comandabll.inserareComenzi(comanda);
                    Success("Comanda inserata cu succes!");
                } catch (NullPointerException | NumberFormatException | NoSuchElementException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }
    /**
     * Are rolul de deschide o noua ferestra in care fi afisata baza de date dupa apasarea butonului Vizualizare
     */
    class AddVizualizareListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            JFrame jFrame=new JFrame();
            OrderBLL comandabll=new OrderBLL();
            JTable table= null;
            try {
                table = comandabll.creeazaTabel(comandabll.vizualizareComenzi());
                jFrame.setTitle("Baza de date de comenzi");
                JPanel panel=new JPanel();
                JScrollPane scrollPanel=new JScrollPane(table);
                panel.add(scrollPanel);
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                jFrame.setContentPane(panel);
                jFrame.setVisible(true);
                jFrame.setSize(800, 400);
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    /**
     * ARe rolul de a deschide o noua fereastra in care vor fi afisate datele unuinclient cu un id
     * Se realizeaza doar daca in combo box este selectata "Cautare"
     */
    class AddCautareListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if (Objects.equals(comboBox.getSelectedItem(), "Cautare")) {
                JFrame jFrame = new JFrame();
                OrderBLL comandabll= new OrderBLL();
                JTable table = null;
                try {
                    String id1 = id.getText();
                    int idConv = Integer.parseInt(id1);
                    table = comandabll.creeazaTabel(comandabll.gasesteComandaDupaID(idConv));
                    jFrame.setTitle("Orders database");
                    JPanel panel = new JPanel();
                    JScrollPane scrollPanel = new JScrollPane(table);
                    panel.add(scrollPanel);
                    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                    jFrame.setContentPane(panel);
                    jFrame.setVisible(true);
                    jFrame.setSize(800, 400);
                    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (NullPointerException | NumberFormatException | IllegalAccessException | NoSuchElementException ex) {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }

    /**
     * are rolul de edita datele unei comenzi dupa apasarea butonului Editare
     * Se realizeaza doar daca in combo box este selctat "Editare"
     */
    class AddEditareListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Editare")) {
                try {
                    String client1 = client.getText();
                    String produs1 = produs.getText();
                    String cantitate1= cantitate.getText();
                    String id1 = id.getText();
                    int id2 = Integer.parseInt(id1);
                    int client2= Integer.parseInt(client1);
                    int produs2 = Integer.parseInt(produs1);
                    int cantitate2 = Integer.parseInt(cantitate1);
                    Orders comanda = new Orders(id2, client2, produs2, cantitate2, 0);
                    OrderBLL comandabll = new OrderBLL();
                    comandabll.editareComenzi(comanda, id2);
                    Success("Comanda editata cu succes!");
                } catch (NullPointerException | NumberFormatException | NoSuchElementException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }
    /**
     * are rolul de a sterge o comanda dupa apasarea butonlui Stergere
     * Se realizeaza doar daca in combo box este selectata optiunea "Stergere"
     */
    class AddStergereListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Stergere")) {
                try {
                    String id1 = id.getText();
                    int id2 = Integer.parseInt(id1);
                    OrderBLL comandabll = new OrderBLL();
                    comandabll.stergeComanda(id2);
                    Success("Comanda stearsa cu succes!");
                }catch(NullPointerException | NumberFormatException | NoSuchElementException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }

}
