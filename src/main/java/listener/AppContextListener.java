package listener;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.bean.Video;
import model.dao.DaoVideo;

/**
 *
 * @author jeferson
 */
 
@WebListener
public class AppContextListener implements ServletContextListener {
 
    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        List<Video> videos = new ArrayList();
        DaoVideo dao = new DaoVideo();
        dao.createTable();
        videos = dao.getVideos();
        contextEvent.getServletContext().setAttribute("videos", videos);
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {

    }
     
}