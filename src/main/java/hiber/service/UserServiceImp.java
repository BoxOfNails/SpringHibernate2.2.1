package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private CarDao carDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public void add(Car car) {
      carDao.add(car);
   }

   @Transactional
   @Override
   public void addCars(List<Car> cars) {
      for(Car car : cars) {
         carDao.add(car);
      }
   }

   @Transactional
   @Override
   public void addUsers(List<User> users) {
      for(User user : users) {
         userDao.add(user);
      }
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<Car> listCars() {
      return carDao.listCars();
   }

   @Transactional
   @Override
   public void linkUserToCar(User user, Car car) {
      user.setUser_car(car);
      userDao.update(user);
   }

   @Transactional(readOnly = true)
   @Override
   public User findUserByCarModelAndSeries(String model, int series) {
      return userDao.findUserByCarModelAndSeries(model, series);
   }

}
