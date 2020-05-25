package com.XadrezCompleto.plugins;

import java.util.Scanner;

import com.XadrezCompleto.padroes.ChessException;
import com.XadrezCompleto.padroes.CorFundo;
import com.XadrezCompleto.padroes.Peca;
import com.XadrezCompleto.padroes.PecaCor;
import com.XadrezCompleto.padroes.PecaTipo;
import com.XadrezCompleto.padroes.TabuleiroInfo;
import com.XadrezCompleto.padroes.Transferidor;

public class Apresentador implements Transferidor{
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	@Override
	public void apresentar(TabuleiroInfo tinfo) {
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		for (int i=0;i<tinfo.pecas.length;i++) {
			System.out.print((8-i)+" ");
			
			int pos1=tinfo.pecas.length-1-i;
			Peca[] pArr=tinfo.pecas[pos1];
			CorFundo[] cArr=tinfo.quadradosFundo[pos1];
			
			for (int j=0;j<pArr.length;j++) {
				
				int pos2=pArr.length-1-j;
				Peca p=pArr[pos2];
				CorFundo c=cArr[pos2];
				if(p!=null)
					System.out.print(corFundo(c)+pecaCor(p.cor)+" "+pecaToString(p.tipo)+" "+ANSI_RESET);
				else
					System.out.print(corFundo(c)+" "+" "+" "+ANSI_RESET);

			}
			System.out.print(" \n");
		}
		//System.out.println();
		System.out.println("   A  B  C  D  E  F  G  H  ");
	}
	
	private String corFundo(CorFundo c) {
		if(c==null)throw new ChessException("Cor de Fundo Inválida!");
		switch(c) {
		case BRANCO:
			return ANSI_WHITE_BACKGROUND;
		case PRETO:
			return ANSI_BLACK_BACKGROUND;
		case VERDE:
			return ANSI_GREEN_BACKGROUND;
		case VERMELHO:
			return ANSI_RED_BACKGROUND;
		default:
			throw new ChessException("Cor de Fundo Inválida!");
		}
	}
	
	private String pecaCor(PecaCor p) {
		if(p==null)throw new ChessException("Cor de Fundo Inválida!");
		switch(p) {
		case BRANCO:
			return ANSI_YELLOW;
		case PRETO:
			return ANSI_BLUE;
		default:
			throw new ChessException("Cor de peça Inválida!");
		}
	}
	
	private String pecaToString(PecaTipo t) {
		if(t==null)throw new ChessException("Cor de Fundo Inválida!");
		switch (t) {
		case TORRE:
			return "T";
		case BISPO:
			return "B";
		case CAVALO:
			return "H";
		case PEAO:
			return "P";
		case RAINHA:
			return "Q";
		case REI:
			return "K";
		default:
			throw new ChessException("Peca Inválida!");
		}
	}

	@Override
	public int[] waitNextPos() {
		Scanner sc=new Scanner(System.in);
		int[] res=new int[] {sc.nextInt(),sc.nextInt()};
		sc.close();
		return res;
	}

}
