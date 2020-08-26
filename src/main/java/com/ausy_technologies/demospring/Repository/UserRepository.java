package com.ausy_technologies.demospring.Repository;


import com.ausy_technologies.demospring.Model.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.email = :email where u.id = :id")
    void updateEmail(@Param(value = "id") int id, @Param(value = "email") String email);

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.firstName = :firstName")
    void updatePassword(@Param(value = "firstName") String firstName, @Param(value = "password") String password);
}
