package rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;

import java.util.List;

public interface DemandRepository extends JpaRepository<Demand, Long> {

   List<Demand> findByUser(UserEntity user);
}
