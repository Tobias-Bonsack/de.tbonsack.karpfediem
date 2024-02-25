package de.tbonsack.karpfediem.pokemon.cardmanager.ui;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.utils.gson.service.SaveService;

public class TestPart {

	@Inject
	CardService _cardService;

	@Inject
	SaveService _saveService;

	@Inject
	CardSetService _setService;

	private TableViewer _tableViewer;

	private void createTestData() {
		Platform.getLog(getClass()).warn("Create TestData start");

		String dir = Platform.getInstanceLocation().getURL().getPath().substring(1);

		CardSet cardSet = _setService.createCardSet();
		cardSet.setName("Base Set");
		_setService.saveCardSet(cardSet);

		dir = dir + "pokemon/cardmanager/res/base-set";
		Path path = Paths.get(dir);
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(path);
			for (Path imagePath : stream) {
				var fileName = imagePath.getFileName().toString().replace(".jpg", "").replace("-base-set-bs-", "-_-")
						.split("-_-");
				Card card = _cardService.createCard();
				card.setName(fileName[0]);
				card.setNumber(Integer.valueOf(fileName[1]));
				card.setImgPath(imagePath.toAbsolutePath().toString());

				cardSet.addCard(card.getId());
				card.addToSet(cardSet.getId());

				_cardService.save(card);
			}
		} catch (Exception e) {
		}

		Platform.getLog(getClass()).warn("Create TestData end");
	}

	@PostConstruct
	public void test(Composite parent) {
		GridLayoutFactory.fillDefaults().applyTo(parent);

//		createTestData();

		_tableViewer = new TableViewer(parent, SWT.None);
		_tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		Table table = _tableViewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);

		var setColumn = new TableViewerColumn(_tableViewer, SWT.None);
		var cardColumn = new TableViewerColumn(_tableViewer, SWT.None);

		setColumn.getColumn().setText("Sets");
		cardColumn.getColumn().setText("Cards");

		setColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var set = (CardSet) element;
				return set.getName();
			}
		});
		cardColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var set = (CardSet) element;
				Collection<Card> cards = _cardService.getCards(set);
				return cards.stream().map(Card::getName).reduce((a, b) -> a + " - " + b).orElse("No Cards");
			}
		});

		// update size of the only column
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				setColumn.getColumn().setWidth(table.getClientArea().width / 2);
				cardColumn.getColumn().setWidth(table.getClientArea().width / 2);
			}
		});

		_tableViewer.setInput(_setService.getAllSets().toArray());
	}
}
