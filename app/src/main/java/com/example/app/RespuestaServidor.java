package com.example.app;

interface RespuestaServidor {

	/**
	 * Invoked when background task is completed
	 */

	public abstract void hecho(Usuario UsuarioDevuelto, DietaSemana dietaSemanaDevuelta, Integer insertado);

}
