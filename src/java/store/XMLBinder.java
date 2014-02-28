package store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;

/**
 * Created by tanya on 2/28/14.
 */
public class XMLBinder {
    private File xmlFile;
    private Store store;

    public XMLBinder(String path) {
        xmlFile = new File(path);
        store = new Store();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<User> getUsers(){
        Set<User> users = new HashSet<User>();
        List<Order> orders = store.getOrderList();
        User user = null;
        Car car = null;

        for (Order o: orders){
            user = o.getUser();
            if (!users.contains(user)){
                users.add(user);
            }
        }

        return users;
    }

    public List<Order> getUserOrders(User user){
        if (store == null){
            return null;
        }
        List<Order> orders = store.getOrderList();
        List<Order> userOrders = new ArrayList<Order>();

        for (Order o: orders){
            if (o.getUser().getLogin().equals(user.getLogin())){
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    public void getStoreDataFromXML(){
        Store store1 = null;
        if (!((xmlFile.exists() && !xmlFile.isDirectory()))){
            return;
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(Store.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream stream = null;
            stream = new FileInputStream(xmlFile);
            store1=(Store) unmarshaller.unmarshal(stream);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.store = store1;

    }

    public void setStoreDataToXML(){

        try {
            JAXBContext jc = JAXBContext.newInstance(Store.class);
            Marshaller m = jc.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream(xmlFile, false);
            m.marshal(store, os);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
