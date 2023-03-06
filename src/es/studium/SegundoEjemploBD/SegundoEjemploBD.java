package es.studium.SegundoEjemploBD;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SegundoEjemploBD implements WindowListener, ActionListener
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
	Button btnAnterior = new Button("Anterior");
	Button btnSiguiente = new Button("Siguiente");
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	SegundoEjemploBD()
	{	
		ventana.setSize(250,170);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblID);
		ventana.add(txtID);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblSalario);
		ventana.add(txtSalario);
		btnAnterior.addActionListener(this);
		btnSiguiente.addActionListener(this);
		ventana.add(btnAnterior);
		ventana.add(btnSiguiente);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			txtID.setText(rs.getString("idEmpleado"));
			txtNombre.setText(rs.getString("nombreEmpleado"));
			txtSalario.setText(rs.getString("salarioEmpleado") + " €");
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-" + sqle.getMessage());
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		ventana.setVisible(true);
	}

	public static void main(String[] args)
	{
		new SegundoEjemploBD();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnSiguiente))
		{
			try
			{	
				if(!rs.isLast())
				{
					rs.next();
					txtID.setText(rs.getString("idEmpleado"));
					txtNombre.setText(rs.getString("nombreEmpleado"));
					txtSalario.setText(rs.getString("salarioEmpleado") + " €");
				}
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-" + sqle.getMessage());
			}
		}
		else if(e.getSource().equals(btnAnterior))
		{
			try
			{
				if(!rs.isFirst())
				{	
					rs.previous();
					txtID.setText(rs.getString("idEmpleado"));
					txtNombre.setText(rs.getString("nombreEmpleado"));
					txtSalario.setText(rs.getString("salarioEmpleado") + " €");
				}
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-" + sqle.getMessage());
			}
		}
	}

	public void windowClosing(WindowEvent ee)
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
		System.exit(0);
	}
	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}

}
