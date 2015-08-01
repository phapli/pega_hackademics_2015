package schooltasklist.pega.com.googlecloudmessage;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

/**
 * Created by Tran on 8/1/2015.
 */
public class MyInstanceIDListenerService extends GcmTaskService {

    @Override
    public int onRunTask(TaskParams taskParams) {
        return 0;
    }
}
