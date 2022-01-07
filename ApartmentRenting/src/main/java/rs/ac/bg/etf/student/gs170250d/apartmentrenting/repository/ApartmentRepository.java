package rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Optional<Apartment> findApartmentByUrl(String url);

    @Query("SELECT a FROM Apartment a WHERE a.price >= ?1 and a.price <= ?2 and a.numOfRooms >= ?3 " +
            "and a.numOfRooms <= ?4 and a.area >= ?5 and a.area <= ?6 and a.floor >= ?7 and a.floor <= ?8")
    List<Apartment> findPossiblySuitableApartments(int priceMin, int priceMax, double numberOfRoomsMin,
                                                   double numberOfRoomsMax, int areaMin, int areaMax, int floorMin, int floorMax);
}
