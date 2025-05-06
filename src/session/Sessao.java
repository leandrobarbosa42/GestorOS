package session;

import model.Usuario;
//Controle de sessão(Usuário logado)
public class Sessao {
	private static Usuario usuarioLogado;

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		Sessao.usuarioLogado = usuarioLogado;
	} 
	
	public static void limpar() {
	     usuarioLogado = null;
	 }
}
