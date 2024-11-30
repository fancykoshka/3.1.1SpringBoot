package ru.alinka.springboot.Springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alinka.springboot.Springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

