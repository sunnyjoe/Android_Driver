package com.example.qingjiao.driver_mock.service;

import android.app.ActivityManager;
import android.content.Context;

import com.example.qingjiao.driver_mock.AGrabTaxiApplication;

/**
 * Created by qing.jiao on 2/12/16.
 */

public class ConnectionManager {
    private static final String TAG = ConnectionManager.class.getSimpleName();
    private static final ConnectionManager INSTANCE = new ConnectionManager();

    private static final int SERVICE_IO_BUFFER_SIZE_HIMEM = 1024 * 1024;                   // Increase
    private static final int SERVICE_IO_BUFFER_SIZE_LOMEM = 512 * 1024;                    // Increase
    private static final int HIMEM_THRESHOLD_MB = 32;                            // 32MB

    private int mBufferSize;

    public static ConnectionManager getInstance() {
        return ConnectionManager.INSTANCE;
    }

    private ConnectionManager() {
        super();
    }

    public void setup() {
        this.setBufferSizeForDevice(AGrabTaxiApplication.getContext());
    }


    /**
     * Returns how much memory we should allocate for NIOService buffer,
     * depending on how much Heap Space we have on the device
     *
     * @return the buffer size to use. Will always return at least SERVICE_IO_BUFFER_SIZE_LOMEM
     */

    private void setBufferSizeForDevice(final Context context) {
        if (context == null) {
            return;
        }
        this.mBufferSize = ConnectionManager.SERVICE_IO_BUFFER_SIZE_LOMEM;

        try {
            // Retrieve the memory class from ActivityManager
            final ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            final int memoryClass = manager.getMemoryClass();

            // Limit the buffer size to the desired value #ConnectionManager.SERVICE_IO_BUFFER_SIZE_HIMEM
            if (memoryClass > ConnectionManager.HIMEM_THRESHOLD_MB) {
                this.mBufferSize = ConnectionManager.SERVICE_IO_BUFFER_SIZE_HIMEM;
            }
        } catch (final RuntimeException e) {
            // Don't care. Use the minimum
        }

        // Prevent the buffer size to go lower than the allowed buffer size #ConnectionManager.SERVICE_IO_BUFFER_SIZE_LOMEM
        this.mBufferSize =
                this.mBufferSize <= ConnectionManager.SERVICE_IO_BUFFER_SIZE_LOMEM ? ConnectionManager.SERVICE_IO_BUFFER_SIZE_LOMEM :
                        this.mBufferSize;
    }
}
