package GUI;

import BLL.ClientBLL;
import DAO.ClientDAO;
import Model.Client;

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
public class ClientFrame extends JFrame implements ItemListener {
    private JButton butonAdaugare = new JButton("Adauga client");
    private JButton butonEditare = new JButton("Editeaza client");
    private JButton butonStergere = new JButton("Sterge client");
    private JButton butonVizualizare = new JButton("Vizualizare clienti");
    private JButton butonCautare = new JButton("Cautare clienti");

    private JTextField idClient = new JTextField(30);
    private JTextField numeClient = new JTextField(30);
    private JTextField adresaClient = new JTextField(30);
    private JTextField emailClient = new JTextField(30);
    private JTextField varstaClient = new JTextField(30);
    private JComboBox comboBox;

    /**
     * Aici avem constructorul clasei care are rolul de a creea panel-r=urile si de le conecta
     */
    public ClientFrame() {

        this.adaugareClientListener(new AddClientListener());
        this.editareClientListener(new AddEditareListener());
        this.stergereClientListener(new AddStergereListener());
        this.vizualizareClientListener(new AddVizualizareListener());
        this.cautareClientListener(new AddCautareListener());

        String[] op = {"Adauga", "Editeaza", "Sterge","Gaseste"};
        comboBox = new JComboBox(op);
        comboBox.addItemListener(this);
        JPanel opPanel = new JPanel();
        opPanel.add(comboBox);

        JPanel titluPanel = new JPanel();
        JLabel titlu = new JLabel("Clienti");
        titluPanel.setBounds(40,80,200,200);
        titluPanel.setBackground(Color.gray);
        titluPanel.add(titlu);
        titluPanel.setLayout(new FlowLayout());



        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("ID: ");
        idPanel.setBounds(40,80,200,200);
        idPanel.setBackground(Color.gray);
        idPanel.add(idLabel);
        idPanel.add(idClient);
        idClient.setEditable(false);
        idPanel.setLayout(new FlowLayout());

        JPanel numePanel = new JPanel();
        JLabel numeLabel = new JLabel("Nume: ");
        numePanel.setBounds(40,80,200,200);
        numePanel.setBackground(Color.gray);
        numePanel.add(numeLabel);
        numePanel.add(numeClient);
        numePanel.setLayout(new FlowLayout());

        JPanel adresaPanel = new JPanel();
        JLabel adresaLabel = new JLabel("Adresa: ");
        adresaPanel.setBounds(40,80,200,200);
        adresaPanel.setBackground(Color.gray);
        adresaPanel.add(adresaLabel);
        adresaPanel.add(adresaClient);
        adresaPanel.setLayout(new FlowLayout());

        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Email: ");
        emailPanel.setBounds(40,80,200,200);
        emailPanel.setBackground(Color.gray);
        emailPanel.add(emailLabel);
        emailPanel.add(emailClient);
        emailPanel.setLayout(new FlowLayout());

        JPanel varstaPanel = new JPanel();
        JLabel varstaLabel = new JLabel("Varsta: ");
        varstaPanel.setBounds(40,80,200,200);
        varstaPanel.setBackground(Color.gray);
        varstaPanel.add(varstaLabel);
        varstaPanel.add(varstaClient);
        varstaPanel.setLayout(new FlowLayout());

        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(40,80,200,200);
        btnPanel.setBackground(Color.gray);
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
        btnPanel.setBounds(40,80,200,200);
        btnPanel.setBackground(Color.orange);
        contentPanel.add(numePanel);
        contentPanel.add(adresaPanel);
        contentPanel.add(titluPanel);
        contentPanel.add(opPanel);
        contentPanel.add(emailPanel);
        contentPanel.add(varstaPanel);
        contentPanel.add(idPanel);
        contentPanel.add(btnPanel);

        this.setTitle("Clienti");
        this.setContentPane(contentPanel);
        this.setSize(750, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            if (Objects.equals(comboBox.getSelectedItem(), "Adaugare")) {
                idClient.setEditable(false);
                numeClient.setEditable(true);
                adresaClient.setEditable(true);
                emailClient.setEditable(true);
                varstaClient.setEditable(true);
            } else {
                if(Objects.equals(comboBox.getSelectedItem(), "Sterge")) {
                    idClient.setEditable(true);
                    numeClient.setEditable(false);
                    adresaClient.setEditable(false);
                    emailClient.setEditable(false);
                    varstaClient.setEditable(false);
                }else{
                    if(Objects.equals(comboBox.getSelectedItem(), "Editeaza")) {
                        idClient.setEditable(true);
                        numeClient.setEditable(true);
                        adresaClient.setEditable(true);
                        emailClient.setEditable(true);
                        varstaClient.setEditable(true);
                    }else{
                        idClient.setEditable(true);
                        numeClient.setEditable(false);
                        adresaClient.setEditable(false);
                        emailClient.setEditable(false);
                        varstaClient.setEditable(false);
                    }
                }
            }
        }
        idClient.setText("");
        numeClient.setText("");
        adresaClient.setText("");
        emailClient.setText("");
        varstaClient.setText("");
    }


