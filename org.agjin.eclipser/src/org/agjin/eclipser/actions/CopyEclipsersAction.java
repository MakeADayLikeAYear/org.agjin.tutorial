package org.agjin.eclipser.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.IEclipserItem;
import org.agjin.eclipser.views.EclipserView3;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.ResourceTransfer;

public class CopyEclipsersAction extends Action {
	
	static EclipserLogger logger = new EclipserLogger(CopyEclipsersAction.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  readOnly="true" inverse="copyAction:org.agjin.eclipser.views.EclipserView3"
	 */
	private EclipserView3 view;
	
	public CopyEclipsersAction(EclipserView3 view, String text) {
		super(text);
		this.view = view;
		
		logger.debug("CopyEclipsersAction ---- text : [{}]", text);
	} 
	
	public void run() {
		logger.debug("run ---- ");
		
		IEclipserItem[] items = view.getSelectedEclipsers();
		if (items.length ==  0)
			return;
		try {
			view.getClipboard().setContents(
				new Object[]{
						asResources(items),
						asText(items), },
				new Transfer[] {
						ResourceTransfer.getInstance(),
						TextTransfer.getInstance(), });
		}
		catch (SWTError error) {
			// ?´ë¦½ë³´ë“œ??ë³µì‚¬ ?¤íŒ¨
			// ???ˆì™¸??ë³µì‚¬ë¥??˜ëŠ” ?™ì•ˆ ?¤ë¥¸ ? í”Œë¦¬ì??´ì…˜?ì„œ ?´ë¦½ë³´ë“œë¥??‘ê·¼?˜ë ¤ê³?????ë°œìƒ?œë‹¤.
			// ê·¸ëƒ¥ ë¬´ì‹œ?œë‹¤?
		}
	}
	
	public static IResource[] asResources(IEclipserItem[] items) {
		logger.debug("asResources ---- ");
		
		List<IResource> resources = new ArrayList<IResource>();
		for (int i=0, size=items.length; i<size; i++) {
			IResource res = (IResource)items[i].getAdapter(IResource.class);
			if (res != null) 
				resources.add(res);
		}
		
		return resources.toArray(new IResource[resources.size()]);
	}
	
	public static String asText(IEclipserItem[] items) {
		logger.debug("asText ---- ");
		
		StringBuffer buf = new StringBuffer();
		for (int i=0, size=items.length; i<size; i++) {
			if (i > 0)
				buf.append(System.getProperty("line.separartor"));
			buf.append(items[i].getName());
		}
		
		return buf.toString();
	}
	
}
