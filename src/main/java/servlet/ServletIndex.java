package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.brightcove.zencoder.client.ZencoderClientException;
import static controller.AmazonS3Upload.UploadFile;
import static controller.ZencoderEncode.EncodeVideo;

/**
 *
 * @author jeferson
 */
public class ServletIndex extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*String fullPath = request.getParameter("hd1");
        String fileName = fullPath.substring(fullPath.lastIndexOf("/"));
        String pathVideo = "http://sandboxoriginal.s3.amazonaws.com/" + (fileName.substring(0, fileName.lastIndexOf("."))) + ".MP4";

        try {
            UploadFile(fullPath, fileName);
            EncodeVideo(fileName);
            request.setAttribute("pathVideo", pathVideo);
            request.getRequestDispatcher("mediaPlayer.jsp").forward(request, response);
        } catch (ZencoderClientException e)
        {
            
        }*/
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