    void adaugareClientListener(ActionListener aal) { butonAdaugare.addActionListener(aal); }
    void editareClientListener(ActionListener aul) { butonEditare.addActionListener(aul);}

    void stergereClientListener(ActionListener adl) { butonStergere.addActionListener(adl);}

    void vizualizareClientListener(ActionListener avl) { butonVizualizare.addActionListener(avl);}
    void cautareClientListener(ActionListener afl) { butonCautare.addActionListener(afl);}

    void afisareEroare(String errorMessage) { JOptionPane.showMessageDialog(this,errorMessage); }
    void Success(String message) { JOptionPane.showMessageDialog(this,message); }

    /**
     * are rolul de adauga un nou client in baza de date dupa apasarea butonului Adaugare
     * Se realizeaza doar daca in combo box este selectat butonul de "Adaugare"
     */
    class AddClientListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Adauga"))
            {
                try {
                    String nume = numeClient.getText();
                    String adresa = adresaClient.getText();
                    String email = emailClient.getText();
                    String varsta = varstaClient.getText();
                    int varsta1 = Integer.parseInt(varsta);
                    Client client = new Client(0, nume, adresa, email, varsta1);
                    ClientBLL clientbll=new ClientBLL();
                    clientbll.inserareClient(client);
                    Success("Client inserat cu succes!");
                } catch (NullPointerException | NumberFormatException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }

    /**
     * are rolul de edita datele unui client dupa apasarea butonului Editare
     * Se realizeaza doar daca in combo box este selctat "Editare"
     */
    class AddEditareListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Editeaza")) {
                try {
                    String nume = numeClient.getText();
                    String adresa = adresaClient.getText();
                    String email = emailClient.getText();
                    String varsta = varstaClient.getText();
                    String id = idClient.getText();
                    int id1 = Integer.parseInt(id);
                    int varsta1= Integer.parseInt(varsta);
                    Client client = new Client(id1, nume, adresa, email, varsta1);
                    ClientBLL clientbll= new ClientBLL();
                    clientbll.editareClient(client, id1);
                    Success("Client editat cu succes!");
                } catch (NullPointerException | NumberFormatException | NoSuchElementException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }

    /**
     * are rolul de a sterge un client dupa apasarea butonlui Stergere
     * Se realizeaza doar daca in combo box este selectata optiunea "Stergere"
     */
    class AddStergereListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Sterge")) {
                try {
                    String id = idClient.getText();
                    int id1 = Integer.parseInt(id);
                    ClientBLL clientbll = new ClientBLL();
                    clientbll.stergereClient(id1);
                    Success("Client sters cu succes!");
                }catch(NullPointerException | NumberFormatException | NoSuchElementException ex)
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
        public void actionPerformed(ActionEvent e)
        {
            JFrame jFrame=new JFrame();
            ClientBLL clientbll=new ClientBLL();
            JTable table;
            try {
                table = clientbll.creeazaTabel(clientbll.vizualizareClienti());
                jFrame.setTitle("Baza de date clienti");
                JPanel panel=new JPanel();
                JScrollPane jScrollPane=new JScrollPane(table);
                panel.add(jScrollPane);
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
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(comboBox.getSelectedItem(), "Gaseste")) {
                JFrame jFrame = new JFrame();
                ClientBLL clientbll = new ClientBLL();
                JTable table =null;
                try {
                    String id = idClient.getText();
                    int id1 = Integer.parseInt(id);
                    table = clientbll.creeazaTabel(clientbll.gasesteClientDupaId(id1));
                    jFrame.setTitle("Baza de date clienti");
                    JPanel panel = new JPanel();
                    JScrollPane scrollPane = new JScrollPane(table);
                    panel.add(scrollPane);
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

}
