package air.ebds.mybatis.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author ChenSijia
 */
@Data
public class Meta {
    private String tableName;
    private int directoryId;
    private String fileName;
    private int processCode;
}
