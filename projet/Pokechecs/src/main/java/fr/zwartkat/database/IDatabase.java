package fr.zwartkat.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase<T> {

    Connection getConnection() throws SQLException;
}
