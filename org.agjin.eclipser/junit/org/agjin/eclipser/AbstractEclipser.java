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
	 * ?¬ì „ì´ˆê¸°??
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// ?¤í–‰??ê°??ŒìŠ¤?¸ì— ??•œ ?ŒìŠ¤??ì¤?¹„ë¬¼ì„ ì´ˆê¸°?”í•œ??
		waitForJobs();
		testView = (EclipserView)
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.showView(VIEW_ID);
		
		// 3ì´ˆê°„ ?¬ìœ ë¥?ì¤˜ì„œ Favorties ë·°ê? ê°œë°œ?ì—ê²?ë³´ì´?„ë¡ ?œë‹¤.
		waitForJobs();
		delay(3000);
	}
	
	/**
	 * ?„ë£Œ ???¤ì²˜ë¦¬ë? ?˜í–‰?œë‹¤.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// ?ŒìŠ¤???¤ë¹„ ?ê¸°
		waitForJobs();
		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.hideView(testView);
	}
	
	/**
	 * ë·??ŒìŠ¤???¤í–‰
	
	public void testView() {
		TableViewer viewer = testView.getFaTableViewer();
		Object[] expectedContent = 
			new Object[]{"One", "Two", "Three"};
		Object[] expectedLabels =
			new Object[]{"One", "Two", "Three"};
		
		// ?¬ë°”ë¥?ì»¨í…Œì¸ ì„???•ì¸
		IStructuredContentProvider contentProvider =
			(IStructuredContentProvider)
				viewer.getContentProvider();
		
		assertEquals(expectedContent,
			contentProvider.getElements(viewer.getInput()));
		
		// ?¬ë°”ë¥??ˆì´ë¸”ì„???•ì¸
		ITableLabelProvider labelProvider =
			(ITableLabelProvider) viewer.getLabelProvider();
		
		for (int i=0, size=expectedLabels.length; i<size; i++) {
			assertEquals(expectedLabels[i],
				labelProvider.getColumnText(expectedContent[i], 1));
		}
	} */
	
	/**
	 * ë°±ê·¸?¼ìš´???‘ì—…???ë‚  ?Œê¹Œì§???¸°
	 */
	@SuppressWarnings("deprecation")
	public void waitForJobs() {
		while(Platform.getJobManager().currentJob()!=null) {
			delay(1000);
		}
	}
	
	/**
	 * ?„ë¡œ?¸ìŠ¤ UI ?…ë ¥??ë°›ì?ë§?ì§? •???œê°„ ê°„ê²© ?™ì•ˆ??ë°˜í™˜?˜ì? ?ŠëŠ”??
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
	 * ?ë°°?´ì´ ê°™ì?ì§?ê²?‚¬?œë‹¤.
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
