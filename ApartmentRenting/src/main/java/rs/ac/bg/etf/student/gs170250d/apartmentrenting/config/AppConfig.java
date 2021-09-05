package rs.ac.bg.etf.student.gs170250d.apartmentrenting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableScheduling
@EnableWebMvc
@EnableAspectJAutoProxy
public class AppConfig {
}
