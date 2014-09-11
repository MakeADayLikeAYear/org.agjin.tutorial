package org.agjin.eclipser.actions;

import org.agjin.eclipser.views.EclipserView;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;


public class OpenEclipserViewActionDelegate implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window;
	public static final String ID = "org.agjin.eclipser.actions.OpenEclipserViewActionDelegate";
	
	@Override
	public void run(IAction action) {
		System.out.println("OpenEclipserViewActionDelegate run ~~~~~");
		
		// 활성화 페이지를 얻는다.
		if (window==null) {
			return;
		}
		
		IWorkbenchPage page = window.getActivePage();
		
		if (page==null) {
			return;
		}
		
		// EclipserView 열고 활성화한다.
		try {
			
			System.out.println(page.findView(EclipserView.ID));
			
			System.out.println("OpenEclipserViewActionDelegate run ~~~~~ open");
			page.showView(EclipserView.ID);
		} catch (PartInitException e) {
//			Favor
			System.out.println("Failed to open the EclipserView Error");
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

}
