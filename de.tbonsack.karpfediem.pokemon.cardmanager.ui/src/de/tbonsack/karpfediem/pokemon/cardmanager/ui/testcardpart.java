package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.widgets.Composite;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class testcardpart {

	@Inject
	CardService _cardService;

	@Inject
	CardSetService _setService;

	@PostConstruct
	public void postConstruct(Composite parent) {

	}

}
