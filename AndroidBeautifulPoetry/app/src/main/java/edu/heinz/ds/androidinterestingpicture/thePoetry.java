package edu.heinz.ds.androidinterestingpicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Sharon Shih (chunchus)
 * This program is an app that make request to a Java servlet on Heroku, and
 * get the information back from a third-party API (PoetryDB). This file will run
 * first before the PoetryInfo.java runs. It starts from the onCreate method, and
 * call upon other class and methods to do the tasks.
 * Reference: https://github.com/CMU-Heinz-95702/lab7-Android
 *
 */
public class thePoetry extends AppCompatActivity {

    thePoetry me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * The click listener will need a reference to this object, so that upon successfully finding a picture from Flickr, it
         * can callback to this object with the resulting picture Bitmap.  The "this" of the OnClick will be the OnClickListener, not
         * this thePoetry.
         */
        final thePoetry ma = this;

        /*
         * Find the "submit" button, and add a listener to it
         */
        Button submitButton = (Button)findViewById(R.id.submit);


        // Add a listener to the send button
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View viewParam) {
//                String searchTerm = ((EditText)findViewById(R.id.searchTerm)).getText().toString();
                String searchType = ((EditText)findViewById(R.id.searchTerm)).getText().toString();
                String searchName = ((EditText)findViewById(R.id.searchTerm2)).getText().toString();

                System.out.println("searchType = " + searchType);
                System.out.println("searchName = " + searchName);
                GetPoetry gp = new GetPoetry();
                gp.search(searchType,searchName, me, ma); // Done asynchronously in another thread.  It calls ip.pictureReady() in this thread when complete.
            }
        });
    }

    /*
     * This is called by the GetPoetry object when the picture is ready.  This allows for passing back the PoetryInfo object for updating the TextView
     */

    public void pictureReady(PoetryInfo poet) {
        TextView searchView = (EditText)findViewById(R.id.searchTerm);
        TextView searchView2 = (EditText)findViewById(R.id.searchTerm2);
        TextView poetryAuthor = (TextView) findViewById(R.id.author);
        TextView poetryTitle = (TextView) findViewById(R.id.poetryTitle);
        TextView poetryLines = (TextView) findViewById(R.id.poetryLines);
        ImageView pictureView = (ImageView)findViewById(R.id.success);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        Bitmap s = null;
        Bitmap f = null;
        if (poet != null) {
            System.out.println("poet: "+poet);
            poetryAuthor.setText("Author: "+poet.author);
            poetryTitle.setText("Title: "+ poet.title);
            poetryLines.setText("Lines: "+poet.lines.toString());
            textView2.setText("Below is your Poetry! Enjoy!");
            //the two icon comes from https://www.flaticon.com/
            new DownloadImageTask((ImageView) findViewById(R.id.success)).execute("https://cdn-icons-png.flaticon.com/128/4315/4315445.png");
            pictureView.setVisibility(View.VISIBLE);
        } else {
            textView2.setText("Poetry not found!");
            new DownloadImageTask((ImageView) findViewById(R.id.success)).execute("https://cdn-icons-png.flaticon.com/128/738/738884.png");
            pictureView.setVisibility(View.VISIBLE);
            System.out.println("No poet");

        }
        searchView.setText("");
        searchView2.setText("");
    }
    //download the image
    //Code obtained from stackOverflow ( https://stackoverflow.com/questions/5776851/load-image-from-url)
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
