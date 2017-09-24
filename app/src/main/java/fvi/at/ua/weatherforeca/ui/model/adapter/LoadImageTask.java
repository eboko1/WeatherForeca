package fvi.at.ua.weatherforeca.ui.model.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Vika on 23.09.2017.
 */

class LoadImageTask extends AsyncTask <String, Void, Bitmap>{
        private ImageView imageView;
        ConcurrentHashMap<String, Bitmap> bitmaps;

    public LoadImageTask(ImageView conditionImageView) {
        this.imageView = conditionImageView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpsURLConnection)url.openConnection();

           try (InputStream inputStream = connection.getInputStream()){
                bitmap = BitmapFactory.decodeStream(inputStream);
                bitmaps.put(params[0], bitmap);
            } catch (Exception e){
               e.printStackTrace();
           }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            {
                connection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
