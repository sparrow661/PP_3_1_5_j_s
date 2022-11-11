package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class UsersInit {

    private final UserService userService;
    private final RoleService roleService;

    public UsersInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        User user1 = new User("harry", "111");//$2a$12$54rDWKSismZ9uGff8bWwvetMn/YqhjzHl0P3D7JxY8GSyXeI2zM9e
        User user2 = new User("ron","111");//111//$2a$12$54rDWKSismZ9uGff8bWwvetMn/YqhjzHl0P3D7JxY8GSyXeI2zM9e
        User user3 = new User("hermi", "111");//$2a$12$54rDWKSismZ9uGff8bWwvetMn/YqhjzHl0P3D7JxY8GSyXeI2zM9e

        user1.addRole(roleService.findByName("ROLE_ADMIN"));
        user1.addRole(roleService.findByName("ROLE_USER"));
        user2.addRole(roleService.findByName("ROLE_ADMIN"));
        user3.addRole(roleService.findByName("ROLE_USER"));

        user1.setFirstName("Harry");
        user1.setLastName("Potter");
        user1.setEmail("harry@mail.ru");

        user2.setFirstName("Ron");
        user2.setLastName("Wesley");
        user2.setEmail("ron@mail.ru");

        user3.setFirstName("Hermione");
        user3.setLastName("Granger");
        user3.setEmail("germi@mail.ru");

        userService.updateUser(user1);
        userService.updateUser(user2);
        userService.updateUser(user3);
    }
}
