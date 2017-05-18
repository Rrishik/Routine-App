package iiest.risk.routine_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import iiest.risk.routine_app.R;

public class Calendar extends AppCompatActivity {

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append("\n" + mLine);
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        TextView tv = (TextView) findViewById(R.id.textView3);
        String str = "";
        Context context = this;
        try {
            str = readFromAssets(context, "dates.txt");
            tv.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
