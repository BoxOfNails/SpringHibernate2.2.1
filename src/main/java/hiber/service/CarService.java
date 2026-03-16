package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CarService {
    void add(Car car);
    void add(List<Car> cars);
    List<Car> listCars();
}
