package es.studium.EjemploProgramaGestion;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements ActionListener, WindowListener
{
	Frame menuPrincipal = new Frame("Menú Principal");
	MenuBar barraMenu = new MenuBar();
	Menu mnuUsuarios = new Menu("Usuarios");
	Menu mnuElementos = new Menu("Elementos");
	Menu mnuIncidencias = new Menu("Incidencias");
	MenuItem mniUsuariosNuevo = new MenuItem("Nuevo");
	MenuItem mniUsuariosListado = new MenuItem("Listado");
	MenuItem mniUsuariosBaja = new MenuItem("Baja");
	MenuItem mniUsuariosModificar = new MenuItem("Modificar");
	MenuItem mniElementosNuevo = new MenuItem("Nuevo");
	MenuItem mniElementosListado = new MenuItem("Listado");
	MenuItem mniElementosBaja = new MenuItem("Baja");
	MenuItem mniElementosModificar = new MenuItem("Modificar");
	
	MenuPrincipal()
	{
		menuPrincipal.setLayout(new FlowLayout());
		menuPrincipal.setMenuBar(barraMenu);
		mnuUsuarios.add(mniUsuariosNuevo);
		mnuUsuarios.add(mniUsuariosListado);
		mnuUsuarios.add(mniUsuariosBaja);
		mnuUsuarios.add(mniUsuariosModificar);
		mnuElementos.add(mniElementosNuevo);
		mnuElementos.add(mniElementosListado);
		mnuElementos.add(mniElementosBaja);
		mnuElementos.add(mniElementosModificar);
		barraMenu.add(mnuUsuarios);
		barraMenu.add(mnuElementos);
		barraMenu.add(mnuIncidencias);
		menuPrincipal.setSize(350, 200);
		mniUsuariosNuevo.addActionListener(this);
		mniUsuariosListado.addActionListener(this);
		mniUsuariosBaja.addActionListener(this);
		mniUsuariosModificar.addActionListener(this);
		mniElementosNuevo.addActionListener(this);
		mniElementosListado.addActionListener(this);
		mniElementosBaja.addActionListener(this);
		mniElementosModificar.addActionListener(this);
		menuPrincipal.addWindowListener(this);
		menuPrincipal.setResizable(false);
		menuPrincipal.setLocationRelativeTo(null);
		menuPrincipal.setVisible(true);
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}

	public void actionPerformed(ActionEvent e)
	{
		// Nuevo Usuario
		if(e.getSource().equals(mniUsuariosNuevo))
		{
			new NuevoUsuario();
		}
		// Listado
		else if(e.getSource().equals(mniUsuariosListado))
		{
			new ListadoUsuario();
		}
		// Baja
		else if(e.getSource().equals(mniUsuariosBaja))
		{
			new BajaUsuario();
		}
		// Modificar
		else if(e.getSource().equals(mniUsuariosModificar))
		{
			new ModificarUsuario();
		}
	}
}
