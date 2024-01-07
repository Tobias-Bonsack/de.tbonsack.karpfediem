package de.tbonsack.karpfediem.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.services.nls.Translation;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.tbonsack.karpfediem.translation.i18n.Messages;

public class PerspectivePart {

	private TableViewer _tableViewer;

	@PostConstruct
	public void postConstruct(Composite parent, MApplication app, //
			EPartService partService, EModelService modelService, @Translation Messages messages) {

		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(parent);
		List<MPerspective> perspectives = modelService.findElements(app, null, MPerspective.class, null);

		createTableViewer(parent, perspectives, partService, messages);
	}

	private void createTableViewer(Composite parent, List<MPerspective> perspectives, //
			EPartService partService, Messages messages) {
		
		_tableViewer = new TableViewer(parent, SWT.None);
		_tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		Table table = _tableViewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);

		var column = new TableViewerColumn(_tableViewer, SWT.NONE);
		column.getColumn().setText(messages.perspectiveColumnLabel);
		column.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				var perspective = (MPerspective) element;
				return perspective.getLabel();
			}
		});

		// update size of the only column
		table.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				column.getColumn().setWidth(table.getClientArea().width);
			}
		});

		_tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				MPerspective firstElement = (MPerspective) selection.getFirstElement();
				partService.switchPerspective(firstElement);
			}
		});

		_tableViewer.setInput(perspectives.toArray());
	}
}
