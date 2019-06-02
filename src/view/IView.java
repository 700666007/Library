package view;

import utils.Factory;

public interface IView {

	String type();
	String translate(String code);
	
	static String translateLang(String code, String lang) {
		return Factory.makeView(lang).translate(code);
	}
	static String translateLog(String code) {
		return translateLang(code,"ENG");
	}
}
