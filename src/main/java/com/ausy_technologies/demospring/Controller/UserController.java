package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.ErrorHandling.ErrorResponse;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Model.DTO.UserDto;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    boolean isDebug = java.lang.management.ManagementFactory.
            getRuntimeMXBean().
            getInputArguments().toString().indexOf("jdwp") >= 0;

    @PostMapping("/addRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        Role roleAdded = this.userService.saveRole(role);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "addNewRole");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roleAdded);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userAdded = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "addNewUser");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @PostMapping("/addUser2/{idRole}")
    public ResponseEntity<User> saveUser2(@RequestBody User user, @PathVariable int idRole) {
        User userAdded = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "addUserByRole");

        try {
            userAdded = this.userService.saveUser2(user, idRole);
        }catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "AddUser2 can't find role id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(userAdded);
        }

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @PostMapping("/addUser3/{roleList}")
    public ResponseEntity<User> saveUser3(@RequestBody User user , @PathVariable List<Role> roleList) {
        User userAdded = this.userService.saveUser3(user, roleList);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "addUserByList");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @GetMapping("/findRoleBy/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable int id) {
        Role role = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "findRoleById");

        try {
            role = this.userService.findRoleById(id);
        } catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "FindRoleById can't find given ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(role);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(role);
    }

    @GetMapping("/findUserBy/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        User user = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "findUserById");

        try {
            user = this.userService.findUserById(id);
        } catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "FindUserById can't find given ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(user);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(user);
    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> roleList = this.userService.findAllRoles();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "findAllRoles");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(roleList);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = this.userService.findAllUsers();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "findAllUsers");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userList);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUser(@PathVariable int id) {
        this.userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteRoleById/{id}")
    public void deleteRole(@PathVariable int id) {
        this.userService.deleteRoleById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody String name) {
        User user = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "updateUserName");

        try {
            user = this.userService.updateUser(id, name);
        }catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "UpdateUser can't find given ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(user);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(user);
    }

    @PutMapping("/updateEmail/{id}")
    public ResponseEntity<User> updateEmail(@PathVariable int id, @RequestBody String email) {
        User user = this.userService.updateEmail(id, email);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "updateUserEmail");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(user);
    }

    @PutMapping("/updatePass/{firstName}")
    public ResponseEntity<User> updatePassword(@PathVariable String firstName, @RequestBody String password) {
        User user = this.userService.updatePassword(firstName, password);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Responded", "updateUserEmail");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(user);
    }

    @PutMapping("/updateRoles/{idList}")
    public ResponseEntity<User> updateRoles(@PathVariable List<Integer> idList) {
        User user = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "updateUserRole");

        try {
            user = this.userService.updateRole(idList);
        }catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "UpdateRoles can't find given IDs");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(user);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(user);
    }

    @GetMapping("/getUserDto/{id}")
    public ResponseEntity<UserDto> getUserDto(@PathVariable int id) {
        User user = null;
        UserDto userDto = null;
        Logger logger = Logger.getLogger(UserController.class.getName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "updateUserRole");

        try {
            user = this.userService.findUserById(id);
            userDto = this.userService.convertToDto(user);
        }catch (ErrorResponse e) {
            if(isDebug)
                e.printStackTrace();
            else
                logger.log(Level.SEVERE, "GetUserDTO can't find given ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(userDto);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDto);
    }

    @GetMapping("/allUserDtos")
    public ResponseEntity<List<UserDto>> findAllUserDtos() {
        List<User> userList = this.userService.findAllUsers();
        HttpHeaders httpHeaders = new HttpHeaders();
        List<UserDto> userDtos = this.userService.convertListToDto(userList);

        httpHeaders.add("Responded", "findAllUsers");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDtos);
    }
}
