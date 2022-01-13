package cl.desafiolatam.sistemaclientes.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cl.desafiolatam.sistemaclientes.modelo.Cliente;

public class ClienteServicio {

	List<Cliente> listaClientes = new ArrayList<Cliente>();

	public ClienteServicio() {
	}

	public ClienteServicio(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	@Override
	public String toString() {
		return "ClienteServicio [listaClientes=" + listaClientes + "]";
	}

	public void agregarCliente(Cliente cliente) {
		if (cliente != null)
			listaClientes.add(cliente);
		else
			System.out.println("El cliente no pudo ser ingresado. Trate nuevamente.\n");
	}

	public List<String> listarClientes() {
		List<String> lista = listaClientes.stream()
				.map(cliente -> "-------------Datos del Cliente-------------" + "\n     RUN del Cliente: "
						+ cliente.getRunCliente() + "\n     Nombre del Cliente: " + cliente.getNombreCliente()
						+ "\n     Apellido del Cliente: " + cliente.getApellidoCliente() + "\n     Años como Cliente: "
						+ cliente.getAniosCliente() + "\n     Categoría del Cliente: " + cliente.getNombreCategoria()
						+ "\n-------------------------------------------")
				.collect(Collectors.toList());
		return lista;
	}

	public void mostrarCliente(String run) {
		listaClientes.stream().filter(cliente -> cliente.getRunCliente().equals(run))
				.map(cliente -> "-------------Actualizando datos del Cliente-------------"
						+ "\n1.-El RUN del Cliente es: " + cliente.getRunCliente() + "\n2.-El Nombre del Cliente es: "
						+ cliente.getNombreCliente() + "\n3.-El Apellido del Cliente es: "
						+ cliente.getApellidoCliente() + "\n4.-Los años como Cliente son: " + cliente.getAniosCliente()
						+ "\n-------------------------------------------")
				.forEach(cliente -> System.out.println(cliente));
	}

	public boolean buscarCliente(String run) {
		boolean encontrado = false;
		for (Cliente cliente : listaClientes)
			if (cliente.getRunCliente().equals(run))
				encontrado = true;
		return encontrado;
	}

	public Cliente getCliente(String run) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRunCliente().equals(run))
				return cliente;
		}
		return null;
	}

	public void editarCliente(String run, int numero, String dato) {
		Cliente cliente = getCliente(run);
		switch (numero) {
		case 1:
			cliente.setRunCliente(dato);
			break;
		case 2:
			cliente.setNombreCliente(dato);
			break;
		case 3:
			cliente.setApellidoCliente(dato);
			break;
		case 4:
			cliente.setAniosCliente(Integer.parseInt(dato));
			break;
		default:
			System.out.println("La opción ingresada no es correcta. Volverá al Menú.\n");
			break;
		}
	}

	public void agregarCliente(ArrayList<Cliente> listaImportada) {
		listaClientes.addAll(listaImportada);
	}

}
