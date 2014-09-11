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
	 * 사전초기화
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// 실행될 각 테스트에 대한 테스트 준비물을 초기화한다.
		waitForJobs();
		testView = (EclipserView)
			PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.showView(VIEW_ID);
		
		// 3초간 여유를 줘서 Favorties 뷰가 개발자에게 보이도록 한다.
		waitForJobs();
		delay(3000);
	}
	
	/**
	 * 완료 후 뒤처리를 수행한다.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// 테스트 설비 폐기
		waitForJobs();
		PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.hideView(testView);
	}
	
	/**
	 * 뷰 테스트 실행
	
	public void testView() {
		TableViewer viewer = testView.getFaTableViewer();
		Object[] expectedContent = 
			new Object[]{"One", "Two", "Three"};
		Object[] expectedLabels =
			new Object[]{"One", "Two", "Three"};
		
		// 올바른 컨테츠임을 확인
		IStructuredContentProvider contentProvider =
			(IStructuredContentProvider)
				viewer.getContentProvider();
		
		assertEquals(expectedContent,
			contentProvider.getElements(viewer.getInput()));
		
		// 올바른 레이블임을 확인
		ITableLabelProvider labelProvider =
			(ITableLabelProvider) viewer.getLabelProvider();
		
		for (int i=0, size=expectedLabels.length; i<size; i++) {
			assertEquals(expectedLabels[i],
				labelProvider.getColumnText(expectedContent[i], 1));
		}
	} */
	
	/**
	 * 백그라운드 작업이 끝날 때까지 대기
	 */
	@SuppressWarnings("deprecation")
	public void waitForJobs() {
		while(Platform.getJobManager().currentJob()!=null) {
			delay(1000);
		}
	}
	
	/**
	 * 프로세스 UI 입력을 받지만 지정된 시간 간격 동안은 반환하지 않는다.
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
	 * 두배열이 같은지 검사한다.
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
