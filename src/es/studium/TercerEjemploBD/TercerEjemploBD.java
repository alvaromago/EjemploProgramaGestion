package es.studium.TercerEjemploBD;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TercerEjemploBD implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Tercer Ejemplo BD");
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "SELECT * FROM empleados";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	TextArea txtArea = new TextArea(10,30);
	Button btnVolver = new Button("Volver");
	
	TercerEjemploBD()
	{
		ventana.setSize(300,270);
		ventana.addWindowListener(this);
		ventana.setLayout(new FlowLayout());
		ventana.add(txtArea);
		ventana.add(btnVolver);
		btnVolver.addActionListener(this);
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
			while(rs.next())
			{				
				txtArea.setText((txtArea.getText() + "\n" + rs.getString("idEmpleado") + " - " + rs.getString("nombreEmpleado") + " - " + rs.getString("salarioEmpleado")));
			}
			
			
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
		new TercerEjemploBD();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnVolver))
		{
			System.exit(0);
		}
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
