package BLL;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa reprezinta logica programului pentru Orders
 */
public class OrderBLL {
    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    public OrderBLL() {
        this.orderDAO=new OrderDAO();
        this.clientDAO=new ClientDAO();
        this.productDAO=new ProductDAO();
    }

    /**
     * Aici se returneaza lista de comenzi cu id-ul dat ca si parametru dupa ce se apeleaza interogarile din baza date
     * @param id este id-ul dupa care se va realiza  selectia
     * @return se da return listei de comenzi cu id-ul cautat
     * @throws NoSuchElementException se arunca o exceptie in cazul in care nu se gasesc comenzi cu id-ul cautat
     */

    public List<Orders> gasesteComandaDupaID(int id) throws NoSuchElementException {
        List<Orders> comenzi = orderDAO.findID(id);
        if (comenzi.isEmpty()) {
            throw new NoSuchElementException("Nu a fost gasit produsul cu id-ul"+id);
        }
        return comenzi;
    }

    /**
     * Se returneaza lista de comenzi care se afla in baza de date,dupa ce se apeleaza interogarile din baza de date
     * @return se returneaza lista de comenzi care se afla in baza de date
     */
    public List<Orders> vizualizareComenzi()
    {
        return orderDAO.selectAll();
    }

    /**
     * Aceasta metoda are rolul de insera in baza de date o comanda care este data ca si parametru
     * @param comanda este comanda ce se doreste a fi inserata
     */

    public void inserareComenzi(Orders comanda) throws NoSuchElementException{
        int clientID=comanda.getClient();
        int produsID=comanda.getProduct();
        List<Client> clienti = clientDAO.findID(clientID);
        if(clienti.isEmpty()){
            throw new NoSuchElementException("Nu a fost gasit clientul cu id-ul:"+clientID);
        }
        List<Product> produse = productDAO.findID(produsID);
        if(produse.isEmpty()){
            throw new NoSuchElementException("Nu a fost gasit produsul cu id-ul:"+produsID);
        }

        int cantitateCurenta=produse.get(0).getQuantity();
        if(cantitateCurenta>=comanda.getQuantity()){
            float pretProdus = produse.get(0).getPrice();
            float pretTotal=pretProdus*comanda.getQuantity();
            comanda.setTotal(pretTotal);
            Product produsEditat=new Product(produse.get(0).getId(),produse.get(0).getName(),cantitateCurenta-comanda.getQuantity(),produse.get(0).getPrice());
            productDAO.update(produsEditat,produsID);
            orderDAO.insert(comanda);
        }else{
            throw new NoSuchElementException("Nu este destul stoc pentru produsul cu id-ul:" + produsID);
        }
    }

    /**
     * Aceasta metoda are rolul de actualiza baza de date,respectiv o comanda din baza de date in functie de un id dat ca si parametru
     * @param comanda este comanda in functie de care va fi actualizata comanda ce se doreste a fi actualizata
     * @param id este id-ul comenzii care urmeaza a fi actualizata
     */
    public void editareComenzi(Orders comanda, int id)
    {
        List<Orders> comenzi = orderDAO.findID(id);
        if(comenzi.isEmpty())
            throw new NoSuchElementException("Nu a fost gasita comanda cu id-ul:"+id);
        int clientID = comanda.getClient();
        int productID = comanda.getProduct();
        int cantitate = comanda.getQuantity();
        List<Client> clienti = clientDAO.findID(clientID);
        if(clienti.isEmpty())
            throw new NoSuchElementException("Nu a fost gasit clientul cu id-ul:"+clientID);
        List<Product> produse = productDAO.findID(productID);
        if(produse.isEmpty())
            throw new NoSuchElementException("Nu a fost gasit produsul cu id-ul:"+productID);
        float total = produse.get(0).getPrice() * cantitate;
        comanda.setTotal(total);
        orderDAO.update(comanda,id);
    }

    /**
     * Aceasta metoda are rolul de a sterge o comanda din baza de date,in functie de un id dat ca si parametru
     * @param id este id-ul comenzii care se doreste a fi stearsa
     */
    public void stergeComanda(int id)
    {
        List<Orders> comenzi = orderDAO.findID(id);
        if(comenzi.isEmpty()){
            throw new NoSuchElementException("Nu a fost gasita comanda cu id-ul:"+id);
        }
        orderDAO.delete(id);
    }
    /**
     *Metoda are rolul de a crea un tabel in functie de lista de comenzi
     */
    public JTable creeazaTabel(List<Orders> list) throws IllegalAccessException {
        return orderDAO.createTable(list);
    }

}