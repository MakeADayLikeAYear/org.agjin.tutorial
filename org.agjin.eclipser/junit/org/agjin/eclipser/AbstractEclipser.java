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
	 * ?ฌ์ ์ด๊ธฐ??
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// ?คํ??๊ฐ??์ค?ธ์ ?? ?์ค??์ค?น๋ฌผ์ ์ด๊ธฐ?ํ??
		waitForJobs();
		testView = (EclipserView)
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.showView(VIEW_ID);
		
		// 3์ด๊ฐ ?ฌ์ ๋ฅ?์ค์ Favorties ๋ทฐ๊? ๊ฐ๋ฐ?์๊ฒ?๋ณด์ด?๋ก ?๋ค.
		waitForJobs();
		delay(3000);
	}
	
	/**
	 * ?๋ฃ ???ค์ฒ๋ฆฌ๋? ?ํ?๋ค.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// ?์ค???ค๋น ?๊ธฐ
		waitForJobs();
		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.hideView(testView);
	}
	
	/**
	 * ๋ท??์ค???คํ
	
	public void testView() {
		TableViewer viewer = testView.getFaTableViewer();
		Object[] expectedContent = 
			new Object[]{"One", "Two", "Three"};
		Object[] expectedLabels =
			new Object[]{"One", "Two", "Three"};
		
		// ?ฌ๋ฐ๋ฅ?์ปจํ์ธ ์???์ธ
		IStructuredContentProvider contentProvider =
			(IStructuredContentProvider)
				viewer.getContentProvider();
		
		assertEquals(expectedContent,
			contentProvider.getElements(viewer.getInput()));
		
		// ?ฌ๋ฐ๋ฅ??์ด๋ธ์???์ธ
		ITableLabelProvider labelProvider =
			(ITableLabelProvider) viewer.getLabelProvider();
		
		for (int i=0, size=expectedLabels.length; i<size; i++) {
			assertEquals(expectedLabels[i],
				labelProvider.getColumnText(expectedContent[i], 1));
		}
	} */
	
	/**
	 * ๋ฐฑ๊ทธ?ผ์ด???์???๋  ?๊น์ง???ธฐ
	 */
	@SuppressWarnings("deprecation")
	public void waitForJobs() {
		while(Platform.getJobManager().currentJob()!=null) {
			delay(1000);
		}
	}
	
	/**
	 * ?๋ก?ธ์ค UI ?๋ ฅ??๋ฐ์?๋ง?์ง? ???๊ฐ ๊ฐ๊ฒฉ ?์??๋ฐํ?์? ?๋??
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
	 * ?๋ฐฐ?ด์ด ๊ฐ์?์ง?๊ฒ?ฌ?๋ค.
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
