package org.agjin.eclipser.views;

import java.util.logging.Level;

import org.agjin.eclipser.actions.CopyEclipsersAction;
import org.agjin.eclipser.logger.EclipserLogger;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.ResourceTransfer;

public class EclipsersDragSource implements DragSourceListener {

	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  readOnly="true"
	 */
	EclipserLogger logger = new EclipserLogger(EclipsersDragSource.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  readOnly="true"
	 */
	private EclipserView3 view;
	
	public EclipsersDragSource(EclipserView3 view, TableViewer viewer) {
		logger.debug("EclipsersDragSource ---- ");
		
		this.view = view;
		 
		DragSource source = new DragSource(viewer.getControl(), DND.DROP_COPY);
		 source.setTransfer(
				 new Transfer[] {
						 TextTransfer.getInstance(),
						 ResourceTransfer.getInstance()});
		 source.addDragListener(this);
	}
	
	@Override
	public void dragStart(DragSourceEvent event) {
		event.doit = view.getSelectedEclipsers().length > 0;
		
		logger.debug("dragStart ---- event.doit : [{}]", event.doit);
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		logger.debug("dragSetData ---- event.detail : [{}]", event.detail);
		
		if (TextTransfer.getInstance().isSupportedType(event.dataType))
			event.data = CopyEclipsersAction.asText(view.getSelectedEclipsers());
		else if (ResourceTransfer.getInstance().isSupportedType(event.dataType))
			event.data =  CopyEclipsersAction.asResources(view.getSelectedEclipsers());
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		// ?´ë™ ?¤í¼?ˆì´?˜ì´?¼ë©´ ?´ê? ?¤ìŒ ?ë˜ ??ª©???œê±°?œë‹¤.
	}

}
