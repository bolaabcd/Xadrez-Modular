package com.XadrezCompleto.padroes;

import java.util.ArrayList;

public class TabuleiroInfo {
	public PecaCor proximoJogador;
	
	public final ArrayList<String> mensagensErro=new ArrayList<String>();
	public final ArrayList<String> mensagensAviso=new ArrayList<String>();
	public final Peca[][] pecas = new Peca[8][8];
	public final CorFundo[][] quadradosFundo = new CorFundo[8][8];
	public final ArrayList<Posicao> historicoMovimentos=new ArrayList<Posicao>();
	public final ArrayList<Peca> pecasComidas=new ArrayList<Peca>();
	
	public static final TabuleiroInfo getDefault() {
		TabuleiroInfo inicial=new TabuleiroInfo();
		inicial.pecas[0][0]=new Peca(PecaTipo.TORRE, PecaCor.BRANCO);
		inicial.pecas[0][7]=new Peca(PecaTipo.TORRE, PecaCor.BRANCO);
		inicial.pecas[7][0]=new Peca(PecaTipo.TORRE, PecaCor.PRETO);
		inicial.pecas[7][7]=new Peca(PecaTipo.TORRE, PecaCor.PRETO);
		
		inicial.pecas[0][1]=new Peca(PecaTipo.CAVALO, PecaCor.BRANCO);
		inicial.pecas[0][6]=new Peca(PecaTipo.CAVALO, PecaCor.BRANCO);
		inicial.pecas[7][1]=new Peca(PecaTipo.CAVALO, PecaCor.PRETO);
		inicial.pecas[7][6]=new Peca(PecaTipo.CAVALO, PecaCor.PRETO);
		
		inicial.pecas[0][2]=new Peca(PecaTipo.BISPO, PecaCor.BRANCO);
		inicial.pecas[0][5]=new Peca(PecaTipo.BISPO, PecaCor.BRANCO);
		inicial.pecas[7][2]=new Peca(PecaTipo.BISPO, PecaCor.PRETO);
		inicial.pecas[7][5]=new Peca(PecaTipo.BISPO, PecaCor.PRETO);
		
		inicial.pecas[0][3]=new Peca(PecaTipo.REI, PecaCor.BRANCO);
		inicial.pecas[0][4]=new Peca(PecaTipo.RAINHA, PecaCor.BRANCO);
		inicial.pecas[7][3]=new Peca(PecaTipo.REI, PecaCor.PRETO);
		inicial.pecas[7][4]=new Peca(PecaTipo.RAINHA, PecaCor.PRETO);
		
		for(int i=0;i<8;i++) {
			inicial.pecas[1][i]=new Peca(PecaTipo.PEAO, PecaCor.BRANCO);
			inicial.pecas[6][i]=new Peca(PecaTipo.PEAO, PecaCor.PRETO);
		}
		
		for(int i=0;i<inicial.quadradosFundo.length;i++)
			for(int j=0;j<inicial.quadradosFundo[i].length;j++)
				if((i+j)%2==0)
					inicial.quadradosFundo[i][j]=CorFundo.BRANCO;
				else
					inicial.quadradosFundo[i][j]=CorFundo.PRETO;
		
		return inicial;
		
	}
}
