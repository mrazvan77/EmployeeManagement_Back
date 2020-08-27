package com.ausy_technologies.demospring.Service;

import com.ausy_technologies.demospring.ErrorHandling.ErrorResponse;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User saveUser2(User user ,int idRole) {
       Role role = this.roleRepository.findById(idRole).get();
       List<Role> roleList = new ArrayList<>();

       roleList.add(role);

       if(role != null) {
           user.setRoleList(roleList);
           return this.userRepository.save(user);
       }
       else
       {
           throw new ErrorResponse("Role not found!");
       }
    }

    public User saveUser3(  User user ,List<Role> roleList) {
        user.setRoleList(roleList);
        return this.userRepository.save(user);
    }

    public Role findRoleById(int id) {

        Role role = this.roleRepository.findById(id).get();

        if(role != null) {
            return role;
        }
        else {
            throw new ErrorResponse("Role not found");
        }
    }

    public User findUserById(int id) {

        User user = this.userRepository.findById(id);

        if(user != null) {
            return user;
        }
        else {
            throw new ErrorResponse("User not found");
        }
    }

    public List<Role> findAllRoles()
    {
        return this.roleRepository.findAll();
    }

    public List<User> findAllUsers()
    {
        return this.userRepository.findAll();
    }

    public void deleteUserById(int id)
    {
         this.userRepository.deleteById(id);
    }

    public void deleteRoleById(int id) {
        this.roleRepository.deleteById(id);
    }

    public User updateUser(int id, String name) {
        User user = this.userRepository.getOne(id);

        if(user != null) {
        user.setFirstName(name);
        return this.userRepository.save(user);
        }
        else {
            throw new ErrorResponse("User not found!");
        }

    }

    public User updateEmail(int id, String email) {
        return this.userRepository.updateEmail(id, email);
    }

    public User updatePassword(String firstName, String password) {
        return this.userRepository.updatePassword(firstName, password);
    }

    public User updateRole(List<Integer> ids) {
        Role role = this.roleRepository.findById(ids.get(1)).get();
        User user = this.userRepository.findById(ids.get(0)).get();
        List<Role> roleList = user.getRoleList();

        if(role != null) {
            if(user != null) {
                roleList.add(role);
                user.setRoleList(roleList);
                return this.userRepository.save(user);
            }
            else {
                throw new ErrorResponse("User not found!");
            }
        }
        else {
            throw new ErrorResponse("Role not found");
        }

    }
}