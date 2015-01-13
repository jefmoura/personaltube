package servlet;

import static controller.ZencoderEncode.EncodeVideo;
import com.brightcove.zencoder.client.ZencoderClientException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.lang.Thread;
import java.lang.InterruptedException;

/**
 *
 * @author jeferson
 */
public class ServletTranscode extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ServletIndex.class);

    public ServletTranscode() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuidValue = (String) request.getSession(false).getAttribute("uuidValue");
        String fileExt = (String) request.getSession(false).getAttribute("fileExt");

        try {
            EncodeVideo(uuidValue, fileExt);
        } catch (ZencoderClientException ex) {
            LOGGER.error(uuidValue + ":" + ":error: " + ex.getMessage());
        }

        try {
            Thread.sleep(15 * 1000);                 //Pause 30 seconds.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        request.setAttribute("transcode", true);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
