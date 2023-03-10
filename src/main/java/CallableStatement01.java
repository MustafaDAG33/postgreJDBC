import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","330217");
        Statement st = con.createStatement();

        /*
        java'da methodlar return type sahibi olsa da olmasa da method olarak adlandirilir.
        sql de ise data return ediyorsa "function" denir. return yapmiyorsa "precedure" olarak adlandirilir.
         */

        //collablestatement ile function cagirmayi parametrelendirecegiz.
        //1.adim: function kodunu yaz
        String sql1 = "create or replace function toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";
        //2.adim: function'i calistir.
        st.execute(sql1);

        //3.adim: function'i cagir
        CallableStatement cst1 =  con.prepareCall("{? = call toplamaF(?,?)}");

        //4.adim: return icin registerOurParameter() methodunu, parametreler icin ise set() methodlarini kullaniriz
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //5.ADIM execute () ile collablestatement ' calistir.
        cst1.execute();

        //6.adim: sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 = "create or replace function koniHacmiF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN r*r*3.14*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        st.execute(sql2);

        CallableStatement cst2 =  con.prepareCall("{? = call koniHacmiF(?,?)}");

        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        cst2.execute();

        System.out.printf("%.2f",  cst2.getBigDecimal(1));





    }
}
