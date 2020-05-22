package com.XadrezCompleto.padroes;

import java.util.ArrayList;

public class TabuleiroInfo {
	public PecaCor proximoJogador;
	public ArrayList<String> mensagensErro;
	public ArrayList<String> mensagensAviso;

	public final Peca[][] pecas = new Peca[8][8];
	public final CorFundo[][] quadradosFundo = new CorFundo[8][8];
}
