package GUI;

import BLL.ProductBLL;
import Model.Product;

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

public class ProductFrame extends JFrame implements ItemListener {

    private JButton butonAdaugare = new JButton("Adaugare produs");
    private JButton butonEditare= new JButton("Editare produs");
    private JButton butonStergere = new JButton("Stergere produs");
    private JButton butonVizualizare= new JButton("Vizualizare produs");
    private JButton butonCautare = new JButton("Cautare produs");

    private JTextField idProdus = new JTextField(30);
    private JTextField numeProdus = new JTextField(30);
    private JTextField cantitateProdus = new JTextField(30);
    private JTextField pretProdus = new JTextField(30);

    private JComboBox comboBox;

    /**
     * Aici avem constructorul clasei care are rolul de a creea panel-r=urile si de le conecta
     */
    public ProductFrame() {

        this.addProdusListener(new AddProductListener());
        this.editareListener(new AddEditareListener());
        this.vizualizareListener(new AddVizualizareListener());
        this.stergereListener(new AddStergereListener());
        this.cautareListener(new AddCautareListener());



        String[] operations = {"Adaugare", "Editare", "Stergere","Cautare"};
        comboBox = new JComboBox(operations);
        comboBox.addItemListener(this);
        JPanel operationsPanel = new JPanel();
        operationsPanel.add(comboBox);
        JPanel titluPanel = new JPanel();
        JLabel titlu = new JLabel("Produse");
        //titluPanel.setBounds(40,80,200,200);
        titluPanel.setBackground(Color.gray);
        titluPanel.add(titlu);
        titluPanel.setLayout(new FlowLayout());
        JPanel idPanel = new JPanel();
        JLabel idLabel = new JLabel("ID: ");
        //idPanel.setBounds(40,80,200,200);
        idPanel.setBackground(Color.gray);
        idPanel.add(idLabel);
        idPanel.add(idProdus);
        idProdus.setEditable(false);
        idPanel.setLayout(new FlowLayout());

        JPanel numePanel = new JPanel();
        JLabel numeLabel = new JLabel("Nume: ");
        //numePanel.setBounds(40,80,200,200);
        numePanel.setBackground(Color.gray);
        numePanel.add(numeLabel);
        numePanel.add(numeProdus);
        numePanel.setLayout(new FlowLayout());

        JPanel cantitatePanel = new JPanel();
        JLabel cantitateLabel = new JLabel("Cantitate: ");
        //cantitatePanel.setBounds(40,80,200,200);
        cantitatePanel.setBackground(Color.gray);
        cantitatePanel.add(cantitateLabel);
        cantitatePanel.add(cantitateProdus);
        cantitatePanel.setLayout(new FlowLayout());

        JPanel pretPanel = new JPanel();
        JLabel pretLabel = new JLabel("Pret: ");
        //pretPanel.setBounds(40,80,200,200);
        pretPanel.setBackground(Color.gray);
        pretPanel.add(pretLabel);
        pretPanel.add(pretProdus);
        pretPanel.setLayout(new FlowLayout());

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
        contentPanel.add(numePanel);
        contentPanel.add(cantitatePanel);
        contentPanel.add(pretPanel);
        contentPanel.add(btnPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        this.setTitle("Produs");
        this.setContentPane(contentPanel);
        this.setSize(750, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboBox) {
            if (Objects.equals(comboBox.getSelectedItem(), "Adaugare")) {
                idProdus.setEditable(false);
                numeProdus.setEditable(true);
                cantitateProdus.setEditable(true);
                pretProdus.setEditable(true);
            } else {
                if (Objects.equals(comboBox.getSelectedItem(), "Stergere")) {
                    idProdus.setEditable(true);
                    numeProdus.setEditable(false);
                    cantitateProdus.setEditable(false);
                    pretProdus.setEditable(false);
                } else {
                    if(Objects.equals(comboBox.getSelectedItem(), "Editare")) {
                        idProdus.setEditable(true);
                        numeProdus.setEditable(true);
                        cantitateProdus.setEditable(true);
                        pretProdus.setEditable(true);
                    }else{
                        idProdus.setEditable(true);
                        numeProdus.setEditable(false);
                        cantitateProdus.setEditable(false);
                        pretProdus.setEditable(false);
                    }
                }
            }
        }
        idProdus.setText("");
        numeProdus.setText("");
        cantitateProdus.setText("");
        pretProdus.setText("");
    }

    void addProdusListener(ActionListener aal) { butonAdaugare.addActionListener(aal); }
    void editareListener(ActionListener aul) { butonEditare.addActionListener(aul);}
    void stergereListener(ActionListener adl) { butonStergere.addActionListener(adl);}
    void vizualizareListener(ActionListener avl) { butonVizualizare.addActionListener(avl);}
    void cautareListener(ActionListener afl) { butonCautare.addActionListener(afl);}
    void afisareEroare(String errorMessage) { JOptionPane.showMessageDialog(this,errorMessage); }
    void Success(String message) { JOptionPane.showMessageDialog(this,message); }

    /**
     * are rolul de adauga un nou produs in baza de date dupa apasarea butonului Adaugare
     * Se realizeaza doar daca in combo box este selectat butonul de "Adaugare"
     */
    class AddProductListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Adaugare")) {
                try {
                    String nume = numeProdus.getText();
                    String cantitate = cantitateProdus.getText();
                    String pret = pretProdus.getText();
                    int cantitate1 = Integer.parseInt(cantitate);
                    float pret1 = Float.parseFloat(pret);
                    Product produs = new Product(0, nume, cantitate1,pret1);
                    ProductBLL produsbll=new ProductBLL();
                    produsbll.inserareProduse(produs);
                    Success("Produs inserat cu succes!");
                } catch (NullPointerException | NumberFormatException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }

    /**
     * are rolul de edita datele unui produs dupa apasarea butonului Editare
     * Se realizeaza doar daca in combo box este selctat "Editare"
     */
    class AddEditareListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Editare")) {
                try {
                    String nume = numeProdus.getText();
                    String cantitate = cantitateProdus.getText();
                    String pret= pretProdus.getText();
                    String id = idProdus.getText();
                    int id1=Integer.parseInt(id);
                    int cantitate1 = Integer.parseInt(cantitate);
                    float pret1 = Float.parseFloat(pret);
                    Product produs = new Product(id1, nume, cantitate1,pret1);
                    ProductBLL produsbll = new ProductBLL();
                    produsbll.editareProduse(produs, id1);
                    Success("Produs editat cu succes!");
                } catch (NullPointerException | NumberFormatException | NoSuchElementException ex)
                {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }
    /**
     * are rolul de a sterge un produs dupa apasarea butonlui Stergere
     * Se realizeaza doar daca in combo box este selectata optiunea "Stergere"
     */
    class AddStergereListener implements ActionListener{
        public void actionPerformed(ActionEvent event)
        {
            if(Objects.equals(comboBox.getSelectedItem(), "Stergere")) {
                try {
                    String id = idProdus.getText();
                    int id1 = Integer.parseInt(id);
                    ProductBLL produsbll = new ProductBLL();
                    produsbll.stergereProduse(id1);
                    Success("Produs sters cu succes!");
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
        public void actionPerformed(ActionEvent event)
        {
            JFrame jFrame=new JFrame();
            ProductBLL produsbll=new ProductBLL();
            JTable table= null;
            try {
                table = produsbll.creeazaTabel(produsbll.vizualizareProduse());
                jFrame.setTitle("Baza de date de produse");
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
                ProductBLL productbll = new ProductBLL();
                JTable table = null;
                try {
                    String id = idProdus.getText();
                    int id1 = Integer.parseInt(id);
                    table = productbll.creeazaTabel(productbll.gasesteProdusulDupaID(id1));
                    jFrame.setTitle("Baza de date de produse");
                    JPanel panel = new JPanel();
                    JScrollPane scrollPanel = new JScrollPane(table);
                    panel.add(scrollPanel);
                    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                    jFrame.setContentPane(panel);
                    jFrame.setVisible(true);
                    jFrame.setSize(800, 400);
                    jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (NullPointerException | NumberFormatException | IllegalAccessException |
                         NoSuchElementException ex) {
                    afisareEroare(ex.getMessage());
                }
            }
        }
    }
}
