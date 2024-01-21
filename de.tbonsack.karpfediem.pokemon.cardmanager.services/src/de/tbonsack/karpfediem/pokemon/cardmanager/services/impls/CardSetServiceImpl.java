package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;

public class CardSetServiceImpl implements CardSetService {

	@Inject
	private static IEventBroker BROKER;

	@Inject
	private static PreferenceService PERF_SERVICE;

	private Map<Integer, CardSet> _sets = new HashMap<>();

	@Override
	public CardSet createCardSet() {
		return new CardSet(PERF_SERVICE.getNextID(), "", "");
	}

	@Override
	public List<CardSet> getAllSets() {
		return _sets.values().stream().map(i -> i.clone()).map(i -> (CardSet) i).collect(Collectors.toList());
	}

	@Override
	public Optional<CardSet> getCardSet(int id) {
		CardSet cardSet = _sets.getOrDefault(id, null);
		return cardSet == null ? Optional.empty() : Optional.of((CardSet) cardSet.clone());
	}

	@Override
	public List<CardSet> getCardSets(String name) {
		return _sets.values().stream()//
				.filter(i -> name.equalsIgnoreCase(i.getName()))//
				.map(i -> (CardSet) i.clone())//
				.collect(Collectors.toList());
	}

	@Override
	public boolean saveCardSet(CardSet cardSet) {
		_sets.put(cardSet.getId(), cardSet);
		return true;
	}

}
