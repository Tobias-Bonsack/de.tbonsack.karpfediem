package de.tbonsack.karpfediem.pokemon.cardmanager.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
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

import de.tbonsack.karpfediem.pokemon.cardmanager.events.CardSetEvents;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.objects.CardSet;
import de.tbonsack.karpfediem.pokemon.cardmanager.model.services.CardSetService;
import de.tbonsack.karpfediem.pokemon.cardmanager.translation.i18n.Messages;

public class CardSetTableViewerPart {

	@Inject
	private IEventBroker _broker;

	private MPart _part;

	@Inject
	private CardSetService _setService;

	private TableViewer _tableViewer;

	private void createTable(TableViewer viewer, Messages messages) {
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridDataFactory.fillDefaults()
				.grab(true, true)
				.applyTo(table);

		// create column for sets
		var column = new TableViewerColumn(viewer, SWT.None);
		column.getColumn()
				.setText(messages.name);
		column.getColumn()
				.setWidth(100);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var set = (CardSet) element;
				return set.getName();
			}
		});

		// update size of the only column
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				column.getColumn()
						.setWidth(table.getClientArea().width);
			}
		});

	}

	private TableViewer createTableViewer(Composite parent, Messages messages) {
		TableViewer viewer = new TableViewer(parent, SWT.None);
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		createTable(viewer, messages);

		viewer.addSelectionChangedListener((event) -> {
			CardSet element = (CardSet) event.getStructuredSelection()
					.getFirstElement();
			_broker.post(CardSetEvents.cardset_select, element);
		});
		viewer.setInput(_setService.getAllSets()
				.toArray());

		return viewer;
	}

	@Inject
	@Optional
	private void deleteCardSetEvent(@UIEventTopic(CardSetEvents.cardset_delete) CardSet cardSet) {
		updateCardSetInput(cardSet);
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
	private void updateCardSetEvent(@UIEventTopic(CardSetEvents.cardset_update) CardSet cardSet) {
		updateCardSetInput(cardSet);
	}

	private void updateCardSetInput(CardSet cardSet) {
		if (cardSet == null)
			return;
		_tableViewer.setInput(_setService.getAllSets());
		_part.setDirty(true);
	}

}
