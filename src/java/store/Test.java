package store;

/**
 * Created by tanya on 2/28/14.
 */
public class Test {
    public static void main(String[] args) {
        XMLBinder x=new XMLBinder("/home/tanya/store.xml");
        Order order1 = new Order();
        order1.setOrder(new User(), new Car());
        x.getStore().getOrderList().add(order1);

        x.setStoreDataToXML();

    }
}
