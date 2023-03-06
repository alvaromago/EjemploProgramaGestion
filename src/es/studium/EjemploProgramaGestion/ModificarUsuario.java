package es.studium.EjemploProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModificarUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Modificar Usuarios");
	Label lblElegir = new Label("  Elegir el usuario a modificar ");
	Choice choUsu = new Choice();
	Label lblEditar = new Label(" - Editor de Usuario -");
	Label lblNombre = new Label("Nombre:");
	Label lblClave = new Label("Clave:");
	Label lblClave2 = new Label("Repetir Clave:");
	Label lblCorreo = new Label("Correo:");
	Label lblMensaje = new Label("Modificación correcta");
	TextField txtNombre = new TextField(15);
	TextField txtClave = new TextField(15);
	TextField txtClave2 = new TextField(15);
	TextField txtCorreo = new TextField(15);
	Button btnMod = new Button("Editar");
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	Dialog dlgEditar = new Dialog(ventana, "Editar usuario", true);
	Dialog dlgMensaje = new Dialog(dlgEditar, "Mensaje", true);
	Conexion c = new Conexion();
	String idUsuario = "";
	
	ModificarUsuario()
	{
		ventana.setSize(180, 140);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		btnMod.addActionListener(this);
		ventana.add(lblElegir);
		c.rellenarChoiceUsuarios(choUsu);
		ventana.add(choUsu);
		ventana.add(btnMod);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnMod))
		{
			if(choUsu.getSelectedIndex() != 0)
			{
				dlgEditar.setSize(200, 330);
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.addWindowListener(this);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblEditar);
				dlgEditar.add(lblNombre);
				dlgEditar.add(txtNombre);
				dlgEditar.add(lblClave);
				txtClave.setEchoChar('*');
				dlgEditar.add(txtClave);
				dlgEditar.add(lblClave2);
				txtClave2.setEchoChar('*');
				dlgEditar.add(txtClave2);
				dlgEditar.add(lblCorreo);
				dlgEditar.add(txtCorreo);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choUsu.getSelectedItem().split("-");
				String resultado = c.getCamposEditar(tabla[0]);
				String datos [] = resultado.split("-");
				c.getCamposEditar(tabla[0]);
				txtNombre.setText(datos[1]);
				txtClave.setText("");
				txtClave2.setText("");
				txtCorreo.setText(datos[3]);
				idUsuario = tabla[0];
				dlgEditar.setVisible(true);
			}
		}
		if(e.getSource().equals(btnMod2))
		{
			dlgMensaje.setSize(200, 100);
			dlgMensaje.addWindowListener(this);
			dlgMensaje.setLayout(new FlowLayout());
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
				//Modificar
				String sentencia = "update usuarios set nombreUsuario = '"+txtNombre.getText()+"', contraseniaUsuario = SHA2('"+txtClave.getText()+"',256), correoUsuario = '"+txtCorreo.getText()+"' where idUsuario = " + idUsuario + ";";
				int respuesta = c.modificarUsuario(sentencia);
				if(respuesta!=0)
				{
					//Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setResizable(false);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			dlgEditar.setVisible(false);
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		if(dlgEditar.isActive())
		{
			dlgEditar.setVisible(false);
			ventana.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
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
