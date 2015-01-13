package controller;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*;
import com.brightcove.zencoder.client.request.*;
import com.brightcove.zencoder.client.response.*;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeferson
 */
public class ZencoderEncode {

    public static void EncodeVideo(String filename, String ext) throws ZencoderClientException {
        ZencoderClient client = new ZencoderClient("API_KEY");

        ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
        job.setInput("s3://sandboxoriginal/" + filename + "." + ext);

        List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();

        ZencoderOutput output1 = new ZencoderOutput("s3://sandboxencoded/" + filename + ".mp4");
        output1.setFilename(filename + ".mp4");
        output1.setFormat(ContainerFormat.MP4);
        output1.setPublic(true);
        outputs.add(output1);

        /*ZencoderOutput output2 = new ZencoderOutput("s3://sandboxencoded/" + filename + ".ogg");
         output2.setFilename(filename + ".ogg");
         output2.setFormat(ContainerFormat.OGG);
         outputs.add(output2);*/
        job.setOutputs(outputs);

        try {
            ZencoderCreateJobResponse response = client.createZencoderJob(job);

            /*String jobId = response.getId();
            ZencoderJobDetail details = client.getZencoderJob(jobId);
            String inputId = details.getInputMediaFile().getId();
            String outputId = response.getOutputs().get(0).getId();

            ZencoderInputOutputProgress inputProgress = client.getInputProgress(inputId);
            ZencoderInputOutputProgress outputProgress = client.getOutputProgress(outputId);
            ZencoderJobProgress jobProgress = client.getJobProgress(jobId);

            State stateIn = inputProgress.getState();
            State stateOut = outputProgress.getState();
            State stateJob = jobProgress.getState();
            String strStateIn = stateIn.toString();
            String strStateOut = stateOut.toString();
            String strStateJob = stateJob.toString();

            while (!strStateOut.equals("finished")) {
                System.out.println("Input progress:" + strStateIn);
                System.out.println("Output progress:" + strStateOut);
                System.out.println("Job progress:" + strStateJob);

                Thread.sleep(5 * 1000); // Pause for 5 seconds

                stateIn = inputProgress.getState();
                stateOut = outputProgress.getState();
                stateJob = jobProgress.getState();
                strStateIn = stateIn.toString();
                strStateOut = stateOut.toString();
                strStateJob = stateJob.toString();
            }*/
        } catch (Exception e) {

        }

    }
}
