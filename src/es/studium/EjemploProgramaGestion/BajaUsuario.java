package es.studium.EjemploProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja de Usuarios");
	Label lblElegir = new Label("Elegir el usuario a Eliminar");
	Choice choUsu = new Choice();
	Dialog dlgSeguro = new Dialog(ventana, "¿Estás seguro?", true);
	Label lblSeguro = new Label();
	Button btnSi = new Button("SI");
	Button btnNo = new Button("NO");
	Button btnEli = new Button("Eliminar");
	Conexion conexion = new Conexion();
	Dialog dlgMensaje = new Dialog(dlgSeguro, "Mensaje", true);
	Label lblMensaje = new Label();
	
	BajaUsuario()
	{
		ventana.setSize(180, 140);
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);
		ventana.add(lblElegir);
		conexion.rellenarChoiceUsuarios(choUsu);
		ventana.add(choUsu);
		btnEli.addActionListener(this);
		ventana.add(btnEli);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnEli))
		{
			if(choUsu.getSelectedIndex() !=0)
			{				
				dlgSeguro.setSize(250, 115);
				dlgSeguro.addWindowListener(this);
				dlgSeguro.setLayout(new FlowLayout());
				lblSeguro.setText("¿Estás seguro de Eliminar a " + choUsu.getSelectedItem() + "?");
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this);
				dlgSeguro.add(btnSi);
				btnNo.addActionListener(this);
				dlgSeguro.add(btnNo);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setResizable(false);
				dlgSeguro.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
		else if(e.getSource().equals(btnSi))
		{
			String tabla[] = choUsu.getSelectedItem().split("-");
			int respuesta = conexion.eliminarUsuario(tabla[0]);
			dlgMensaje.setSize(275, 75);
			dlgMensaje.addWindowListener(this);
			dlgMensaje.setLayout(new FlowLayout());
			
			if(respuesta==0)
			{
				lblMensaje.setText("El usuario ha sido eliminado correctamente");
			}
			else
			{
				lblMensaje.setText("Ha ocurrido un error");
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setResizable(false);
			dlgMensaje.setVisible(true);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
			dlgSeguro.setVisible(false);
			ventana.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
	}
	
	public void windowOpened(WindowEvent e){}
	
	public void windowClosed(WindowEvent e){}
	
	public void windowIconified(WindowEvent e){}
	
	public void windowDeiconified(WindowEvent e){}
	
	public void windowActivated(WindowEvent e){}
	
	public void windowDeactivated(WindowEvent e){}

}
