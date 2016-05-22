package com.impinj.octanesdk.samples;

import java.util.Scanner;

import com.impinj.octanesdk.ImpinjReader;
import com.impinj.octanesdk.OctaneSdkException;
import com.impinj.octanesdk.Settings;
import com.impinj.octanesdk.samples.helpers.SampleProperties;

public class SetGPO {

    public static void main(String[] args) {

        try {
            String hostname = System.getProperty(SampleProperties.hostname);                       

            if (hostname == null) {
                throw new Exception("Must specify the '" 
                        + SampleProperties.hostname + "' property");
            }        
            
            ImpinjReader reader = new ImpinjReader();
            
            reader.connect(hostname);

            Settings settings = reader.queryDefaultSettings();
            reader.applySettings(settings);

            System.out.println("Setting general purpose outputs");

            for (int i = 1; i <= 4; i++) {
                reader.setGpo(i, true);
                Thread.sleep(1500);
                reader.setGpo(i, false);
            }

            System.out.println("Press enter to exit.");
            Scanner s = new Scanner(System.in);
            s.nextLine();

            reader.disconnect();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
}
