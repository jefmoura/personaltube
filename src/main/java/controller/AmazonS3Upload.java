package controller;

import java.io.IOException;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonClientException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author jeferson
 */
public class AmazonS3Upload {

    private static final String AMAZON_ACCESS_KEY = "ACCESS_KEY";
    private static final String AMAZON_SECRET_KEY = "SECRET_KEY";
    private static final String S3_BUCKET_NAME = "BUCKET_NAME";

    private static final Logger LOGGER = LogManager.getLogger(AmazonS3Upload.class);

    public AmazonS3Upload() {
        super();
    }

    public static void UploadFile(FileItem itemFile, String uuidValue) throws IOException {

        if (itemFile != null) {
            // get item inputstream to upload file into s3 aws
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AMAZON_ACCESS_KEY, AMAZON_SECRET_KEY);

            AmazonS3 s3client = new AmazonS3Client(awsCredentials);

            try {
                ObjectMetadata om = new ObjectMetadata();
                om.setContentLength(itemFile.getSize());
                String ext = FilenameUtils.getExtension(itemFile.getName());
                String keyName = uuidValue + '.' + ext;

                s3client.putObject(new PutObjectRequest(S3_BUCKET_NAME, keyName, itemFile.getInputStream(), om));
                s3client.setObjectAcl(S3_BUCKET_NAME, keyName, CannedAccessControlList.PublicRead);

            } catch (AmazonServiceException ase) {
                LOGGER.error(uuidValue + ":error:" + ase.getMessage());

            } catch (AmazonClientException ace) {
                LOGGER.error(uuidValue + ":error:" + ace.getMessage());
            }

        } else {
            LOGGER.error(uuidValue + ":error:" + "No Upload file");
        }
    }
}
