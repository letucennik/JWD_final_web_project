package by.tc.shop.controller.listener;

import by.tc.shop.dao.exception.ConnectionPoolException;
import by.tc.shop.dao.pool.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
@javax.servlet.annotation.WebListener
public class WebContextListener implements ServletContextListener {
    private static final Logger logger=Logger.getLogger(WebContextListener.class);
    private static final String UNABLE_TO_INIT_POOL="Error while initializing pool data";
    private static final String ERROR_ON_CLOSING_POOL="Error while closing connection pool";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext= sce.getServletContext();
        logger.log(Level.INFO,servletContext.getRealPath("/"));
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e){
            logger.log(Level.FATAL,UNABLE_TO_INIT_POOL);
            throw new ListenerException(UNABLE_TO_INIT_POOL,e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e){
            logger.log(Level.FATAL,ERROR_ON_CLOSING_POOL);
            throw new ListenerException(ERROR_ON_CLOSING_POOL,e);
        }
    }
}
