package air.ebds.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 此项目为数据库数据源切换项目
 * https://github.com/helloworlde/SpringBoot-DynamicDataSource 在该项目基础上做了精简，并添加了注解注入的方式
 */
@SpringBootApplication()
@MapperScan("air.ebds.mybatis.mapper")
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
