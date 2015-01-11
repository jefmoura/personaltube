package controller;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*;
import com.brightcove.zencoder.client.request.*;
import com.brightcove.zencoder.client.response.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeferson
 */
public class ZencoderEncode {

    public static void EncodeVideo(String filename) throws ZencoderClientException {
        ZencoderClient client = new ZencoderClient("70845e01f5faa050fde8408b0c8e079d");

        ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
        job.setInput("http://sandboxoriginal.s3.amazonaws.com/" + filename);

        List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();

        filename = (filename.substring(0, filename.lastIndexOf("."))) + ".MP4";

        ZencoderOutput output1 = new ZencoderOutput("http://sandboxencoded.s3.amazonaws.com/" + filename);
        output1.setFormat(ContainerFormat.MP4);
        outputs.add(output1);

        job.setOutputs(outputs);
        try {
            ZencoderCreateJobResponse response = client.createZencoderJob(job);

            String jobId = response.getId();
            ZencoderJobDetail details = client.getZencoderJob(jobId);
            String outputId = response.getOutputs().get(0).getId();
            ZencoderInputOutputProgress outputProgress = client.getOutputProgress(outputId);
            State state = outputProgress.getState();
            String strState = state.toString();

            while (!strState.equals("finished")) {
                state = outputProgress.getState();
                strState = state.toString();
            }
        } catch (Exception e) {
            
        }

    }
}
