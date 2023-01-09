package edu.heinz.ds.androidinterestingpicture;

import android.app.Activity;
//import android.os.Build;
//import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

/**
 * @author Sharon Shih (chunchus)
 * This class provides capabilities to get poetry information from the servlet on Heroku.
 * Reference: https://github.com/CMU-Heinz-95702/lab7-Android
 */
public class GetPoetry {
    thePoetry ip = null;   // for callback
    String searchType = null;       // search poetryDB using this type
    PoetryInfo poet = null;             // the rearranged information send back to the forefrond
    String searchName = null;       // search poetryDB for this keyword
    // Parameters:
    // String searchTerm: the thing to search for on flickr
    // Activity activity: the UI thread activity
    // InterestingPicture ip: the callback method's class; here, it will be ip.pictureReady( )
    public void search(String searchType, String searchName, Activity activity, thePoetry ip) {
        this.ip = ip;
        this.searchType = searchType;
        this.searchName = searchName;
        new BackgroundTask(activity).execute();
    }
    //the backgroundtask will handle the send httprequest out to servlet and get
    //back the required information
    private class BackgroundTask {

        private Activity activity; // The UI thread

        public BackgroundTask(Activity activity) {
            this.activity = activity;
        }

        //start the background thread
        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {

                    try {
                        doInBackground();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // This is magic: activity should be set to MainActivity.this
                    //    then this method uses the UI thread
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            }).start();
        }


        private void execute(){
            // There could be more setup here, which is why
            //    startBackground is not called directly
            startBackground();
        }

        /**
         * doInBackground( ) implements whatever you need to do on the background thread.
         * Implement this method to suit your needs
         * @throws IOException
         * @throws JSONException
         */

        private void doInBackground() throws IOException, JSONException {
            poet = search(searchType, searchName);
        }

        /**
         * onPostExecute( ) will run on the UI thread after the background thread completes.
         * Implement this method to suit your needs
         */

        public void onPostExecute() {
            ip.pictureReady(poet);
        }

        /**
         * The function calls the getRemoteJson method to connect to the servlet
         * @param searchType
         * @param searchName
         * @return
         * @throws IOException
         * @throws JSONException
         */
        //call the getRemoteJson function and send the JSON information back to the screen
        private PoetryInfo search(String searchType, String searchName) throws IOException, JSONException {

            return getRemoteJson(searchType, searchName);
        }

        /**
         * The function connect to the servlet and get back the poetry information
         * @param searchType searchType
         * @param searchName searchName
         * @return
         * @throws IOException
         * @throws JSONException
         */
        //using the user input information, send the HTTP request to the servlet
        private PoetryInfo getRemoteJson(String searchType, String searchName) throws IOException, JSONException {
            System.out.println("getRemotejson");
            HttpURLConnection urlConnection = null;
            PoetryInfo poetryInfo=null;
            URL url = null;
            JSONObject object = null;
            InputStream inStream = null;
            String response = "";
            try {
                url = new URL("https://murmuring-reaches-71576.herokuapp.com/getPoetry?choiceType="+searchType+"&searchName="+searchName);

                /*
                 * Create an HttpURLConnection.  This is useful for setting headers
                 * and for getting the path of the resource that is returned (which
                 * may be different than the URL above if redirected).
                 * HttpsURLConnection (with an "s") can be used if required by the site.
                 *
                 * Below connection code is from stackOverFlow
                 * Reference: https://stackoverflow.com/questions/34691175/how-to-send-httprequest-and-get-json-response-in-android
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoOutput(true);
                connection.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                //deserializing json object using GSON
                Gson gson = new Gson();
                String jsonString = sb.toString();
                poetryInfo = gson.fromJson(jsonString, PoetryInfo.class);
                System.out.println("Received json object author: " + poetryInfo.author);
            } catch (IOException e) {
                System.out.println("Eeek, an exception" + e.getMessage() + " " );
                e.printStackTrace();

            }
            return poetryInfo;
        }
    }
}

