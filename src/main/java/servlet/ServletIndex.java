package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import static controller.AmazonS3Upload.UploadFile;
import java.util.ArrayList;
import model.bean.Video;
import model.dao.DaoVideo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author jeferson
 */
public class ServletIndex extends HttpServlet {

    private static final long serialVersionUID = -7720246048637220075L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 5;  // 5MB
    private static final String UUID_STRING = "uuid";

    private static final Logger LOGGER = LogManager.getLogger(ServletIndex.class);

    public ServletIndex() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Video> videos = new ArrayList();
        DaoVideo dao = new DaoVideo();
        
        videos = dao.getVideos();
        request.setAttribute("videosAtt", videos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        DaoVideo dao = new DaoVideo();
        // cross-domain communication
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");

        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }

        // configure upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);

        ServletFileUpload upload = new ServletFileUpload(factory);

        String uuidValue = "";
        FileItem itemFile = null;

        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();

            // iterates over form's fields to get UUID Value
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equalsIgnoreCase(UUID_STRING)) {
                        uuidValue = item.getString();
                    }
                }
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    itemFile = item;
                }
            }
            UploadFile(itemFile, uuidValue);
        } catch (Exception ex) {
            LOGGER.error(uuidValue + ":" + ":error: " + ex.getMessage());
        }
        dao.setVideo(uuidValue, request.getParameter("description"));
        session.setAttribute("fileExt", FilenameUtils.getExtension(itemFile.getName()));
        session.setAttribute("uuidValue", uuidValue);
        request.getRequestDispatcher("transcoding.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
