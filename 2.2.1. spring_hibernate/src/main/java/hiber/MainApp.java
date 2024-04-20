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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car car1 = new Car("BMW", 7);
        Car car2 = new Car("Hyundai", 30);
        Car car3 = new Car("Lada", 2107);
        Car car4 = new Car("Toyota", 300);

        userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));
        userService.add(new User("User8", "Lastname8", "user8@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() == null) {
                System.out.println("Car model and series = " + null);
            } else {
                System.out.println("Car model= " + user.getCar().getModel());
                System.out.println("Car series= " + user.getCar().getSeries());
            }
            System.out.println();
        }

        List<User> users1 = userService.getUserByCar("Lada", 2107);
        for (User user : users1) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
