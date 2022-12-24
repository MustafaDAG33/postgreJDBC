import java.sql.*;


public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","330217");
        Statement st = con.createStatement();

        /*
PreparedStatement interface, birden çok kez çalıştırılabilen önceden derlenmiş bir SQL kodunu temsil eder.
Paremetrelendirilmiş SQL sorguları(query) ile çalışır. Bur sorguyu 0 veya daha fazla parametre ile kullanabiliriz.
 */
        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1.adim:preparedstatement query'sini olustur.
        String sql1 = "update companies set number_of_employees = ? where company= ? ";

        //2.adim: preparedstatement objesini olustur.
        java.sql.PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.adim: setInt, setString methodlarini kullanarak soru isaretleri yerine deger gir
        pst1.setInt(1,9999);
        pst1.setString(2, "IBM");

        //4.ADIM: QUERY CALISTIR
        int guncellenensatirsayisi = pst1.executeUpdate();
        System.out.println("guncellenensatirsayisi = " + guncellenensatirsayisi);

        String sql2 = "select * from companies";
        ResultSet resultSet1 =    st.executeQuery(sql2);

        while(resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"--"+resultSet1.getString(2)+"--"+resultSet1.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rs2 = st.executeQuery(sql2);

        while (rs2.next()){
            System.out.println(rs2.getInt(1)+ " -- "+ rs2.getString(2)+ " -- "+ rs2.getInt(3));
        }

        con.close();
        st.close();
        resultSet1.close();
        rs2.close();
        pst1.close();





    }
}
