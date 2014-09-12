package org.agjin.eclipser.views;

import java.util.Comparator;

import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

public class EclipsersViewSorter extends ViewerSorter {
	
	// 열 기준으로 정렬 정보를 그룹짓는 간단한 데이터 구조체
	private class SortInfo {
//		int columnIndex;
		Comparator<IEclipserItem> comparator;
		boolean descending;
	}
	
	private TableViewer viewer;
	private SortInfo[] infos;
	
	/**
	 * sort 참고 --> http://www.java2s.com/Code/JavaAPI/org.eclipse.jface.viewers/extendsViewerSorterSortable.htm 
	 * @param viewer
	 * @param columns
	 * @param comparators
	 */
	public EclipsersViewSorter(TableViewer viewer, TableColumn[] columns, Comparator<IEclipserItem>[] comparators) {
		System.out.println("EclipserViewSorter--------: " +  columns );
		
		this.viewer = viewer;
		infos = new SortInfo[columns.length];
		
		for(int i=0, size=columns.length; i<size; i++) {
			infos[i] = new SortInfo();
//			infos[i].columnIndex = i;
			infos[i].comparator = comparators[i];
			infos[i].descending = false;

			createSelectionListener(columns[i], infos[i]);
		}
	}
	
	/**
	 * Param type은 변경되면 안됨 (Viewer, Object, Object) 
	 * 변경될 경우 Sort 하지 못한다
	 * public int compare(Viewer viewer, Object elclipser1, Object eclipser2) {
	 */
	public int compare(Viewer viewer, Object elclipser1, Object eclipser2) {
		System.out.println("compare ------- ");
		for(int i=0, size=infos.length; i<size; i++) {
			int result = infos[i].comparator.compare((IEclipserItem)elclipser1, (IEclipserItem)eclipser2);
			if(result!=0) {
				if(infos[i].descending) {
					return -result;
				} else {
					return result;
				}
			}
		}
		
		return 0;
	}
	
	private void createSelectionListener(final TableColumn column, final SortInfo info) {
		System.out.println("createSelectionListener--------: " +  info );
		column.addSelectionListener(new SelectionAdapter() {			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("widgetSelected--------: " +  info );
				sortUsing(info);
			}

		});
	}
	
	protected void sortUsing(SortInfo info) {
		if(info==infos[0]) {
			info.descending = !info.descending;
		}
		else {
			for(int i=0, size=infos.length; i<size; i++) {
				if(info == infos[i]) {
					System.out.println("" );
					System.arraycopy(infos, 0, infos, 1, i);
					infos[0] = info;
					info.descending = false;
					break;
				}
			}
		}
		
		viewer.refresh();
	}
	
	public static void main(String[] args) {
		String a[] = {"A", "B", "C", "D", "E", "F"};
		
		System.arraycopy(a, 0, a, 1, 4);
		
		for (int i=0, size=a.length ; i<size; i++)
			System.out.println(a[i]);
	}
}
