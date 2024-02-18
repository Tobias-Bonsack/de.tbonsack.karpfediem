package de.tbonsack.karpfediem.pokemon.cardmanager.ui.dialog;

import javax.swing.JFileChooser;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.widgets.ButtonFactory;
import org.eclipse.jface.widgets.LabelFactory;
import org.eclipse.jface.widgets.TextFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.tbonsack.karpfediem.pokemon.cardmanager.translation.i18n.Messages;

public class SetCreateDialog extends Dialog {

	private String _imgPath = "";

	private Messages _messages;

	private String _name;

	public SetCreateDialog(Shell parentShell, Messages messages) {
		super(parentShell);
		_messages = messages;
	}

	@Override
	protected Control createDialogArea(Composite com) {
		Composite parent = (Composite) super.createDialogArea(com);
		GridLayoutFactory.fillDefaults()
				.numColumns(2)
				.applyTo(parent);

		createNamePart(parent);
		createImagePart(parent);

		return parent;
	}

	private void createImagePart(Composite parent) {
		LabelFactory.newLabel(SWT.None)//
				.layoutData(GridDataFactory.fillDefaults()
						.create())//
				.text(_messages.imgPath)//
				.create(parent);

		ButtonFactory.newButton(SWT.PUSH)
				.layoutData(GridDataFactory.fillDefaults()
						.create())
				.text("file chooser")
				.onSelect((event) -> {
					var fileDialog = new JFileChooser();
					int showDialog = fileDialog.showDialog(null, "Choose");

					if (showDialog == JFileChooser.APPROVE_OPTION) {
						_imgPath = fileDialog.getSelectedFile()
								.getAbsolutePath()
								.toString();
					}
				})
				.create(parent);
	}

	private void createNamePart(Composite parent) {
		LabelFactory.newLabel(SWT.None)//
				.layoutData(GridDataFactory.fillDefaults()
						.create())//
				.text(_messages.name)
				.create(parent);

		ModifyListener modifyListener = (modifyEvent) -> {
			String name = ((Text) modifyEvent.widget).getText();
			getButton(IDialogConstants.OK_ID).setEnabled(!name.isBlank());
			_name = name;
		};

		TextFactory.newText(SWT.None)//
				.layoutData(GridDataFactory.fillDefaults()
						.grab(false, true)
						.create())//
				.onModify(modifyListener)
				.create(parent);
	}

	public String getImgPath() {
		return _imgPath;
	}

	public String getName() {
		return _name;
	}
}
