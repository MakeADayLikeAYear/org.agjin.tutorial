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
		
		// �ý����� ���� �� ����� �� �ֵ��� ��� ��ٸ���
		// ���� ���۷��̼��� �ٸ� ��׶��� �۾��� �浹���� �ʴ´�.
		delay(3000);
		waitForJobs();
		
		project.delete(true, true, null);
	}
	
	@Test
	public void testOpenEclipserView() throws CoreException {
		
		IViewPart navigator = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.ResourceNavigator");
		StructuredSelection selection = new StructuredSelection(project);
		((ISetSelectionTarget)navigator).selectReveal(selection);
		
		// �׼��� �����Ѵ�.
		final IObjectActionDelegate delegate =  new AddToEclipserActionDelegate();
		IAction action = new Action("Test Add to Eclipser"){
			public void run() {
				delegate.run(this);
			}
		};
		
		delegate.setActivePart(action, navigator);
		delegate.selectionChanged(action, selection);
		action.run();
		
		// ���߿� Add to Eclipse �׼��� ������ ���� 
		// Eclipser �信 ����� �߰��ߴ��� �����ϴ� �ڵ带 ���⿡ �߰��Ѵ�.
	}
	
}
