package cl.desafiolatam.sistemaclientes.vista;

import java.io.File;
import java.util.Scanner;

import cl.desafiolatam.sistemaclientes.modelo.CategoriaEnum;
import cl.desafiolatam.sistemaclientes.modelo.Cliente;
import cl.desafiolatam.sistemaclientes.servicio.ArchivoServicio;
import cl.desafiolatam.sistemaclientes.servicio.ClienteServicio;
import cl.desafiolatam.sistemaclientes.utilidades.Utilidad;

public class MetodoMenu {

	ClienteServicio cs = new ClienteServicio();
	ArchivoServicio as = new ArchivoServicio(cs);
	Scanner sc = new Scanner(System.in);
	String run, nombre, apellido;
	int anios, numero, num1, num2;
	Cliente cliente;
	String dato, ruta;

	public void listarClientes() {
		cs.listarClientes().forEach(clienteI -> System.out.println(clienteI));
	}

	public void agregarCliente() {
		System.out.println("-------------Crear Cliente-------------");
		System.out.println("Ingresa RUN del Cliente:");
		run = sc.nextLine();
		System.out.println("Ingresa Nombre del Cliente:");
		nombre = sc.nextLine();
		System.out.println("Ingresa Apellido del Cliente:");
		apellido = sc.nextLine();
		System.out.println("Ingresa años como Cliente:");
		anios = Integer.parseInt(sc.nextLine());
		System.out.println("---------------------------------------");
		cliente = new Cliente(run, nombre, apellido, anios, CategoriaEnum.ACTIVO);
		System.out.println(cliente);
		cs.agregarCliente(cliente);
	}

	public void editarCliente() {
		System.out.println("-------------Editar Cliente-------------");
		System.out.println("Seleccione qué desea hacer:");
		System.out.println("1.-Cambiar el estado del Cliente");
		System.out.println("2.-Editar los datos ingresados del Cliente");
		System.out.println("Ingrese opcion:");
		System.out.println("----------------------------------------");
		numero = Integer.parseInt(sc.nextLine());
		System.out.println("Ingrese RUN del Cliente a editar:");
		run = sc.nextLine();
		if (cs.buscarCliente(run)) {
			cliente = cs.getCliente(run);

			if (numero == 1) {
				System.out.println("-----Actualizando estado del Cliente----");
				System.out.println("El estado actual es:" + cliente.getNombreCategoria());
				System.out.println("1.-Si desea cambiar el estado del Cliente a Inactivo");
				System.out.println("2.-Si desea mantener el estado del cliente Activo");
				System.out.println("Ingrese opcion:");
				System.out.println("----------------------------------------");
				numero = Integer.parseInt(sc.nextLine());
				if (numero == 1)
					cliente.setNombreCategoria(CategoriaEnum.INACTIVO);
				else if (numero == 2)
					cliente.setNombreCategoria(CategoriaEnum.ACTIVO);
				else
					System.out.println("Parámetro ingresado no corresponde");

			} else if (numero == 2) {
				cs.mostrarCliente(run);
				System.out.println("Ingrese opcion a editar de los datos del cliente:");
				System.out.println("----------------------------------------");
				numero = Integer.parseInt(sc.nextLine());
				switch (numero) {
				case 1:
					System.out.println("Ingrese el nuevo run del cliente: ");
					dato = sc.nextLine();
					break;
				case 2:
					System.out.println("Ingrese el nuevo nombre del cliente: ");
					dato = sc.nextLine();
					break;
				case 3:
					System.out.println("Ingrese el nuevo apellido del cliente: ");
					dato = sc.nextLine();
					break;
				case 4:
					System.out.println("Ingrese los años del cliente: ");
					dato = (sc.nextLine());
					break;
				default:
					System.out.println("La opción ingresada no es correcta. Volverá al Menú.\n");
					break;
				}
				if (dato == null)
					System.out.println("No se ingresó el dato, por lo que no se creará el cliente.\n");
				else
					cs.editarCliente(run, numero, dato);
			}
		} else
			System.out.println("El run ingresado no es corresponde a un cliente.\n");
	}

	public void importarDatos() {
		System.out.println("---------Cargar Datos-----------");
		System.out.println("Elija el sistema operativo de su pc:");
		System.out.println("1.- Mac");
		System.out.println("2.- Windows");
		numero = Integer.parseInt(sc.nextLine());
		System.out.println("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");
		ruta = sc.nextLine();
		System.out.println("-----------------------------------------------");

		switch (numero) {
		case 1:
			ruta = "home/usuario/Desktop";
			break;
		case 2:
			ruta = "C://usuario/equipo/Desktop";
			break;
		default:
			System.out.println("Número ingresado incorrecto.");
			break;
		}
		File directorio = new File(ruta);
		if (directorio.exists())
			as.cargarDatos(ruta + "/DBClientes.csv");
		else
			System.out.println("El directorio especificado no existe.\n ");
	}

	public void exportarDatos() {
		System.out.println("---------Exportar Datos-----------");
		System.out.println("Elija el sistema operativo de su pc:");
		System.out.println("1.- Mac");
		System.out.println("2.- Windows");
		num1 = Integer.parseInt(sc.nextLine());
		System.out.println("Elija un tipo de datos a exportar:");
		System.out.println("1.- CSV");
		System.out.println("2.- TXT");
		System.out.println("-----------------------------------------------");
		num2 = Integer.parseInt(sc.nextLine());
		boolean rutaOk = false;
		boolean nombreOk = false;
		switch (num1) {
		case 1:
			ruta = "home/usuario/Desktop";
			rutaOk = true;
			break;
		case 2:
			ruta = "C://usuario/equipo/Desktop";
			rutaOk = true;
			break;
		default:
			System.out.println("Número ingresado incorrecto.");
			break;
		}
		switch (num2) {
		case 1:
			nombre = ruta + "Clientes.csv";
			nombreOk = true;
			break;
		case 2:
			nombre = ruta + "Clientes.txt";
			nombreOk = true;
			break;
		default:
			System.out.println("Número ingresado incorrecto.");
			break;
		}
		if (rutaOk && nombreOk)
			as.exportar(nombre, cs.getListaClientes());
		else {
			System.out.println("La ruta o tipo de archivo no fueron ingresados correctamente.");
			System.out.println("Los datos no serán exportados.\n ");
		}
	}

	public boolean salir() {
		Utilidad u = new Utilidad();
		u.limpiarPantalla();
		u.tiempoEspera();
		return true;
	}

}
