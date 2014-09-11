package org.agjin.eclipser;

import org.agjin.eclipser.actions.OpenEclipserViewActionDelegate;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

public class OpenEclipserTest extends AbstractEclipser {
	
	public OpenEclipserTest(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// �䰡 �����ְ� �Ѵ�.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = page.findView(VIEW_ID);
		
		if (view!=null) {
			page.hideView(view);
		}
		
		// �䰡 3�ʵڿ� ����������
		waitForJobs();
	}
	
	@Test
	public void testOpenEclipserView() {
		
		// ���۷��̼��� �����Ѵ�.
		(new Action("OpenEclipserViewTest") {
			public void run() {
				IWorkbenchWindowActionDelegate delegate = new OpenEclipserViewActionDelegate();
				delegate.init(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
				delegate.selectionChanged(this, StructuredSelection.EMPTY);
				delegate.run(this);
			}
		}).run();
		
		// ���۷��̼��� ���������� �Ϸ�Ǿ����� �׽�Ʈ�Ѵ�.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		assertTrue(page.findView(VIEW_ID)!=null);
	}
}
