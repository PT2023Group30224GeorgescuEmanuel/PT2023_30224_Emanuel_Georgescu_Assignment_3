package BLL;

import DAO.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta clasa reprezinta logica programului pentru tipul Client
 */
public class ClientBLL {

    private ClientDAO client;

    public ClientBLL()
    {
        this.client=new ClientDAO();
    }

    /**
     * Aici se returneaza lista de clienti cu id-ul dat ca si parametru dupa ce se apeleaza interogarile din baza date
     * @param id este id-ul dupa care se va realiza  selectia
     * @return se da return listei de clienti cu id-ul cautat
     * @throws NoSuchElementException se arunca o exceptie in cazul in care nu se gasesc clientii cu id-ul cautat
     */

    public List<Client> gasesteClientDupaId(int id) throws NoSuchElementException
    {
        List<Client> clienti=this.client.findID(id);
        if(clienti.isEmpty())
        {
            throw new NoSuchElementException("Nu am gasit clientul cu id-ul:"+id);
        }
        return clienti;
    }


    /**
     * Se returneaza lista de clienti care se afla in baza de date,dupa ce se apeleaza interogarile din baza de date
     * @return se returneaza lista de clienti care se afla in baza de date
     */
    public List<Client> vizualizareClienti()
    {
        return this.client.selectAll();
    }


    /**
     * Aceasta metoda are rolul de a insera in baza de date un client dat ca si parametru
     * @param client1 este clientul care urmeaza a fi inserat in baza de date
     */
    public void inserareClient(Client client1)
    {
        this.client.insert(client1);
    }

    /**
     * Aceasta metoda are rolul de actualiza baza de date,respectiv un client din baza de date in functie de un id dat ca si parametru
     * @param client1 client1 este clientul in functie de care va fi actualizat clientul ce se doreste a fi actualizat
     * @param id este id-ul clientului care urmeaza a fi actualizat
     */

    public void editareClient(Client client1,int id)
    {
        List<Client> clienti = client.findID(id);
        if(clienti.isEmpty()){
            throw new NoSuchElementException("Nu am gasit clientul cu id-ul:"+id);
        }
        client.update(client1,id);
    }

    /**
     * Aceasta metoda are rolul de a sterge un client din baza de date,in functie de un id dat ca si parametru
     * @param id este id-ul clientului care se doreste a fi sters
     */
    public void stergereClient(int id)
    {
        List<Client> clienti = client.findID(id);
        if(clienti.isEmpty()){
            throw new NoSuchElementException("Nu am gasit clientul cu id-ul:"+id);
        }
        client.delete(id);
    }

    /**
     *Metoda are rolul de a crea un tabel in functie de lista de clienti
     */
    public JTable creeazaTabel(List<Client> lista) throws IllegalAccessException {
        return client.createTable(lista);
    }


}