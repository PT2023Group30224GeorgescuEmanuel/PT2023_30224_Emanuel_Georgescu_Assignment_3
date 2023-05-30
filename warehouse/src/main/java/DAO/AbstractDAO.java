package DAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

/**
 * Aceasta clasa reprezinta o clasa generica care contine metode pentru adaugare,editare,stergere si selectie din baza de date
 * @param <T> este tipul generic,care poate fi oricare din: Client,Product sau Order
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Aceasta metoda are rolul de returna o lista de elemente care se afla in baza date cu un id dat ca si parametru
     * @param id este id-ul elementului in functie de care se va face cautarea
     * @return se returneaza o lista de elemente care au id-ul dat ca si parametru
     */

    public List<T> findID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + "id" + " =?");
        String querry = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(querry);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> result = this.createObjects(resultSet);
            return result;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + ex.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }



    /**
     *Aceasta metoda are rolul de returna lista de elemente care se afla in baza de date
     * @return se returneaza o lista de elemente care se afla in baza de date
     */

    public List<T> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("* ");
        sb.append("FROM ");
        sb.append(this.type.getSimpleName());
        String result = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(result);
            resultSet = statement.executeQuery();
            return this.createObjects(resultSet);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *Aceasta metoda are rolul de creea un tabel cu elemente dintr-o lista care este data ca si parametru
     * @param lista reprezinta elementele care vor fi intoduse in tabel
     * @return se returneaza tabelul cu elementele care vor fi introduse in tabel
     * @throws IllegalAccessException se arunca exceptie
     */

    public JTable createTable(List<T> lista) throws IllegalAccessException
    {
        JTable tabel=new JTable();
        DefaultTableModel model=new DefaultTableModel();
        tabel.setModel(model);
        List<String> coloane=new ArrayList<>();
        Field[] v=this.type.getDeclaredFields();
        int i=0;
        while(i<v.length)
        {
            coloane.add(v[i].getName());
            i++;
        }
        String[] coloane1=coloane.toArray(new String[0]);
        model.setColumnIdentifiers(coloane1);
        int j=0;
        while(j<(lista.size()))
        {
            T obj= lista.get(j);
            Object[] objects=new Object[coloane1.length];
            int j1=0,index_obj=0;
            while(j1<this.type.getDeclaredFields().length)
            {
                Field fld=this.type.getDeclaredFields()[j1];
                fld.setAccessible(true);
                objects[index_obj]=fld.get(obj);
                ++index_obj;
                ++j1;
            }
            model.addRow(objects);
            j++;
        }
        return tabel;
    }



    /**
     * Aceasta metoda are rolul de insera in baza de date un element care este dat ca si parametru
     * @param obj este elementul care va fi inserat in baza de date
     */

    public void insert(T obj) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(this.type.getSimpleName());
        sb.append(" VALUES (");
        Field[] v = this.type.getDeclaredFields();
        for (int j = 0; j < v.length; j++) {
            Field field = v[j];
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        String result = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(result);
            int i = 1, j = 0;
            Field[] v1 = obj.getClass().getDeclaredFields();
            while (j < v1.length) {
                Field field = v1[j];
                field.setAccessible(true);
                String tip_date = String.valueOf(field.getType());
                if (tip_date.compareTo("int") == 0) {
                    statement.setInt(i, (Integer) field.get(obj));
                } else if (tip_date.compareTo("float") == 0) {
                    statement.setFloat(i, (Float) field.get(obj));
                } else {
                    statement.setString(i, (String) field.get(obj));
                }
                i = i + 1;
                j++;
            }
            statement.executeUpdate();
        } catch (IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



    /**
     *Aceatsa metoda are rolul de sterge din baza de date un element cu un id dat ca si parametru
     * @param id este id-ul elementului care va fi sters din baza de date
     */

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(this.type.getSimpleName());
        sb.append(" WHERE " + "id" + " =?");
        String result = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(result);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + ex.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Aceasta metoda are rolul de edita un element din baza de date cu id-ul dat ca si parametru
     * @param obj este elementul cu care se va face editarea
     * @param id este  id-ul elementului care va fi editat
     */

    public void update(T obj, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(this.type.getSimpleName());
        sb.append(" SET ");
        Field[] v = this.type.getDeclaredFields();
        int i = v.length;
        for (int j = 0; j < i; j++) {
            Field field = v[j];
            field.setAccessible(true);
            String nume = field.getName();
            sb.append(nume + "=?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id=?");
        String result = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(result);
            Field[] v1 = obj.getClass().getDeclaredFields();
            int i1 = 1, j = 0;
            while (j < v1.length) {
                Field field = v1[j];
                field.setAccessible(true);
                String tip_date = String.valueOf(field.getType());
                if (tip_date.compareTo("int") == 0) {
                    statement.setInt(i1, (Integer) field.get(obj));
                } else if (tip_date.compareTo("float") == 0) {
                    statement.setFloat(i1, (Float) field.get(obj));
                } else {
                    statement.setString(i1, (String) field.get(obj));
                }
                i1 = i1 + 1;
                j = j + 1;
            }
            statement.setInt(i1, id);
            statement.executeUpdate();
        } catch (IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * Aceasta metoda are rolul de converti din ResultSet intr-o lista de obiecte
     * @param resultSet este ResultSet-ul care este returnat dupa comenzi sql
     * @return se retuneaza Lista de obiecte convertita
     */

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    System.out.println(fieldName);
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                System.out.println(instance);
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}