package org.agjin.eclipser.actions;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.agjin.eclipser.views.EclipserView3;
import org.eclipse.jface.action.Action;

public class RemoveEclipsersAction extends Action {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EclipserLogger logger = new EclipserLogger(RemoveEclipsersAction.class, Level.CONFIG);
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="removeAction:org.agjin.eclipser.views.EclipserView3"
	 */
	private EclipserView3 view;
	
	public RemoveEclipsersAction(EclipserView3 view, String text) {
		super(text);
		this.view = view;
		logger.debug("RemoverEclipsersAction ---- text : [{}]", text);
	}
	
	public void run() {
		logger.debug("run ---- ");
		EclipsersManager.getManager().removeEclipsers(view.getSelectedEclipsers());
	}
}
