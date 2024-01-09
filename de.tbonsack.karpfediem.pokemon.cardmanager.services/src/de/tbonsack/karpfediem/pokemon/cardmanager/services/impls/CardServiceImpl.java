package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;

public class CardServiceImpl implements CardService {

	@Inject
	private IEventBroker _broker;

	public CardServiceImpl() {

	}

	@Override
	public Card getCard(int number, CardSet set) {
		// TODO Auto-generated method stub
		return null;
	}

}
