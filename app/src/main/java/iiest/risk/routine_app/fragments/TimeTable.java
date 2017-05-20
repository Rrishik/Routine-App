package iiest.risk.routine_app.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import iiest.risk.routine_app.R;

public class TimeTable extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_time_table, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GraphView graph = (GraphView) getView().findViewById(R.id.graphs1);
        int percent = 0;
        int i = 0;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        int size = 100;
        int x = 0, y = 0;
        for (i = 0; i < size; i++) {
            x = x + 1;
            y = y + 2;
            series.appendData(new DataPoint(x, y), true, size);
        }
        graph.addSeries(series);

        try {
            readFromAssets(getActivity(), "tt.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void graph() {
        int i = 0;

    }

    public void readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        String mLine = reader.readLine();
        String p, s;
        int resId;
        while (mLine != null) {
            p = " ";
            s = " ";
            p = "text" + mLine.substring(0, 2);
            if (mLine.substring(0, 1).equals("s")) {
                s = "S" + mLine.substring(1, 2) + " : " + mLine.substring(3);
            } else {
                s = mLine.substring(3, 5);
            }
            resId = getResources().getIdentifier(p, "id", getActivity().getPackageName());
            TextView t = (TextView) getActivity().findViewById(resId);
            t.setText(s);
            mLine = reader.readLine();
        }

        reader.close();
    }
}
