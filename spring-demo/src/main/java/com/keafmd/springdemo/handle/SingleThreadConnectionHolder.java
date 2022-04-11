package com.keafmd.springdemo.handle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Keafmd
 *
 * @ClassName: SingleThreadConnectionHolder
 * @Description: 管理Connection对象 -线程安全
 * @author: 牛哄哄的柯南
 * @date: 2022-04-11 10:23
 */
public class SingleThreadConnectionHolder {
    private static ThreadLocal<ConnectionHolder> localConnectionHolder = new ThreadLocal<>();

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        return getConnectionHolder().getConnection(dataSource);
    }

    public static void removeConnection(DataSource dataSource) {
        getConnectionHolder().removeConnection(dataSource);
    }

    private static ConnectionHolder getConnectionHolder() {
        ConnectionHolder connectionHolder = localConnectionHolder.get();
        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            localConnectionHolder.set(connectionHolder);
        }
        return connectionHolder;
    }
}
