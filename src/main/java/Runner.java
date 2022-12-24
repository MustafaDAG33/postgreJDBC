import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {

        //1.adim:driver'a kaydol
        //2.adim:database'e baglan
        Connection connection =  JDBCUtils.connectToDataBase("localhost", "techproed","postgres","330217");


        //3.adim: statement olustur.
        Statement statement =  JDBCUtils.createStatement();

        //4.adim: query calistir.
        //JDBCUtils.execute("CREATE TABLE students (name varchar(20), id int, address varchar(50))");
        JDBCUtils.createTable("ABC", "classes varchar(20)","teacher_name varchar(20)","id int");

        //5.adim:baglanti ve statement'i kapat
        JDBCUtils.closeConAndStatement();








    }
}
