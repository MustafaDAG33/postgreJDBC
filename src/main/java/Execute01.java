import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","330217");

        Statement st = con.createStatement();
        System.out.println("Connection Success");

        //4.adim query calistir
        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

        boolean sql1 = st.execute("create table workers(worker_id varchar(20), worker_name varchar(20), worker_salary int)");
        System.out.println("sql1 = " + sql1);//false return eder. cunku data cagirmiyoruz.

        //execute methodu DDL(create, drop,alter table) ve DQL(select) icin kullanilabilir.
        //eger execute() methodu DDL icin kullanilirsa false return eder. DQL icin kullanilirsa ResultSet alindiginda true aksi halde false verir.

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "alter table workers add worker_address varchar(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.ornek workers table silin
        String sql3 = "drop table workers";
        st.execute(sql3);

        //5.adim
        con.close();
        st.close();


    }
}
