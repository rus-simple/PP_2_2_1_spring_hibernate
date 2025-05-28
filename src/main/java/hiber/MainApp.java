package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

public class MainApp {
   private static final Logger logger = Logger.getLogger(MainApp.class.getName());

   public static void main(String[] args) {
      try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
         UserService userService = context.getBean(UserService.class);

         // Добавление пользователей
         try {
            userService.add(new User("User1", "Lastname1", "user1@mail.ru"), new Car("Toyota", 2020));
            userService.add(new User("User2", "Lastname2", "user2@mail.ru"), new Car("Ford", 2021));
            userService.add(new User("User3", "Lastname3", "user3@mail.ru"), new Car("Nissan", 2022));
            userService.add(new User("User4", "Lastname4", "user4@mail.ru"), new Car("BMW", 2023));
         } catch (Exception e) {
            logger.log(Level.WARNING, "Ошибка при добавлении пользователей: ", e);
         }

         // Вывод списка пользователей
         try {
            List<User> users = userService.listUsers();
            for (User u : users) {
               System.out.println("Id = " + u.getId());
               System.out.println("First Name = " + u.getFirstName());
               System.out.println("Last Name = " + u.getLastName());
               System.out.println("Email = " + u.getEmail());
               System.out.println("model = " + u.getCar().getModel());
               System.out.println("series = " + u.getCar().getSeries());
               System.out.println();
            }
         } catch (Exception e) {
            logger.log(Level.WARNING, "Ошибка при получении списка пользователей: ", e);
         }

         // Поиск пользователя по машине
         try {
            User user = userService.findUserByCar("Nissan", 2022);
            if (user != null) {
               System.out.println("Найден пользователь: " + user.getFirstName() +
                       " владеющий машиной: " + user.getCar().getModel() +
                       " " + user.getCar().getSeries());
            } else {
               System.out.println("Пользователь не найден!");
            }
         } catch (Exception e) {
            logger.log(Level.WARNING, "Ошибка при поиске пользователя по машине: ", e);
         }
      } catch (Exception e) {
         logger.log(Level.SEVERE, "Критическая ошибка при запуске приложения: ", e);
      }
   }
}