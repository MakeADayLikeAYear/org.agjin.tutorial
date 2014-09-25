package org.agjin.eclipser.views;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.EclipsersManager;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.ResourceTransfer;


public class EclipsersDropTarget extends DropTargetAdapter {
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  readOnly="true"
	 */
	EclipserLogger logger = new EclipserLogger(EclipsersDropTarget.class, Level.CONFIG);
	
	public EclipsersDropTarget(EclipserView3 view, TableViewer viewer) {
		logger.debug("EclipsersDropTarget ---- ");
		
		DropTarget target = new DropTarget(
				viewer.getControl(),
				DND.DROP_MOVE |DND.DROP_COPY);
		target.setTransfer(
				new Transfer[] {
						ResourceTransfer.getInstance(),
						JavaUI.getJavaElementClipboardTransfer()});
		target.addDropListener(this);
	}
	
	public void dragEnter(DropTargetEvent event) {
		logger.debug("dragEnter ---- event.detail : [{}]", event.detail);
		
		if (event.detail == DND.DROP_MOVE
				|| event.detail == DND.DROP_DEFAULT) {
			if ((event.operations & DND.DROP_COPY) != 0)
				event.detail = DND.DROP_COPY;
			else
				event.detail = DND.DROP_NONE;
		}
	}
	
	public void drop(DropTargetEvent event) {
		logger.debug("drop ---- event.detail : [{}]", event.detail);
		
		EclipsersManager mgr = EclipsersManager.getManager();
		if (ResourceTransfer.getInstance().isSupportedType(event.currentDataType)
				&& (event.data instanceof IResource[])) {
			mgr.addEclipsers(mgr.newEclipsersFor((IResource[])event.data));
			event.detail = DND.DROP_COPY;
			
		} else if (JavaUI.getJavaElementClipboardTransfer().isSupportedType(event.currentDataType)
				&& (event.data instanceof IJavaElement[])) {
			mgr.addEclipsers(mgr.newEclipsersFor((IJavaElement[])event.data));
			event.detail = DND.DROP_COPY;
			
		} else
			event.detail = DND.DROP_NONE;
	}
}
