package air.ebds.mybatis.datasource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenSijia
 * @date 2019/12/6 17:29
 */
@Configuration
public class DataSourceConfigurer {

    /**
     * master DataSource
     * @ConfigurationProperties 注解用于从 application.properties 文件中读取配置，为 Bean 设置属性
     * @return data source
     */
    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 第二个数据库配置
     * @return data source
     */
    @Bean("second")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource second() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     * 设置Dynamic的数据源
     * @return the data source
     */
    @Bean("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(DataSource master, DataSource second) {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.master.name(), master);
        dataSourceMap.put(DataSourceKey.second.name(), second);
        /*
          此处包含几种添加默认数据库连接的方式：
          1.注掉下面这个调用，在需要调用的方法中添加@DataSource注解，并且在注解中填入default值为master
          2.使用下面注解，不需要给default注解添加默认值
         */
        dynamicRoutingDataSource.setDefaultTargetDataSource(master);
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
//        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicRoutingDataSource;
    }

    /**
     * Mybatis的主要设置，切换数据库的主要代码
     * 配置 SqlSessionFactoryBean
     * @ConfigurationProperties 在这里是为了将 MyBatis 的 mapper 位置和持久层接口的别名设置到
     * Bean 的属性中，如果没有使用 *.xml 则可以不用该配置，否则将会产生 invalid bond statement 异常
     * @return 返回SqlSessionFactoryBean
     */
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setTypeAliasesPackage("air.ebds.mybatis.mapper");
//        // 本次项目中采用注解方式，不存在xml文件，因此，忽略该行代码配置
////        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mappers/**Mapper.xml"));
//        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }

    /**
     * 注入 DataSourceTransactionManager 用于事务管理
     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }

}
