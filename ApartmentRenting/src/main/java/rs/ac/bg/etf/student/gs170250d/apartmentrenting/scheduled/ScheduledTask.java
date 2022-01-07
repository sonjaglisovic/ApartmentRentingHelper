package rs.ac.bg.etf.student.gs170250d.apartmentrenting.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.CrawlerService;

@Component
public class ScheduledTask {

    @Autowired
    private CrawlerService crawlerService;

    @Scheduled(initialDelay = 5000, fixedDelay = 3600 * 1000)
    public void writeCurrentTime() {

        crawlerService.processCrawling();
    }
}
