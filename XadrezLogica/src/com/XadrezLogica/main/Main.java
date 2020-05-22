package com.XadrezLogica.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.XadrezCompleto.padroes.ArquivosGerais;
import com.XadrezCompleto.padroes.Peca;
import com.XadrezCompleto.padroes.PecaCor;
import com.XadrezCompleto.padroes.PecaTipo;
import com.XadrezCompleto.padroes.TabuleiroInfo;
import com.XadrezCompleto.padroes.Transferidor;

public class Main {
	private static final HashSet<String> chaves = new HashSet<String>(
			Arrays.asList(
					"Caminho Relativo:", 
					"Caminho do Plugin Apresentador:"
					)
			);
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		File config = new File("config.txt");
		HashMap<String, String[]> elementos = new HashMap<String, String[]>();
		if (!config.exists()) {
			System.out.println("Preparando Configurações Padrão!");
			config.createNewFile();
			elementos.put("Caminho Relativo:", new String[] { "True" });
			elementos.put("Caminho do Plugin Apresentador:", new String[] { "Presenter.jar" });
			ArquivosGerais.setArgs(config, elementos);
		}
		elementos = ArquivosGerais.getArgs(config, chaves);
		//File localPlugin = new File("//home/artur/Desktop/pogramando/Plugins/Plugin.jar");
		File localPlugin=new File(elementos.get("Caminho do Plugin Apresentador:")[0]);
		if(!localPlugin.exists())throw new IllegalStateException("Favor adicionar plugin apresentador!");
		URLClassLoader child = new URLClassLoader(
				new URL[] { new URL("jar", "", "file:" + localPlugin.getAbsolutePath() + "!/") }, Main.class.getClassLoader());

		Class<?> clazz = Class.forName("com.XadrezCompleto.plugins.Apresentador", true, child);
		Transferidor trans=(Transferidor)clazz.getConstructor().newInstance();
		
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
		
		inicial.pecas[0][3]=new Peca(PecaTipo.RAINHA, PecaCor.BRANCO);
		inicial.pecas[0][4]=new Peca(PecaTipo.REI, PecaCor.BRANCO);
		inicial.pecas[7][3]=new Peca(PecaTipo.RAINHA, PecaCor.PRETO);
		inicial.pecas[7][4]=new Peca(PecaTipo.REI, PecaCor.PRETO);
		
		for(int i=0;i<8;i++) {
			inicial.pecas[1][i]=new Peca(PecaTipo.PEAO, PecaCor.BRANCO);
			inicial.pecas[6][i]=new Peca(PecaTipo.PEAO, PecaCor.PRETO);
		}
		
		trans.apresentar(inicial);
		int[] newpos;
		while((newpos=trans.waitNextPos())!=null) {
			//Interpretar os resultados aqui!
			System.out.println("Pos: "+ newpos);
		}
	}
}
