package air.ebds.mybatis.service.Impl;

import air.ebds.mybatis.datasource.DataSource;
import air.ebds.mybatis.datasource.DataSourceKey;
import air.ebds.mybatis.datasource.DynamicDataSourceContextHolder;
import air.ebds.mybatis.entity.Meta;
import air.ebds.mybatis.entity.SelectCollection;
import air.ebds.mybatis.mapper.IDataMapper;
import air.ebds.mybatis.service.IMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenSijia
 * @date 2019/12/6 14:11
 */
@Service
public class IMetaServiceImpl implements IMetaService {

    @Autowired
    IDataMapper iDataMapper;

    /**
     * 默认数据库为master所以不需要添加任何注解
     * @return
     */
    @Override
    public List<Meta> getFilesFromParmDB() {
        return iDataMapper.getFilesFromParmDB();
    }

    @DataSource(DataSourceKey.second)
    @Override
    public List<SelectCollection> getUserFromSecondDB() {
        return iDataMapper.getUserFromSecondDB();
    }

    @DataSource(DataSourceKey.master)
    public List<Meta> getAllFilesFromParmDB(){return iDataMapper.getFilesFromParmDB();}
}
