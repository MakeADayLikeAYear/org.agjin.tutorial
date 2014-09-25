package org.agjin.eclipser;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.agjin.eclipser.views.EclipserView;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractEclipser extends TestCase {
	public static final String VIEW_ID = "org.agjin.eclipser.views.EclipserView";
	
	/**
	 * @uml.property  name="testView"
	 * @uml.associationEnd  
	 */
	private EclipserView testView;
	
	public AbstractEclipser(String name) {
		super(name);
	}
	
	/**
	 * ?�전초기??
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// ?�행??�??�스?�에 ??�� ?�스??�?��물을 초기?�한??
		waitForJobs();
		testView = (EclipserView)
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.showView(VIEW_ID);
		
		// 3초간 ?�유�?줘서 Favorties 뷰�? 개발?�에�?보이?�록 ?�다.
		waitForJobs();
		delay(3000);
	}
	
	/**
	 * ?�료 ???�처리�? ?�행?�다.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// ?�스???�비 ?�기
		waitForJobs();
		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.hideView(testView);
	}
	
	/**
	 * �??�스???�행
	
	public void testView() {
		TableViewer viewer = testView.getFaTableViewer();
		Object[] expectedContent = 
			new Object[]{"One", "Two", "Three"};
		Object[] expectedLabels =
			new Object[]{"One", "Two", "Three"};
		
		// ?�바�?컨테츠임???�인
		IStructuredContentProvider contentProvider =
			(IStructuredContentProvider)
				viewer.getContentProvider();
		
		assertEquals(expectedContent,
			contentProvider.getElements(viewer.getInput()));
		
		// ?�바�??�이블임???�인
		ITableLabelProvider labelProvider =
			(ITableLabelProvider) viewer.getLabelProvider();
		
		for (int i=0, size=expectedLabels.length; i<size; i++) {
			assertEquals(expectedLabels[i],
				labelProvider.getColumnText(expectedContent[i], 1));
		}
	} */
	
	/**
	 * 백그?�운???�업???�날 ?�까�???��
	 */
	@SuppressWarnings("deprecation")
	public void waitForJobs() {
		while(Platform.getJobManager().currentJob()!=null) {
			delay(1000);
		}
	}
	
	/**
	 * ?�로?�스 UI ?�력??받�?�?�?��???�간 간격 ?�안??반환?��? ?�는??
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
	 * ?�배?�이 같�?�?�?��?�다.
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
