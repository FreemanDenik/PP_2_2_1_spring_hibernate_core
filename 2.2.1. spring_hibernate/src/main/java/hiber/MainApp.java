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

      User u1 = new User("User1", "Lastname1", "user1@mail.ru");
      User u2 = new User("User2", "Lastname2", "user1@mail.ru");
      User u3 = new User("User3", "Lastname3", "user1@mail.ru");
      Car c1 = new Car("Honda1",45);
      Car c2 = new Car("Honda2",25);
      Car c3 = new Car("Honda3",15);
      u1.setCar(c1);
      u2.setCar(c2);
      u3.setCar(c3);
      userService.add(u1);
      userService.add(u2);
      userService.add(u3);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getModel());
         System.out.println();
      }

      User us = userService.getUser("Honda2", 25);
      System.out.println(us.getFirstName() + " " + us.getCar().getModel());

      context.close();
   }
}
