package com.AppRH.AppRH;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan("com.AppRH.AppRH")
public class AppRhApplication {

	public static void main(String[] args)throws SQLException {
		SpringApplication.run(AppRhApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("kleber"));
		//abrirJrxml("01");
		//exportarParaPdf("01","C:\\Users\\Kleber\\Documents\\" + "jasper-" +UUID.randomUUID()+".pdf");
		//abrirPontoJasper("01");
		
	}

/*	private static void abrirPontoJasper(String numero) throws SQLException {
		Connection connection = JdbcConnection.connection();
		JasperService service= new JasperService();
		service.abrirPontoJasper("relatorios/jasper/Cooperados-"+numero+".jasper",connection);
		connection.close();
		
	}

	private static void exportarParaPdf(String numero, String saida) throws SQLException {
		Connection connection = JdbcConnection.connection();
		JasperService service= new JasperService();
		service.exportarParaPDF("relatorios/jrxml/Cooperados-"+numero+".jrxml",connection,saida);
		connection.close();	}

	private static void abrirJrxml(String numero) throws SQLException{
		Connection connection = JdbcConnection.connection();
		JasperService service= new JasperService();
		service.abrirJasperViewer("relatorios/jrxml/Cooperados-"+numero+".jrxml",connection);
		connection.close();
		
	}
*/
}
