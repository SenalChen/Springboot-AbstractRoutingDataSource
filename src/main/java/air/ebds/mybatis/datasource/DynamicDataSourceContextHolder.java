package air.ebds.mybatis.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenSijia
 * @date 2019/12/6 17:28
 */
public class DynamicDataSourceContextHolder {

    /**
     * Maintain variable for every thread, to avoid effect other thread
     */
    private static final ThreadLocal<Object> CONTEXT_HOLDER = new ThreadLocal<>();


    /**
     * All DataSource List
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * To switch DataSource
     *
     * @param key the key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * Get current DataSource
     *
     * @return data source key
     */
    public static String getDataSourceKey() {
        return String.valueOf(CONTEXT_HOLDER.get());
    }

    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

}
