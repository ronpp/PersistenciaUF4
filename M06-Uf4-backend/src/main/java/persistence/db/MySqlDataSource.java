package persistence.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDataSource {

    private static final HikariConfig config;
    private static final HikariDataSource ds;

    static {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.4.30:3306/uf4_db");
        config.setUsername("myuser");
        config.setPassword("mypass");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private MySqlDataSource() {}
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}
