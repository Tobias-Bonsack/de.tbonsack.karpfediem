package de.tbonsack.karpfediem.pokemon.cardmanager.services.impls;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardSetEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.PreferenceService;
import de.tbonsack.karpfediem.utils.gson.service.ISerializable;
import de.tbonsack.karpfediem.utils.gson.service.LoadService;
import de.tbonsack.karpfediem.utils.gson.service.SaveService;

public class CardSetServiceImpl implements CardSetService {

	private static Map<Integer, CardSet> _sets = new HashMap<>();

	@Inject
	private IEventBroker _broker;

	@Inject
	private LoadService _loader;

	@Inject
	private SaveService _saver;

	@Inject
	private PreferenceService PERF_SERVICE;

	@Override
	public CardSet createCardSet() {
		return new CardSet(PERF_SERVICE.getNextID(), "", "");
	}

	@Override
	public List<CardSet> getAllSets() {
		return _sets.values()
				.stream()
				.map(i -> i.clone())
				.map(i -> (CardSet) i)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CardSet> getCardSet(int id) {
		CardSet cardSet = _sets.getOrDefault(id, null);
		return cardSet == null ? Optional.empty() : Optional.of((CardSet) cardSet.clone());
	}

	@Override
	public List<CardSet> getCardSets(String name) {
		return _sets.values()
				.stream()//
				.filter(i -> name.equalsIgnoreCase(i.getName()))//
				.map(i -> (CardSet) i.clone())//
				.collect(Collectors.toList());
	}

	@Override
	public void init() {
		Collection<CardSet> cardSets = _loader.loadFromGsonArray(CardSet.PATH + CardSet.FILE, CardSet.class);
		cardSets.stream()
				.forEach(cs -> _sets.put(cs.getId(), cs));
	}

	@Override
	public boolean saveAllSets() {
		List<ISerializable> collect = _sets.values()
				.stream()
				.map(i -> (ISerializable) i)
				.collect(Collectors.toList());

		_saver.safeAsGson(collect, CardSet.class);
		return true;
	}

	@Override
	public boolean saveCardSet(CardSet cardSet) {
		_sets.put(cardSet.getId(), cardSet);
		_broker.post(CardSetEvents.cardset_update, cardSet);
		return true;
	}

}
