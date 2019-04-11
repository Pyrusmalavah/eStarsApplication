package com.pyrusoft.pybot.pysecurity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class AlertListView extends ArrayAdapter<String> {

    private String[] alertTitle;
    private String[] alertLocation;
    private String[] alertDateTime;
    private String[] alertImage;
    private Activity context;
    Bitmap bitmap;


    public AlertListView(Activity context, String[] alertTitle, String[] alertLocation, String[] alertDateTime, String[] alertImage) {
        super(context, R.layout.alert_layout, alertTitle);
        this.context = context;
        this.alertTitle = alertTitle;
        this.alertDateTime = alertDateTime;
        this.alertLocation = alertLocation;
        this.alertImage = alertImage;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.alert_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }


        viewHolder.alert_title.setText(alertTitle[position]);
        viewHolder.alert_loc.setText(alertLocation[position]);
        viewHolder.alert_datetime.setText(alertDateTime[position]);
        new GetImagePath(viewHolder.alert_img).execute(alertImage[position]);


        return r;
    }

    class ViewHolder {
        TextView alert_title, alert_loc, alert_datetime;
        ImageView alert_img;

        ViewHolder(View v) {
            alert_title = (TextView) v.findViewById(R.id.alert_title);
            alert_loc = (TextView) v.findViewById(R.id.alert_location);
            alert_datetime = (TextView) v.findViewById(R.id.alert_time);
            alert_img = (ImageView) v.findViewById(R.id.alert_image);
        }
    }

    public class GetImagePath extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public GetImagePath(ImageView imagevw) {
            this.imageView = imagevw;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap=null;

            try{
                InputStream inputStream = new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            }catch(Exception x){
                x.printStackTrace();
            }

            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
