package br.com.usp.stoodi.repositories;

import br.com.usp.stoodi.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Boolean existsByEmail(String email);
}
