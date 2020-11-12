package lv.learning.spring_boot_with_mysql.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport // enable Sort and Page
public class PersistenceContext {
}
