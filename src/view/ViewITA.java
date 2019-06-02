package view;

import java.util.HashMap;

public class ViewITA implements IView {

	@Override
	public String type() { return "ITA"; }
	
	HashMap<String,String> sentences = new HashMap<String,String>();
	
	public ViewITA() {
		sentences.put("", "");
	}

	@Override
	public String translate(String code) {
		return sentences.containsKey(code) ? sentences.get(code) : code;
	}
}
