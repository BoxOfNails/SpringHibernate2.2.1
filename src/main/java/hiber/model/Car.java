package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="cars")
public class Car {

    @Column(name="model")
    private String model;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="series")
    int series;

    public Car() {}
    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
