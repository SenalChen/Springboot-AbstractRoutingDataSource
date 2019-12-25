package air.ebds.mybatis;

import air.ebds.mybatis.datasource.DataSourceConfigurer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 此项目为数据库数据源切换项目
 * https://github.com/helloworlde/SpringBoot-DynamicDataSource 在该项目基础上做了精简，并添加了注解注入的方式
 */
@MapperScan("air.ebds.mybatis.mapper")
//排除自动配置数据源
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//导入已经配置好的数据源
@Import({DataSourceConfigurer.class})
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
