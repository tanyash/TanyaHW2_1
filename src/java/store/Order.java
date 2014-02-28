package store;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by tanya on 2/28/14.
 */
@XmlRootElement(name = "order")
@XmlType()
public class Order {
    @XmlElement(name = "user")
    private User user;
    @XmlElement(name = "car")
    private Car car;

    public void setOrder(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }
}
