package MetaData;
import Connection.abstractConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaData extends abstractConnection {

    public static void displayTableNamesAndColumns() {
        try (Connection connection = establishConnection()) {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet tables = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
                System.out.println();
                displayColumns(tableName);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayColumns(String tableName) {
        try (Connection connection = establishConnection()) {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet columns = dbMetadata.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                System.out.println("Column: " + columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayColumnDetails() {
        try (Connection connection = establishConnection()) {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet tables = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                ResultSet columns = dbMetadata.getColumns(null, null, tableName, null);
                System.out.println("Table: " + tableName);
                System.out.println();
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("Column: " + columnName + ", Type: " + columnType);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void displayPrimaryKeys() {
        try (Connection connection = establishConnection()) {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet tables = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                ResultSet primaryKeys = dbMetadata.getPrimaryKeys(null, null, tableName);
                System.out.println("Table: " + tableName);
                System.out.println();
                while (primaryKeys.next()) {
                    String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                    System.out.println("Primary Key: " + primaryKeyColumnName);
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayForeignKeys() {
        try (Connection connection = establishConnection()) {
            DatabaseMetaData dbMetadata = connection.getMetaData();
            ResultSet tables = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                ResultSet foreignKeys = dbMetadata.getImportedKeys(null, null, tableName);
                System.out.println("Table: " + tableName);
                System.out.println();
                while (foreignKeys.next()) {
                    String foreignKeyColumn = foreignKeys.getString("FKCOLUMN_NAME");
                    String referencedTableName = foreignKeys.getString("PKTABLE_NAME");
                    String referencedColumnName = foreignKeys.getString("PKCOLUMN_NAME");
                    System.out.print("Foreign Key: " + foreignKeyColumn + ", References: " + referencedTableName + "(" + referencedColumnName + ")");
                    System.out.println();
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
