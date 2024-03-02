package de.tbonsack.karpfediem.pokemon.cardmanager.ui.parts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardSetEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.Card;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardService;
import de.tbonsack.karpfediem.pokemon.cardmanager.translation.i18n.Messages;
import de.tbonsack.karpfediem.utils.image.service.ImageLoaderService;

public class CardTableViewerPart {

	private static class LazyContentProvider implements ILazyContentProvider {
		private Card[] elements;
		private TableViewer viewer;

		public LazyContentProvider(TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			this.elements = (Card[]) newInput;
		}

		@Override
		public void updateElement(int index) {
			viewer.replace(elements[index], index);
		}

	}

	@Inject
	IEventBroker _broker;

	@Inject
	CardService _cardService;

	private CardSet _cardset;

	private CellLabelProvider _idLabelProvider = new ColumnLabelProvider() {
		@Override
		public String getText(Object element) {
			Card card = (Card) element;
			return String.valueOf(card.getNumber());
		}
	};

	private ColumnLabelProvider _imageLabelProvider = new ColumnLabelProvider() {
		@Override
		public Image getImage(Object element) {
			Card card = (Card) element;
			ImageDescriptor imageDescriptor = _imageLoader.getImageDescriptor(card.getImgPath(), true);
			Image image = imageDescriptor.createImage();
			Image resize = _imageLoader.resize(image, 50, 50);
			image.dispose();
			return resize;
		}

		@Override
		public String getText(Object element) {
			return "";
		}
	};

	@Inject
	ImageLoaderService _imageLoader;

	private List<Image> _images = new ArrayList<>();

	private ColumnLabelProvider _nameLabelProvider = new ColumnLabelProvider() {
		@Override
		public String getText(Object element) {
			Card card = (Card) element;
			return card.getName();
		}
	};

	private MPart _part;

	private TableViewer _tableViewer;

	private void createTable(TableViewer viewer, Messages messages) {
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridDataFactory.fillDefaults()
				.grab(true, true)
				.applyTo(table);

		//create icon column
		var iconTVC = new TableViewerColumn(viewer, SWT.None);
		var iconTC = iconTVC.getColumn();
		iconTC.setText(messages.icon);
		iconTC.setWidth(55);
		iconTVC.setLabelProvider(_imageLabelProvider);

		//create number column
		var idTVC = new TableViewerColumn(viewer, SWT.None);
		var idTC = idTVC.getColumn();
		idTC.setText(messages.number);
		idTC.setWidth(55);
		idTVC.setLabelProvider(_idLabelProvider);

		//create Name column
		var nameTVC = new TableViewerColumn(viewer, SWT.None);
		var nameTC = nameTVC.getColumn();
		nameTC.setText(messages.name);
		nameTC.setWidth(200);
		nameTVC.setLabelProvider(_nameLabelProvider);

	}

	private TableViewer createTableViewer(Composite parent, Messages messages) {
		var viewer = new TableViewer(parent, SWT.VIRTUAL);
		viewer.setContentProvider(new LazyContentProvider(viewer));
		viewer.setUseHashlookup(true);
		createTable(viewer, messages);

		viewer.addSelectionChangedListener((event) -> {
			Card card = (Card) event.getStructuredSelection()
					.getFirstElement();
			_broker.post(CardEvents.card_select, card);
		});
		return viewer;
	}

	@PostConstruct
	public void postConstruct(MPart part, Composite parent, @Translation Messages messages) {
		_part = part;
		GridLayoutFactory.fillDefaults()
				.numColumns(1)
				.applyTo(parent);

		_tableViewer = createTableViewer(parent, messages);
	}

	@Inject
	@Optional
	private void selectSetEvent(@UIEventTopic(CardSetEvents.cardset_select) CardSet cardset) {
		if (cardset == null || cardset.equals(_cardset))
			return;
		_tableViewer.setInput(new Card[0]);
		_tableViewer.setItemCount(0);

		_cardset = cardset;
		_images.stream()
				.forEach(image -> image.dispose());
		_images.clear();

		Collection<Card> cards2 = _cardService.getCards(cardset);
		Card[] cards = cards2.toArray(new Card[cards2.size()]);
		_tableViewer.setInput(cards);
		_tableViewer.setItemCount(cards.length);
	}
}
