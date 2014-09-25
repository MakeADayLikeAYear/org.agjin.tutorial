package org.agjin.eclipser.actions;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.views.EclipsersViewNameFilter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Shell;

public class EclipserViewFilterAction extends Action {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EclipserLogger logger = new EclipserLogger(EclipserViewFilterAction.class, Level.CONFIG);
	/**
	 * @uml.property  name="shell"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Shell shell;
	/**
	 * @uml.property  name="nameFilter"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final EclipsersViewNameFilter nameFilter;
	
	public EclipserViewFilterAction(StructuredViewer viewer, String text) {
		super(text);
		this.shell = viewer.getControl().getShell();
		this.nameFilter = new EclipsersViewNameFilter(viewer);
		
		logger.debug("EclipserViewFilterAction ---- text : [{}]", text);
	}
	
	public void run() {
		logger.debug("run ---- ");
		
		InputDialog dialog = new InputDialog (
				shell,
				"Eclipsers View Filter",
				"Enter a name filter pattern (* = any String, ? = any character)"
					+ System.getProperty("line.separator")
					+" or and empty string for no filtering : ",
				nameFilter.getPattern(),
				null);
		
		if (dialog.open() == InputDialog.OK) {
			nameFilter.setPattern(dialog.getValue().trim());
		}
	}
}
