package xyz.huanxicloud.findjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("xyz.huanxicloud.findjob.mapper")
public class FindjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindjobApplication.class, args);
    }
}
