package com.keafmd.springdemo.handle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Keafmd
 *
 * @ClassName: ConnectionHolder
 * @Description: 管理Connection对象 -线程不安全
 * @author: 牛哄哄的柯南
 * @date: 2022-04-11 10:17
 */
public class ConnectionHolder {
    private final Map<DataSource, Connection> connectionMap = new HashMap<>();

    public Connection getConnection(DataSource dataSource) throws SQLException
    {
        Connection connection = connectionMap.get(dataSource);
        if (connection == null || connection.isClosed())
        {
            connection = dataSource.getConnection();
            connectionMap.put(dataSource, connection);
        }
        return connection;
    }
    public void removeConnection(DataSource dataSource)
    {
        connectionMap.remove(dataSource);
    }
}
