package air.ebds.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author ChenSijia
 * @date 2019/12/6 13:47
 */
@Data
public class Meta {
    private String tableName;
    private int directoryId;
    private String fileName;
    private int processCode;
}
