package air.ebds.mybatis.service;

import air.ebds.mybatis.entity.Meta;
import air.ebds.mybatis.entity.SelectCollection;

import java.util.List;

/**
 * @author ChenSijia
 */
public interface IMetaService {

    List<Meta> getFilesFromParmDB();

    List<SelectCollection> getUserFromSecondDB();
}
