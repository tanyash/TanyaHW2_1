package store;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanya on 2/28/14.
 */
@XmlRootElement(namespace = "store")
public class Store {
    @XmlElementWrapper(name = "ordersList")
    @XmlElement(name = "order")
    private List<Order> orderList1;

    public Store() {
        orderList1 = new ArrayList<Order>();
    }

    public List<Order> getOrderList() {
        return orderList1;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList1 = orderList;
    }
}
