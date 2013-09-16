package org.achartengine.chartdemo.demo.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class SinhFunction extends AbstractDemoChart{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Intent execute(Context context) {
	    String[] titles = new String[] { "sin + cos"};
	    List<double[]> x = new ArrayList<double[]>();
	    List<double[]> values = new ArrayList<double[]>();
	    int step = 4;
	    int count = 360 / step + 1;
	    x.add(new double[count]);
	    double[] sinValues = new double[count];
	    values.add(sinValues);
	    for (int i = 0; i < count; i++) {
	      int angle = i * step;
	      x.get(0)[i] = angle;
	      double rAngle = Math.toRadians(angle);
	      sinValues[i] = Math.sinh(rAngle);
	    }
	    int [] colors = new int[] { Color.RED};
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    setChartSettings(renderer, "Trigonometric functions", "X (in degrees)", "Y", 0, 360, -2, 2,
	        Color.GRAY, Color.LTGRAY);
	    renderer.setXLabels(20);
	    renderer.setYLabels(10);
	    return ChartFactory.getLineChartIntent(context, buildDataset(titles, x, values), renderer);
	}


}
