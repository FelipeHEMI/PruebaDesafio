package cl.desafiolatam.sistemaclientes.servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import cl.desafiolatam.sistemaclientes.modelo.Cliente;

public class ExportadorTxt extends Exportador {

	public void exportar(String fileName, List<Cliente> listaClientes) {

		try {
			File fichero = new File("src/main/java/datos/" + fileName + ".txt");
			fichero.createNewFile();

			FileWriter fw = new FileWriter(fichero);
			BufferedWriter bw = new BufferedWriter(fw);

			for (Cliente cliente : listaClientes) {
				bw.write(cliente.toString());
				bw.newLine();
			}

			System.out.println("Datos de clientes exportados correctamente en formato txt.\n");

			bw.close();

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

}
