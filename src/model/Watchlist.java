package model;

import java.util.ArrayList;
import java.util.List;

public class Watchlist {
	ArrayList<ProdottoBean> items;
	
	public Watchlist() {
		items = new ArrayList<ProdottoBean>();
	}
	
	public void addItem(ProdottoBean item) {
		items.add(item);
	}
	
	public void deleteItem(String nome) {
		items.removeIf(i -> i.getNome().equals(nome));
	}

	public List<ProdottoBean> getItems() {
		return items;
	}
	
	public void clearWatchlist() {
		items.clear();
	}

	@Override
	public String toString() {
		return "Watchlist [items=" + items + "]";
	}
	
	
}
