package org.agjin.eclipser.logger;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EclipserLogger {
	
	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	Logger logger ;
	/**
	 * @uml.property  name="level"
	 */
	Level level;
	
	public EclipserLogger(Class<?> clz, Level level) {
		this.logger = LoggerFactory.getLogger(clz);
		this.level = level;
	}
	
	public void debug(String message, Object... param) {
		
		if (isCreateLog(Level.CONFIG))
			logger.debug(message, param);	
	}
	
	public void info(String message, Object... param) {
		
		if (isCreateLog(Level.INFO))
			logger.info(message, param);	
	}
	
	public void warn(String message, Object... param) {
		
		if (isCreateLog(Level.WARNING))
			logger.warn(message, param);	
	}
	
	public void error(String message, Object... param) {
		
		if (isCreateLog(Level.SEVERE))
			logger.error(message, param);	
	}
	
	public boolean isCreateLog(Level tempLevel) {
		return level.intValue() <= tempLevel.intValue();
	}
	
}
