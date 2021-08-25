package rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.CrawlerService;

@SpringBootTest
public class DemandRepositoryTest {

    @Autowired
    DemandRepository demandRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    void testFindByUser() {
        UserEntity user = new UserEntity();
        user.setEmail("bla");

        userRepository.save(user);
        demandRepository.findByUser(user);
    }

    @Test
    void crawlerTest() {
        CrawlerService.processCrawling("sonja", apartmentRepository, demandRepository);
    }
}
