package air.ebds.mybatis.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ChenSijia
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * 调用注解前，切换对应的数据库
     * @param point 切入点为DataSource所调用的方法
     */
    @Before("@annotation(air.ebds.mybatis.datasource.DataSource)")
    public void switchDataSource(JoinPoint point) {
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        DataSourceKey dataSource = null;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 验证是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        if (dataSource != null){
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.name());
            logger.info("数据库数据源已切换为：[{}]", dataSource.name());
        } else {
            logger.info("已调用默认数据库数据源：[{}]", DataSourceKey.master.name());
        }
    }

    @After("@annotation(air.ebds.mybatis.datasource.DataSource)")
    public void restoreDataSource(JoinPoint point) {
        logger.info("清除数据库数据源，之前调用的数据库数据源为：[{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("数据源已经清除为： [{}] 在方法[{}]中调用结束",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

}
