package br.com.usp.stoodi.repositories;

import br.com.usp.stoodi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByEmail(String email);
}
