package cl.desafiolatam.sistemaclientes.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.desafiolatam.sistemaclientes.servicio.Exportador;
import cl.desafiolatam.sistemaclientes.modelo.CategoriaEnum;
import cl.desafiolatam.sistemaclientes.modelo.Cliente;

public class ArchivoServicio extends Exportador {
	private ClienteServicio cs;

	public ArchivoServicio(ClienteServicio cs) {
		this.cs = cs;
	}

	Cliente cliente;

	public void cargarDatos(String nombre) {
		File archivo = new File(nombre);
		if (!archivo.exists()) {
			System.out.println("El archivo no existe en la ruta especificada. Volverá al Menú.\n");
			return;
		}

		else {
			try {
				FileReader fr = new FileReader(archivo);
				BufferedReader br = new BufferedReader(fr);
				String linea = br.readLine();
				ArrayList<Cliente> clientesImportados = new ArrayList<Cliente>();
				CategoriaEnum actividad;

				while (linea != null) {
					linea.split(",");
					String[] datos = linea.split(",");
					if (datos[4].equals("Activo"))
						actividad = CategoriaEnum.ACTIVO;
					else
						actividad = CategoriaEnum.INACTIVO;

					cliente = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].substring(0, 1)),
							actividad);

					clientesImportados.add(cliente);
					linea = br.readLine();
				}
				cs.agregarCliente(clientesImportados);
				br.close();
				System.out.println("Datos cargados correctamente en la lista.\n");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Datos no pudieron ser cargados correctamente en la lista.\n");
			}
		}
	}

	public void exportar(String nombre, List<Cliente> lista) {
		Exportador exportador = null;
		if (nombre.endsWith("csv"))
			exportador = new ExportadorCsv();
		else
			exportador = new ExportadorTxt();
		exportador.exportar(nombre, lista);
	}
}
