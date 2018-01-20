package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.dao.VendasDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
//SERIALIZABLE SERVE PARA PEGAR O OBJETO COLOCAR NA MEM�RIA DEPOIS RECARREGAR O ESTADO DO OBJETO
public class VendasBean implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	@Inject
	private VendasDao vendasDao;
	
	//REPRESENTA OS DADOS DO GR�FICO
	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();

		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());

		}

		
		model.addSeries(vendaSerie);
		return model;
	}

	public List<Venda> getVendas() {


		return this.vendasDao.listaTodos();
	}

}
