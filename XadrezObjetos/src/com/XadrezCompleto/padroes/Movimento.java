package com.XadrezCompleto.padroes;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Movimento{
	private class Mov{
		private class Tab2Pos{
			private TabuleiroInfo tab;
			private Posicao de;
			private Posicao para;
		}
		private Stream<Posicao> pos;
		private Function<Tab2Pos,TabuleiroInfo> mover;
	}
	private Mov[] movs;
	private Movimento(Mov[] movs) {
		this.movs=movs;
	}
	private Stream<Posicao> next(){
		return null;
	}
	private Mov nextMov() {
		return null;
	}
	private TabuleiroInfo moverAtual(TabuleiroInfo tinfo,Posicao de, Posicao para) {
		return null;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	private static final UnaryOperator<Posicao>[] diagonais=new UnaryOperator[] {
		getPadrao(1,1),
		getPadrao(1,-1),
		getPadrao(-1,-1),
		getPadrao(-1,1),
	};
	@SuppressWarnings("unchecked")
	private static final UnaryOperator<Posicao>[] retos=new UnaryOperator[] {
		getPadrao(0,1),
		getPadrao(1,0),
		getPadrao(0,-1),
		getPadrao(-1,0)
	};
	@SuppressWarnings("unchecked")
	private static final UnaryOperator<Posicao>[] cavaL=new UnaryOperator[] {
		getPadrao(1,2),
		getPadrao(2,1),
		getPadrao(2,-1),
		getPadrao(1,-2),
		getPadrao(-1,-2),
		getPadrao(-2,-1),
		getPadrao(-2,1),
		getPadrao(-1,2),
	};
	
	private static final UnaryOperator<Posicao> getPadrao( int vertical,int horizontal){
		return (Posicao anterior)->{
			return new Posicao(anterior.getPos()[0]+horizontal, anterior.getPos()[1]+vertical);
		};
	}
	@SuppressWarnings("unchecked")
	private static final Stream<Posicao>[] getMovimento(Peca peca,Posicao inicial) {
		if(peca.tipo==PecaTipo.BISPO)
			return new Stream[] {
					Stream.iterate(inicial, diagonais[0]),
					Stream.iterate(inicial, diagonais[1]),
					Stream.iterate(inicial, diagonais[2]),
					Stream.iterate(inicial, diagonais[3]),
			};
		else if(peca.tipo==PecaTipo.TORRE)
			return new Stream[] {
					Stream.iterate(inicial, retos[0]),
					Stream.iterate(inicial, retos[1]),
					Stream.iterate(inicial, retos[2]),
					Stream.iterate(inicial, retos[3]),
			};
		else if(peca.tipo==PecaTipo.CAVALO)
			return new Stream[] {
					Stream.iterate(inicial, cavaL[0]).limit(2),
					Stream.iterate(inicial, cavaL[1]).limit(2),
					Stream.iterate(inicial, cavaL[2]).limit(2),
					Stream.iterate(inicial, cavaL[3]).limit(2),
			};
		else if(peca.tipo==PecaTipo.RAINHA)
			return new Stream[] {
					Stream.iterate(inicial, diagonais[0]),
					Stream.iterate(inicial, diagonais[1]),
					Stream.iterate(inicial, diagonais[2]),
					Stream.iterate(inicial, diagonais[3]),
					Stream.iterate(inicial, retos[0]),
					Stream.iterate(inicial, retos[1]),
					Stream.iterate(inicial, retos[2]),
					Stream.iterate(inicial, retos[3]),
			};
		else if(peca.tipo==PecaTipo.REI)
			return new Stream[] {
					Stream.iterate(inicial, diagonais[0]).limit(2),
					Stream.iterate(inicial, diagonais[1]).limit(2),
					Stream.iterate(inicial, diagonais[2]).limit(2),
					Stream.iterate(inicial, diagonais[3]).limit(2),
					Stream.iterate(inicial, retos[0]).limit(2),
					Stream.iterate(inicial, retos[1]).limit(2),
					Stream.iterate(inicial, retos[2]).limit(2),
					Stream.iterate(inicial, retos[3]).limit(2),
			};
		else if(peca.tipo==PecaTipo.PEAO)
			if(peca.cor==PecaCor.BRANCO)
				return new Stream[] {
						Stream.iterate(inicial, diagonais[0]).limit(2),
						Stream.iterate(inicial, diagonais[3]).limit(2),
						Stream.iterate(inicial, retos[0]).limit(3),
				};
			else if(peca.cor==PecaCor.PRETO)
				return new Stream[] {
						Stream.iterate(inicial, diagonais[1]).limit(2),
						Stream.iterate(inicial, diagonais[2]).limit(2),
						Stream.iterate(inicial, retos[2]).limit(3),
				};
			else
				throw new ChessException("Cor de peça inválida!");
		else 
			throw new ChessException("Tipo de peça inválida!");
		
	}
}
