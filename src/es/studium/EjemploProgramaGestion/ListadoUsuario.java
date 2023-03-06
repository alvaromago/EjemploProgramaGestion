package es.studium.EjemploProgramaGestion;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListadoUsuario implements WindowListener
{
	Frame ventana = new Frame("Listado de Usuarios");
	Button btnPDF = new Button("PDF");
	TextArea txaUsuar = new TextArea(6, 30);
	Conexion conexion = new Conexion();
	
	ListadoUsuario()
	{
		ventana.setSize(260, 200);
		ventana.setLayout(new FlowLayout());
		conexion.rellenarListadoUsuario(txaUsuar);
		ventana.addWindowListener(this);
		ventana.add(txaUsuar);
		ventana.add(btnPDF);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		if(ventana.isActive())
		{
			ventana.setVisible(false);
		}
		else
		{
			System.exit(0);
		}
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
