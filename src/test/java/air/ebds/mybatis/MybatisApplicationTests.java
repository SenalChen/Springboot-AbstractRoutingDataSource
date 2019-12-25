package air.ebds.mybatis;

import air.ebds.mybatis.datasource.DynamicDataSourceContextHolder;
import air.ebds.mybatis.mapper.IDataMapper;
import air.ebds.mybatis.service.Impl.IMetaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    private IDataMapper iDataMapper;

    @Autowired
    private IMetaServiceImpl iMetaService;

    @Test
    void contextLoads() {
        System.out.println(iMetaService.getFilesFromParmDB().size());
        System.out.println(iMetaService.getUserFromSecondDB().size());
        System.out.println(iMetaService.getAllFilesFromParmDB().size());
    }

}
