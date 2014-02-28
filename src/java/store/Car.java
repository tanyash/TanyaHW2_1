package store;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by tanya on 2/28/14.
 */
@XmlRootElement(name = "car")
@XmlType(propOrder = { "brand", "model", "yearProduced" })
public class Car {
    private String brand;
    private String model;
    private int yearProduced;

    public Car() {
        this.brand = "";
        this.model = "";
        this.yearProduced = 2013;
    }


    public Car(String brand, String model, int yearProduced) {
        this.brand = brand;
        this.model = model;
        this.yearProduced = yearProduced;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(int yearProduced) {
        this.yearProduced = yearProduced;
    }
}
