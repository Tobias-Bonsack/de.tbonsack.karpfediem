package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.widgets.Composite;

import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardSetEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
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

	@Inject
	@Optional
	private void selectCardSetEvent(@UIEventTopic(CardSetEvents.cardset_select) CardSet cardSet) {
		System.out.println("there is some event incomming");
	}

}
