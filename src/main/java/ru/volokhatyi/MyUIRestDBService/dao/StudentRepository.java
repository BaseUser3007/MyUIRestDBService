package ru.volokhatyi.MyUIRestDBService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volokhatyi.MyUIRestDBService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
