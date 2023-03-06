package es.studium.EjemploProgramaGestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login implements WindowListener, ActionListener
{
	Frame ventanaLogin = new Frame("Login");
	Dialog dlgMensaje = new Dialog(ventanaLogin, "Error", true);
	Label lblUsuario = new Label("Usuario");
	Label lblClave = new Label("Clave:");
	Label lblMensaje = new Label("Credenciales incorrectas");
	TextField txtUsuario = new TextField(10);
	TextField txtClave = new TextField(10);
	Button btnAcceder = new Button("Acceder");
	Conexion conexion = new Conexion();
	
	Login()
	{
		ventanaLogin.setSize(200, 130);
		ventanaLogin.setLayout(new FlowLayout());
		ventanaLogin.addWindowListener(this);
		btnAcceder.addActionListener(this);
		ventanaLogin.add(lblUsuario);
		ventanaLogin.add(txtUsuario);
		ventanaLogin.add(lblClave);
		txtClave.setEchoChar('*');
		ventanaLogin.add(txtClave);
		ventanaLogin.add(btnAcceder);
		ventanaLogin.setLocationRelativeTo(null);
		ventanaLogin.setResizable(false);
		ventanaLogin.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Login();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAcceder))
		{
			String usuario = txtUsuario.getText();
			String clave = txtClave.getText();
			// Credenciales correctas
			if(conexion.comprobarCredenciales(usuario, clave))
			{
				MenuPrincipal menuPrincipal = new MenuPrincipal();
				ventanaLogin.setVisible(false);
			}
			// Credeanciales incorrectas
			else
			{
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(lblMensaje);
				dlgMensaje.setSize(200,70);
				dlgMensaje.setResizable(false);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{			
			System.exit(0);
		}
	}
	
	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
