package org.agjin.eclipser.actions;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.agjin.eclipser.views.EclipserView3;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.ResourceTransfer;

public class PasteEclipsersAction extends Action {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  readOnly="true"
	 */
	EclipserLogger logger = new EclipserLogger(PasteEclipsersAction.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  readOnly="true" inverse="pasteAction:org.agjin.eclipser.views.EclipserView3"
	 */
	private EclipserView3 view;
	
	public PasteEclipsersAction(EclipserView3 view, String text) {
		super(text);
		this.view = view;
		
		logger.debug("PasteEclipsersAction ---- text : [{}]", text);
	} 
	
	public void run() {
		logger.debug("run ---- ");
		
		if (pasteResources())
			return;
		if (pasteJavaElements())
			return;
		// ?¤ë¥¸ ?„ì†¡ ? í˜•???¬ê¸°??ì¶”ê??œë‹¤.
	}
	
	private boolean pasteResources() {
		logger.debug("pasteResources ---- ");
		
		ResourceTransfer transfer =ResourceTransfer.getInstance();
		IResource[] resources = (IResource[])view.getClipboard().getContents(transfer);
		if (resources == null || resources.length == 0)
			return false;
		EclipsersManager mgr = EclipsersManager.getManager();
		mgr.addEclipsers(mgr.newEclipsersFor(resources));
		return true;
	}
	
	private boolean pasteJavaElements() {
		logger.debug("pasteJavaElements ---- ");
		
		Transfer transfer =JavaUI.getJavaElementClipboardTransfer();
		IJavaElement[] elements = (IJavaElement[])view.getClipboard().getContents(transfer);
		if (elements ==null || elements.length == 0)
			return false;
		EclipsersManager mgr = EclipsersManager.getManager();
		mgr.addEclipsers(mgr.newEclipsersFor(elements));
		return true;
	}
	
}
