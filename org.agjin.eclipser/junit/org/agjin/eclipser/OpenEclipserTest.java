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
		
		// 뷰가 닫혀있게 한다.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = page.findView(VIEW_ID);
		
		if (view!=null) {
			page.hideView(view);
		}
		
		// 뷰가 3초뒤에 보여지도록
		waitForJobs();
	}
	
	@Test
	public void testOpenEclipserView() {
		
		// 오퍼레이션을 실행한다.
		(new Action("OpenEclipserViewTest") {
			public void run() {
				IWorkbenchWindowActionDelegate delegate = new OpenEclipserViewActionDelegate();
				delegate.init(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
				delegate.selectionChanged(this, StructuredSelection.EMPTY);
				delegate.run(this);
			}
		}).run();
		
		// 오퍼레이션이 성공적으로 완료되었는지 테스트한다.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		assertTrue(page.findView(VIEW_ID)!=null);
	}
}
