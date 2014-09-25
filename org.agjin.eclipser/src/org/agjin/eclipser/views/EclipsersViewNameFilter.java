package org.agjin.eclipser.views;

import java.util.logging.Level;

import org.agjin.eclipser.logger.EclipserLogger;
import org.agjin.eclipser.model.IEclipserItem;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.internal.misc.StringMatcher;

@SuppressWarnings("restriction")
public class EclipsersViewNameFilter extends ViewerFilter {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	EclipserLogger logger = new EclipserLogger(EclipsersViewNameFilter.class, Level.CONFIG);
	
	/**
	 * @uml.property  name="viewer"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final StructuredViewer viewer;
	/**
	 * @uml.property  name="pattern"
	 */
	private String pattern = "";
	/**
	 * @uml.property  name="matcher"
	 * @uml.associationEnd  
	 */
	private StringMatcher matcher;
	
	public EclipsersViewNameFilter(StructuredViewer viewer) {
		this.viewer = viewer;
	}
	
	/**
	 * @return
	 * @uml.property  name="pattern"
	 */
	public String getPattern() {
		return this.pattern;
	}
	
	/**
	 * @param newPattern
	 * @uml.property  name="pattern"
	 */
	public void setPattern(String newPattern) {
		logger.debug("setPattern ---- newPattern : [{}]", newPattern);
		boolean filtering = matcher!=null;
		
		if (newPattern !=null && newPattern.trim().length() > 0 ) {
			pattern = newPattern;
			matcher = new StringMatcher(pattern, true, false);
			if (!filtering)
				viewer.addFilter(this);
			else
				viewer.refresh();
		}
		else {
			pattern = "";
			matcher = null;
			if (filtering)
				viewer.removeFilter(this);
		}
		
		logger.debug("setPattern ---- pattern : [{}]", pattern);
		logger.debug("setPattern ---- matcher : [{}]", matcher);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		logger.debug("select ---- parentElement : [{}]", parentElement);
		logger.debug("select ---- element : [{}]", element);
		
		return matcher.match(((IEclipserItem)element).getName());
	}

}
