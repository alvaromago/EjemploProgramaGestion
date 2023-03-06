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

public class NuevoUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Nuevo Usuario");
	Label lblAlta = new Label("Alta de Usuario");
	Label lblNombre = new Label("Nombre: ");
	Label lblClave = new Label("Clave: ");
	Label lblClave2 = new Label("Repetir clave: ");
	Label lblCorreo = new Label("Correo: ");
	TextField txtNombre = new TextField(15);
	TextField txtClave = new TextField(15);
	TextField txtClave2 = new TextField(15);
	TextField txtCorreo = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Dialog dlgAviso = new Dialog(ventana, "Aviso", true);
	Label lblMensaje = new Label("ALTA correcta");
	Conexion conexion = new Conexion();
	
	NuevoUsuario()
	{
		ventana.setSize(175, 340);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblAlta);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblClave);
		ventana.add(txtClave);
		txtClave.setEchoChar('*');
		ventana.add(lblClave2);
		ventana.add(txtClave2);
		txtClave2.setEchoChar('*');
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		ventana.setResizable(true);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		dlgAviso.setSize(150, 100);
		dlgAviso.addWindowListener(this);
		dlgAviso.setLayout(new FlowLayout());
		dlgAviso.add(lblMensaje);
		dlgAviso.setLocationRelativeTo(null);
		dlgAviso.setResizable(false);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAceptar))
		{
			dlgAviso.setSize(200, 100);
			dlgAviso.addWindowListener(this);
			dlgAviso.setLayout(new FlowLayout());
			dlgAviso.setLocationRelativeTo(null);
			dlgAviso.setResizable(false);
			if(txtNombre.getText().length()==0||txtClave.getText().length()==0||txtClave2.getText().length()==0||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Hay campos vacíos");
			}
			//Comprobar las claves
			else if(!txtClave.getText().equals(txtClave2.getText()))
			{
				lblMensaje.setText("Las claves no coinciden");
			}
			else
			{				
				//Dar de alta
				String sentencia = "insert into usuarios values (null, '"+txtNombre.getText()+"', SHA2('"+txtClave.getText()+"',256), '"+txtCorreo.getText()+"');";
				int respuesta = conexion.altaUsuario(sentencia);
				if(respuesta!=0)
				{
					//Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				}
			}
			dlgAviso.add(lblMensaje);
			dlgAviso.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtClave.setText("");
			txtClave2.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		if(dlgAviso.isActive())
		{
			dlgAviso.setVisible(false);
		}
		else
		{			
			ventana.setVisible(false);
		}
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
