package org.agjin.eclipser;

import org.agjin.eclipser.actions.AddToEclipserActionDelegate;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.junit.Test;

public class AddToEclipserTest extends AbstractEclipser {
	
	protected IProject project;
	
	public AddToEclipserTest(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		project = root.getProject("TestProj");
		project.create(null);
		project.open(null);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// 시스템이 실행 후 따라올 수 있도록 잠시 기다리면
		// 삭제 오퍼레이션이 다른 백그라운드 작업과 충돌하지 않는다.
		delay(3000);
		waitForJobs();
		
		project.delete(true, true, null);
	}
	
	@Test
	public void testOpenEclipserView() throws CoreException {
		
		IViewPart navigator = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.ResourceNavigator");
		StructuredSelection selection = new StructuredSelection(project);
		((ISetSelectionTarget)navigator).selectReveal(selection);
		
		// 액션을 실행한다.
		final IObjectActionDelegate delegate =  new AddToEclipserActionDelegate();
		IAction action = new Action("Test Add to Eclipser"){
			public void run() {
				delegate.run(this);
			}
		};
		
		delegate.setActivePart(action, navigator);
		delegate.selectionChanged(action, selection);
		action.run();
		
		// 나중에 Add to Eclipse 액션이 적절한 값을 
		// Eclipser 뷰에 제대로 추가했는지 검증하는 코드를 여기에 추가한다.
	}
	
}
