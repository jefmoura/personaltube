package controller;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.*
import com.brightcove.zencoder.client.request.*
import com.brightcove.zencoder.client.response.*;

/**
 *
 * @author jeferson
 */
public class ZencoderEncode {

    public static void EncodeVideo(String path) {
        ZencoderClient client = new ZencoderClient("INSERT_API_KEY_HERE");

        ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
        job.setInput("s3://zencodertesting/test.mov");

        List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();

        ZencoderOutput output1 = new ZencoderOutput();
        output1.setFormat(ContainerFormat.MP4);
        outputs.add(output1);

        job.setOutputs(outputs)
        ZencoderCreateJobResponse response = client.createZencoderJob(job);

        String jobId = response.getId();
        ZencoderJobDetail details = client.getZencoderJob(jobId);
        String outputId = response.getOutputs().get(0).getId();
        ZencoderInputOutputProgress outputProgress = client.getOutputProgress(outputId);
        State state = outputProgress.getState();
        String strState = state.toString();
        
        while(!strState.equals("finished")) {
            state = outputProgress.getState();
            strState = state.toString();
	}

    }
}
