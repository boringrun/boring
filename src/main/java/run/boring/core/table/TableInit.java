package run.boring.core.table;

import javax.annotation.PostConstruct;

import run.boring.core.bean.EnvBean;
import run.boring.core.table.service.MysqlTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Initialize table, scan the pointed destination package, generate the tables.
 */
@Service
public class TableInit {

    private static final Logger log = LoggerFactory.getLogger(TableInit.class);

    @Autowired
    EnvBean envBean;

    /**
     * constant value for mysql database.
     */
    public static String MYSQL = "mysql";

    /**
     * Supported database type, value from configuration properties file;
     */
    @Value("${boring.database}")
    private String databaseType = MYSQL;

    @Value("${boring.table.init}")
    public boolean init = false;

    public String getDatabaseType() {
        return databaseType;
    }

    public boolean isInit() {
        return init;
    }

    /**
     * link to table create service;
     */
    @Autowired
    private MysqlTableService mysqlTableService;

    @PostConstruct
    public void start() {
        if (!envBean.isTableInit())
            return;
        if (MYSQL.equals(databaseType))
            mysqlTableService.createMysqlTable();
    }
}
