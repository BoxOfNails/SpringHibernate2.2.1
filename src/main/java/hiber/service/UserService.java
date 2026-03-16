package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void add(Car car);
    void addCars(List<Car> cars);
    void addUsers(List<User> users);
    List<User> listUsers();
    List<Car> listCars();
    void linkUserToCar(User user, Car car);
    User findUserByCarModelAndSeries(String model, int series);
}
