package org.agjin.eclipser.actions;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.eclipse.jface.action.Action;

public class CutEclipsersAction extends Action {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EclipserLogger logger = new EclipserLogger(CutEclipsersAction.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="copyAction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private CopyEclipsersAction copyAction;
	/**
	 * @uml.property  name="removeAction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private RemoveEclipsersAction removeAction;
	
	public CutEclipsersAction(CopyEclipsersAction copyAction, RemoveEclipsersAction removeAction, String text) {
		super(text);
		this.copyAction = copyAction;
		this.removeAction = removeAction;
		
		logger.debug("CutEclipsersAction ---- text : [{}]", text);
	} 
	
	public void run() {
		logger.debug("run ---- ");
		copyAction.run();
		removeAction.run();
	}
	
}
