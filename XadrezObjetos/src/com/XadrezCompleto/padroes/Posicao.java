package com.XadrezCompleto.padroes;

public class Posicao {
	private final int[] pos=new int[2];
	public Posicao(int x,int y) throws ChessInvalidStateException {
		if(x>8||x<1||y>8||y<1)throw new ChessInvalidStateException("Posição inválida!");
		pos[0]=x;
		pos[1]=y;
	}
	public int[] getPos() {
		return pos;
	}
}
