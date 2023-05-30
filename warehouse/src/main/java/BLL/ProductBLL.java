package BLL;

import DAO.ProductDAO;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa reprezinta logica programului pentru Product
 */

public class ProductBLL {
    private ProductDAO produs;
    public ProductBLL()
    {
        this.produs=new ProductDAO();
    }
    /**
     * Aici se returneaza lista de prooduse cu id-ul dat ca si parametru dupa ce se apeleaza interogarile din baza date
     * @param id este id-ul dupa care se va realiza  selectia
     * @return se da return listei de produse cu id-ul cautat
     * @throws NoSuchElementException se arunca o exceptie in cazul in care nu se gasesc produse cu id-ul cautat
     */
    public List<Product> gasesteProdusulDupaID(int id) {
        List<Product> produse = produs.findID(id);
        if (produse.isEmpty()) {
            throw new NoSuchElementException("Nu am gasit produsul cu id-ul:"+id);
        }
        return produse;
    }

    /**
     * Se returneaza lista de produsecare se afla in baza de date,dupa ce se apeleaza interogarile din baza de date
     * @return se returneaza lista de produse care se afla in baza de date
     */
    public List<Product> vizualizareProduse()
    {
        return produs.selectAll();
    }

    /**
     * Aceasta metoda are rolul de insera in baza de date un produs care este dat ca si parametru
     * @param product este produsul ce se doreste a fi inserat
     */

    public void inserareProduse(Product product) {
        produs.insert(product);
    }

    /**
     * Aceasta metoda are rolul de actualiza baza de date,respectiv un produs din baza de date in functie de un id dat ca si parametru
     * @param product este produsul in functie de care va fi actualizat produsul ce se doreste a fi actualizata
     * @param id este id-ul produsului care urmeaza a fi actualizat
     */
    public void editareProduse(Product product,int id)
    {
        List<Product> produse = produs.findID(id);
        if (produse.isEmpty()) {
            throw new NoSuchElementException("Nu am gasit produsul cu id-ul:"+id);
        }
        produs.update(product,id);
    }

    /**
     * Aceasta metoda are rolul de a sterge un produs din baza de date,in functie de un id dat ca si parametru
     * @param id este id-ul produsului care se doreste a fi sters
     */

    public void stergereProduse(int id)
    {
        List<Product> produse = produs.findID(id);
        if (produse.isEmpty()) {
            throw new NoSuchElementException("Nu am gasit produsul cu id-ul:"+id);
        }
        produs.delete(id);
    }
    /**
     *Metoda are rolul de a crea un tabel in functie de lista de produse
     */
    public JTable creeazaTabel(List<Product> list) throws IllegalAccessException {
        return produs.createTable(list);
    }
}

