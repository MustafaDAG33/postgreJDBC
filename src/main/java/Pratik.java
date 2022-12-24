import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pratik {
    public static void main(String[] args) throws SQLException {

        JDBCUtils.connectToDataBase("localhost","techproed","postgres","330217");
        Statement statement =   JDBCUtils.createStatement();

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        statement.execute("drop table workers");
        statement.execute("create table workers (worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        statement.execute("insert into workers values ('1234', 'mustafa', 5000);");


        String sql1 = "select * from workers where worker_name='mustafa';";

        ResultSet resultSet1 =  statement.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString(3));
        }

        JDBCUtils.closeConAndStatement();




    }
}
