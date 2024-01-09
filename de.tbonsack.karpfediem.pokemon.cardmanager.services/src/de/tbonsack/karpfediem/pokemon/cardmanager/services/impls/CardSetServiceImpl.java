package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;

public class CardSetServiceImpl implements CardSetService {

	@Inject
	private IEventBroker _broker;

	public CardSetServiceImpl() {

	}

	@Override
	public CardSet getCardSet(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
