package gwicks.com.soundpressurelevel;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SoundPressure extends JobService {

//    double spl2;
//    File location;
//    long TS;
//    double SPL;
//    boolean running;
//    private static final String TAG = "SoundPressure";
//
//    private MediaRecorder mRecorder = null;
//    @Override
//    public boolean onStartJob(JobParameters jobParameters) {
//
//        String path = this.getExternalFilesDir(null) + "/videoDIARY/SoundPressure/";
//        TS = System.currentTimeMillis();
//
//        Calendar c = Calendar.getInstance();
//
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//        String formattedDate = df.format(c.getTime());
//
//        SimpleDateFormat df2 = new SimpleDateFormat("ddMMyyyy");
//        String currentDate = df2.format(c.getTime());
//
//        File directory = new File(path);
//        if(!directory.exists()){
//            Log.d(TAG, "onStartJob: making directory");
//            directory.
//                    mkdirs();
//        }
//
//        if (mRecorder == null) {
//            mRecorder = new MediaRecorder();
//            try{
//                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                mRecorder.setOutputFile("/dev/null");
//                mRecorder.prepare();
//                mRecorder.start();
//                Log.d(TAG, "start: recorder started");
//            }catch(Exception e){
//                Log.d(TAG, "start: exception: " + e);
//            }
//
//        }
//
//
////        while(true){
////            Log.d(TAG, "onStartJob: 1");
////        }
//
//        location = new File(directory, currentDate +".txt");
//
//      //  final SoundMeter mSoundMeter = new SoundMeter();
//
//        Log.d(TAG, "onStartJob: starting");
//       // mSoundMeter.start();
//        Log.d(TAG, "onStartJob: started");
//        SPL = mRecorder.getMaxAmplitude();
//
//        running = true;
//
//        while(running){
//            Log.d(TAG, "run: 1");
//            spl2 = mRecorder.getMaxAmplitude();
//            Log.d(TAG, "onStartJob: have the SPL: " + SPL);
//            Log.d(TAG, "onStartJob: have the SPL: " + spl2);
//            double amplitudeDb = 20 * Math.log10((double)Math.abs(spl2));
//            Log.d(TAG, "run: amplitude is: " + amplitudeDb);
//
//        }
//
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//              running = false;
//            }
//        }, 10000);
//
//
//
//
//
//
//
////        final Handler handler = new Handler();
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                //Do something after 100ms
////                Log.d(TAG, "run: 1");
////                spl2 = mSoundMeter.getAmplitude();
////                Log.d(TAG, "onStartJob: have the SPL: " + SPL);
////                Log.d(TAG, "onStartJob: have the SPL: " + spl2);
////                double amplitudeDb = 20 * Math.log10((double)Math.abs(spl2));
////                Log.d(TAG, "run: amplitude is: " + amplitudeDb);
////
////                mSoundMeter.stop();
////                Log.d(TAG, "onStartJob: stopped");
////                writeToFile(location, TS + "," + SPL + "\n");
////                Log.d(TAG, "onStartJob: writted to file");
////            }
////        }, 2000);
//
////        Log.d(TAG, "onStartJob: have the SPL: " + SPL);
////        Log.d(TAG, "onStartJob: have the SPL: " + spl2);
////
////        mSoundMeter.stop();
////        Log.d(TAG, "onStartJob: stopped");
////        writeToFile(location, TS + "," + SPL + "\n");
//
//
//
//
//
//
//        return false;
//    }
//
//    @Override
//    public boolean onStopJob(JobParameters jobParameters) {
//        return false;
//    }
//
//    private static void writeToFile(File file, String data) {
//
//        FileOutputStream stream = null;
//        try {
//            stream = new FileOutputStream(file, true);
//            stream.write(data.getBytes());
//        } catch (FileNotFoundException e) {
//            Log.e("History", "In catch");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public class SoundMeter {
//
//        private MediaRecorder mRecorder = null;
//
//        public void start() {
//            Log.d(TAG, "start: starting mediarecrder");
//            if (mRecorder == null) {
//                mRecorder = new MediaRecorder();
//                try{
//                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                    mRecorder.setOutputFile("/dev/null");
//                    mRecorder.prepare();
//                    mRecorder.start();
//                    Log.d(TAG, "start: recorder started");
//                }catch(Exception e){
//                    Log.d(TAG, "start: exception: " + e);
//                }
//
//            }
//        }
//
//        public void stop() {
//            if (mRecorder != null) {
//                mRecorder.stop();
//                mRecorder.reset();
//                mRecorder.release();
//                mRecorder = null;
//            }
//        }
//
//        public double getAmplitude() {
//            if (mRecorder != null)
//                return  mRecorder.getMaxAmplitude();
//            else
//                return 0;
//
//        }
//    }
//}

//
    private static final String TAG = "SoundPressure";

    MediaRecorder mMediaRecorder;
    double amplitude;
    Timer timer;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        String path = this.getExternalFilesDir(null) + "/videoDIARY/SoundPressure/";
        long TS = System.currentTimeMillis();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        SimpleDateFormat df2 = new SimpleDateFormat("ddMMyyyy");
        String currentDate = df2.format(c.getTime());

        File directory = new File(path);
        if (!directory.exists()) {
            Log.d(TAG, "onStartJob: making directory");
            directory.
                    mkdirs();
        }


        mMediaRecorder = new MediaRecorder();
        start(mMediaRecorder);



        return false;
    }

    public void start(MediaRecorder recorder) {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/dev/null");

        timer = new Timer();
        timer.scheduleAtFixedRate(new RecorderTask(recorder), 0, 1000);// will update Max Amplitude Value every 1 second
        Log.d(TAG, "start: 11");

        try {
            recorder.prepare();
            recorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private class RecorderTask extends TimerTask { ;
        private MediaRecorder recorder;



        public RecorderTask(MediaRecorder recorder) {
            long t1 = System.currentTimeMillis();

            this.recorder = recorder;
        }

        public void run() {
            if(amplitude > 0){
                Log.d(TAG, "run: cancelling timer");
                timer.cancel();
            }
            Log.d(TAG, "run: running");
            long t0 = System.currentTimeMillis();


                Log.d(TAG, "run: runing");
                amplitude = recorder.getMaxAmplitude();


                Log.d(TAG, "run: value " + amplitude    );
                double amplitudeDb = 20 * Math.log10((double)Math.abs(amplitude));
                Log.d(TAG, "run: DB: " + amplitudeDb);
                double timeTaken = (System.currentTimeMillis() - t0) /1000 ;
                Log.d(TAG, "run: timeer: "  + timeTaken);
//            double timeTake2n = (System.currentTimeMillis() - t1) /1000 ;
//            Log.d(TAG, "run: timeer: "  + timeTaken);



        }
    }




    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

