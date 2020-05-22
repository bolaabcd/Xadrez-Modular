package com.XadrezCompleto.plugins;

import com.XadrezCompleto.padroes.TabuleiroInfo;
import com.XadrezCompleto.padroes.Transferidor;

public class Apresentador implements Transferidor{

	@Override
	public void apresentar(TabuleiroInfo arg0) {
		System.out.println(arg0.pecas);
	}

	@Override
	public int[] waitNextPos() {
		return new int[] {0,0};
	}

}
