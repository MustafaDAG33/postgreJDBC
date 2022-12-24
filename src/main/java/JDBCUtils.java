import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDataBase(String hostname, String dbname, String username,String password){

        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostname+":5432/"+dbname,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("Connection Success");
        }else{
            System.out.println("Connection Fail");
        }

        return connection;
    }

    public  static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //4.adim: query calistir
    public static boolean execute(String sql){
        boolean isExecute;
        try {
            isExecute =  statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //5.adim: baglanti ve statement'i kapat
    public static void closeConAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and statement closed!");

            }else {
                System.out.println("Connection and statement NOT closed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //tABLE OLUSTURAN METHOD
    public static void createTable(String tableName, String... columnName_dataType){

        StringBuilder columnName_dataValue = new StringBuilder("");
        for(String w : columnName_dataType){
            columnName_dataValue.append(w).append(",");
        }
        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);


        try {
            statement.execute( "CREATE TABLE "+tableName+"("+columnName_dataValue+")");
            System.out.println("Table "+tableName+" successfully created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
