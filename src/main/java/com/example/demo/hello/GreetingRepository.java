package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Integer> {

    @Query("SELECT g FROM Greeting g WHERE g.userId = (SELECT u.id FROM User u WHERE u.username = :username)")
    Greeting findByUsername(String username);
}