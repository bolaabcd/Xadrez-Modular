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
import com.XadrezCompleto.padroes.ChessInvalidStateException;
import com.XadrezCompleto.padroes.Posicao;
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
		
		TabuleiroInfo tab=TabuleiroInfo.getDefault();
		
		trans.apresentar(tab);
		Posicao newpos;
		
			while(true) {
				try {
					if((newpos=trans.waitNextPos())==null)break;
				} catch (ChessInvalidStateException e) {
					tab.mensagensErro.add(e.getMessage());
					newpos=null;//Indica que a entrada foi invalidada
				}
				//Interpretar os resultados aqui!
				trans.apresentar(tab);
			}
		
		trans.fechar();
	}
}
