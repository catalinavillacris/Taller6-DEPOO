package test;

public class IngredienteRepetidoException extends HamburguesaException {
	
	public IngredienteRepetidoException () {
		super("Este ingrediente ya ha sido agregado");
	}

}
