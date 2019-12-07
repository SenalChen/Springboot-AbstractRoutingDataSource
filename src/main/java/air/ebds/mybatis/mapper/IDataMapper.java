package air.ebds.mybatis.mapper;

import air.ebds.mybatis.entity.Meta;
import air.ebds.mybatis.entity.SelectCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChenSijia
 * @date 2019/12/6 13:45
 */
@Mapper
public interface IDataMapper {

    @Select("select * from t_file")
    List<Meta> getFilesFromParmDB();

    @Select("select * from air_ebds_t_collection_select")
    List<SelectCollection> getUserFromSecondDB();
}
