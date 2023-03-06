package es.studium.PrimerEjemploBD;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimerEjemploBD implements WindowListener
{
	Frame ventana = new Frame("Primer Ejemplo BD");
	Label lblID = new Label("idEmpleado");
	Label lblNombre = new Label("Nombre");
	Label lblSalario = new Label("Salario");
	TextField txtID = new TextField(15);
	TextField txtNombre = new TextField(15);
	TextField txtSalario = new TextField(15);
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados"; 
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	PrimerEjemploBD()
	{	
		ventana.setSize(250,150);
		ventana.setSize(250,150);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblID);
		ventana.add(txtID);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblSalario);
		ventana.add(txtSalario);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			txtID.setText(rs.getString("idEmpleado"));
			txtNombre.setText(rs.getString("nombreEmpleado"));
			txtSalario.setText(rs.getString("salarioEmpleado") + " €");
			
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		finally
		{
			try
			{
				if(connection!=null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error 3-" + e.getMessage());
			}
		}
		ventana.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new PrimerEjemploBD();
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	public void windowOpened(WindowEvent e){}


	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}


}
