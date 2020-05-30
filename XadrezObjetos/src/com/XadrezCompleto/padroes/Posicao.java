package com.XadrezCompleto.padroes;

public class Posicao {
	private final int[] pos=new int[2];
	public Posicao(int x,int y) {
		pos[0]=x;
		pos[1]=y;
	}
	public int[] getPos() {
		return pos;
	}
}
