package config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"controller","service"})
//JPA对接口的扫描
@EnableJpaRepositories(basePackages = {"dao"})
//对model层扫描
@EntityScan(basePackages = {"model"})
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
//事务驱动
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig {

    /**
     * 把hibernate异常转化为Spring异常
     * @return
     */
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
