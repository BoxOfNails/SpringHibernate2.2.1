package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //Создаём список юзеров и сохраняем его
      List<User> listOfUsers = new ArrayList<>();
      listOfUsers.add(new User("User1", "Lastname1", "user1@mail.ru"));
      listOfUsers.add(new User("User2", "Lastname2", "user2@mail.ru"));
      listOfUsers.add(new User("User3", "Lastname3", "user3@mail.ru"));
      listOfUsers.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.addUsers(listOfUsers);

      // Создаём список машин и сохраняем его
      List<Car> listOfCars = new ArrayList<>();
      listOfCars.add(new Car("model1", 12313));
      listOfCars.add(new Car("model2", 456456));
      listOfCars.add(new Car("model3", 67673));
      listOfCars.add(new Car("model4", 243528));
      userService.addCars(listOfCars);

      // Вытаскиваем/получаем из бд список юзеров и список машин.
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }
      List<Car> cars = userService.listCars();
      for (Car car : cars) {
         System.out.println(car);
      }

      //Раздаём юзерам машины из полученного списка и сохраняем список юзеров в бд
      Iterator<User> userIterator = users.iterator();
      Iterator<Car> carIterator = cars.iterator();
      while(userIterator.hasNext() && carIterator.hasNext()) {
         User user1 = userIterator.next();
         Car car1 = carIterator.next();
         userService.linkUserToCar(user1, car1);
      }

      System.out.println("Users after linking them to cars:");
      users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      String targetModel = "model4";
      int targetSeries = 243528;
      System.out.println("Looking for users with car model = " + targetModel + " and series = " + targetSeries);
      try {
         User foundUser = userService.findUserByCarModelAndSeries(targetModel, targetSeries);
         System.out.println("Found user: " + foundUser);
      } catch (NoResultException e) {
         System.out.println("No such users found!");
      }

      context.close();
   }
}
