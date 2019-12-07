package air.ebds.mybatis.service;

import air.ebds.mybatis.entity.Meta;
import air.ebds.mybatis.entity.SelectCollection;

import java.util.List;

/**
 * @author ChenSijia
 * @date 2019/12/6 14:06
 */
public interface IMetaService {

    List<Meta> getFilesFromParmDB();

    List<SelectCollection> getUserFromSecondDB();
}
