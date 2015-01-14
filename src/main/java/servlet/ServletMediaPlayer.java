package servlet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author jeferson
 */
public class TranscodeListener extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ServletIndex.class);

    public TranscodeListener() {
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
        String videoMP4 = "https://s3.amazonaws.com/sandboxencoded/" + uuidValue + ".mp4";
        request.setAttribute("videoMP4", videoMP4);
        request.getRequestDispatcher("mediaPlayer.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
