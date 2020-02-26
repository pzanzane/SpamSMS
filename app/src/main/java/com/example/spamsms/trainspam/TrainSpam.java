package com.example.spamsms.trainspam;

import android.content.Context;
import android.util.Log;

import com.example.spamsms.logistic_regression.LogisticRegressionClassifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainSpam {

    private static TrainSpam trainSpam;
    public static synchronized TrainSpam getInstance(Context context){

        if(trainSpam == null){

            trainSpam = new TrainSpam();
            trainSpam.initialiseTraining(context);

        }

        return trainSpam;


    }

    private TrainSpam(){}
    private LogisticRegressionClassifier logisticRegressionClassifier = null;

    private void initialiseTraining(Context context){

        try {

            /*
            List<File> listHamFiles = getFilesFromAssetDirectory(context,"lingmessages");
            File[] arrFileHam = new File[listHamFiles.size()];
            arrFileHam = listHamFiles.toArray(arrFileHam);*/



            /*
            List<File> listSpamFiles = getFilesFromAssetDirectory(context,"spammessages");
            File[] arrFileSpam = new File[listSpamFiles.size()];
            arrFileSpam = listSpamFiles.toArray(arrFileSpam);*/


            logisticRegressionClassifier = new LogisticRegressionClassifier();
            logisticRegressionClassifier.train("lingmessages","spammessages",
                    context.getResources().getAssets().list("lingmessages"),
                    context.getResources().getAssets().list("spammessages"),
                    context.getAssets());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isSpam(String msg){

        return logisticRegressionClassifier.classify(msg);

    }

    public List<File> getFilesFromAssetDirectory(Context context, String directory) throws IOException {

        String[] fileNames = context.getResources().getAssets().list(directory);

        List<File> listFiles = new ArrayList<>();

        for(String fileName : fileNames){

            listFiles.add(new File(fileName));

        }

        return listFiles;
    }
}
