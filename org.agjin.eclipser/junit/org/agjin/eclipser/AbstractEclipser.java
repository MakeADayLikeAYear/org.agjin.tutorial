package org.agjin.eclipser;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.agjin.eclipser.views.EclipserView;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractEclipser extends TestCase {
	public static final String VIEW_ID = "org.agjin.eclipser.views.EclipserView";
	
	private EclipserView testView;
	
	public AbstractEclipser(String name) {
		super(name);
	}
	
	/**
	 * �����ʱ�ȭ
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// ����� �� �׽�Ʈ�� ���� �׽�Ʈ �غ��� �ʱ�ȭ�Ѵ�.
		waitForJobs();
		testView = (EclipserView)
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.showView(VIEW_ID);
		
		// 3�ʰ� ������ �༭ Favorties �䰡 �����ڿ��� ���̵��� �Ѵ�.
		waitForJobs();
		delay(3000);
	}
	
	/**
	 * �Ϸ� �� ��ó���� �����Ѵ�.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// �׽�Ʈ ���� ���
		waitForJobs();
		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.hideView(testView);
	}
	
	/**
	 * �� �׽�Ʈ ����
	
	public void testView() {
		TableViewer viewer = testView.getFaTableViewer();
		Object[] expectedContent = 
			new Object[]{"One", "Two", "Three"};
		Object[] expectedLabels =
			new Object[]{"One", "Two", "Three"};
		
		// �ùٸ� ���������� Ȯ��
		IStructuredContentProvider contentProvider =
			(IStructuredContentProvider)
				viewer.getContentProvider();
		
		assertEquals(expectedContent,
			contentProvider.getElements(viewer.getInput()));
		
		// �ùٸ� ���̺����� Ȯ��
		ITableLabelProvider labelProvider =
			(ITableLabelProvider) viewer.getLabelProvider();
		
		for (int i=0, size=expectedLabels.length; i<size; i++) {
			assertEquals(expectedLabels[i],
				labelProvider.getColumnText(expectedContent[i], 1));
		}
	} */
	
	/**
	 * ��׶��� �۾��� ���� ������ ���
	 */
	@SuppressWarnings("deprecation")
	public void waitForJobs() {
		while(Platform.getJobManager().currentJob()!=null) {
			delay(1000);
		}
	}
	
	/**
	 * ���μ��� UI �Է��� ������ ������ �ð� ���� ������ ��ȯ���� �ʴ´�.
	 * @param waitTimeMillis
	 */
	public void delay(long waitTimeMillis) {
		Display display = Display.getCurrent();
		
		if (display!=null) {
			long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			display.update();
		} else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	/**
	 * �ι迭�� ������ �˻��Ѵ�.
	 * @param expected
	 * @param actual
	 */
	public void assertEquals(Object[] expected, Object[] actual) {
		if (expected==null) {
			if (actual==null) {
				return;
			} else {
				throw new AssertionFailedError(
					"expected is null, but actual is not");
			}
		} else {
			if (actual==null) {
				throw new AssertionFailedError(
					"expected is not, but actual is null");
			}
		}
		
		assertEquals(
			"expected.length "
				+ expected.length
				+ " but actual.length "
				+ actual.length,
			expected.length,
			actual.length);
		
		for (int i=0; i<actual.length; i++) {
			assertEquals(
				"expected[" + i +
					"] is not equals to actual[" +
					i + "]",
				expected[i],
				actual[i]);
		}
	}

}
