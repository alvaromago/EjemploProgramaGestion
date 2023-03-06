package es.studium.EjemploProgramaGestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/EjemploProgramaGestion";
	String login = "userProgramaGestion";
	String password = "Studium2023;";
	String sentencia = "";
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Conexion()
	{
		connection = this.conectar();
	}
	
	public Connection conectar()
	{
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			return (DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		return null;
	}
	
	public boolean comprobarCredenciales(String u, String c)
	{
		String cadena = "select * from usuarios where nombreUsuario = '"+ u + "' and contraseniaUsuario = SHA2('"+ c + "',256);";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			rs = statement.executeQuery(cadena);
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		return false;
	}

	public int altaUsuario(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0; 
		}
		catch (SQLException sqle)
		{	
			System.out.println("Error 4-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoUsuario(TextArea txaUsuar)
	{
		String sentencia = "select idUsuario, nombreUsuario, correoUsuario from usuarios;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaUsuar.append(resultado.getString("idUsuario") + "   ");
				txaUsuar.append(resultado.getString("nombreUsuario") + "   ");
				txaUsuar.append(resultado.getString("correoUsuario") + "\n");
			}
		}
		catch (SQLException sqle)
		{	
			System.out.println("Error 5-" + sqle.getMessage());
		}
	}

	public void rellenarChoiceUsuarios(Choice choUsu)
	{
		String sentencia = "select idUsuario, nombreUsuario from usuarios;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			choUsu.add("Elegir usuario...");
			while(resultado.next())
			{
				choUsu.add(resultado.getString("idUsuario") + "-" + resultado.getString("nombreUsuario"));
			}
		}
		catch (SQLException sqle)
		{	
			System.out.println("Error 6-" + sqle.getMessage());
		}
	}

	public int eliminarUsuario(String idUsuario)
	{
		String sentencia = "delete from usuarios where idUsuario= " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{	
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}

	public String getCamposEditar(String idUsuario)
	{
		String resultado = "";
		String sentencia = "select * from usuarios where idUsuario = " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			ResultSet resultSet= statement.executeQuery(sentencia);
			resultSet.next();
			resultado = (resultSet.getString("idUsuario") + "-" + resultSet.getString("nombreUsuario") + "-" + resultSet.getString("contraseniaUsuario") + "-" + resultSet.getString("correoUsuario"));
			
		}
		catch (SQLException sqle)
		{	
			System.out.println("Error 8-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarUsuario(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-" + sqle.getMessage());
			return 1;
		}
	}
}
