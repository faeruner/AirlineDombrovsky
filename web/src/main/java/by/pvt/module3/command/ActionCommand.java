package by.pvt.module3.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
	String execute(HttpServletRequest request);
	static final Logger log = LogManager.getRootLogger();
}