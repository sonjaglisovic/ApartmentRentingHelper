package rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
