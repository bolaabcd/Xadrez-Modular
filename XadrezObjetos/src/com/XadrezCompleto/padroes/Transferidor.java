package com.XadrezCompleto.padroes;

public interface Transferidor {
	public void apresentar(TabuleiroInfo inf);
	public Posicao waitNextPos ()throws ChessInvalidStateException;
	public void fechar();
}
