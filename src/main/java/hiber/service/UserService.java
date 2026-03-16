package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void add(List<User> users);
    List<User> listUsers();
    void linkUserToCar(User user, Car car);
    User findUserByCarModelAndSeries(String model, int series);
}
