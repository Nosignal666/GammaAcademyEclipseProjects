import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class TestJDBC {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=null;
			Statement stm=null;
			ResultSet rs=null;
			String url="jdbc:postgresql://localhost:5432/postgres";
			con=DriverManager.getConnection(url,"postgres","academy2020");
			
		
			
			stm=con.createStatement();
			rs=stm.executeQuery("select * from impiegato");
			int matricola;
			String cognome;
			int stipendio;
			String dipartimento;
			
			
			while(rs.next()){
				matricola=rs.getInt(1);
				cognome=rs.getString(2);
				stipendio=rs.getInt(3);
				dipartimento=rs.getString(4);
				System.out.println(matricola+"-"+cognome+"-"+stipendio+"-"+dipartimento);
			}
			
			stm.close();
			rs.close();
			
			System.out.println("\n"+"AFTER INSERT"+"\n");
			
			
			stm=con.createStatement();
			stm.executeUpdate("insert into impiegato(matricola,cognome,stipendio,dipartimento) values('420','moretti','101','NO')");
			
			stm.close();

			stm=con.createStatement();
			rs=stm.executeQuery("select * from impiegato");
			while(rs.next()){
				matricola=rs.getInt(1);
				cognome=rs.getString(2);
				stipendio=rs.getInt(3);
				dipartimento=rs.getString(4);
				System.out.println(matricola+"-"+cognome+"-"+stipendio+"-"+dipartimento);
			}
			stm.close();
			rs.close();
			
			
			/*
			Statement stm4=con.createStatement();
			stm4.executeUpdate("create table JDBC(prova int)");
			stm4.close();
			*/
			
			PreparedStatement ps=con.prepareStatement("insert into JDBC(prova) values( a=? )");
			ps.setInt(1, 1);
			ps.execute();
			
			ps.close();
			
			stm=con.createStatement();
			rs=stm.executeQuery("select * from JDBC");
			rs.next();
			System.out.println("prova= "+rs.getInt(1));
			
			stm.close();
			rs.close();
		   
			
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
