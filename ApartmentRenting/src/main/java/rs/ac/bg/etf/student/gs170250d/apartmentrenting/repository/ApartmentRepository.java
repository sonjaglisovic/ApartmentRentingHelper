package rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Optional<Apartment> findApartmentByUrl(String url);
}
