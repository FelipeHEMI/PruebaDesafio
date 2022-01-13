package cl.desafiolatam.sistemaclientes.utilidades;

public class Utilidad {

	public void limpiarPantalla() {
		for (int i = 0; i < 100; i++)
			System.out.println("");
	}

	public void tiempoEspera() {
		try {
			System.out.println("Abandonando el sistema...");
			for (int i = 0; i < 2; i++) {
				Thread.sleep(1000);
			}
			System.out.println("Acaba de salir del Sistema");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
