package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //userService.createUserWithCar("Ivan", "Ivanov", "ivan@mail.ru", "Toyota", 2020);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),new Car("Toyota",2020));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"),new Car("Ford",2021));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"),new Car("Nissan",2022));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"),new Car("BMW",2023));

      List<User> users = userService.listUsers();
      for (User u : users) {
         System.out.println("Id = "+u.getId());
         System.out.println("First Name = "+u.getFirstName());
         System.out.println("Last Name = "+u.getLastName());
         System.out.println("Email = "+u.getEmail());
         System.out.println("model = "+u.getCar().getModel());
         System.out.println("series = "+u.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
