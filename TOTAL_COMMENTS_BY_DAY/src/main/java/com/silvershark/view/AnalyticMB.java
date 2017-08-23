/**
 * 
 */
package com.silvershark.view;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.silvershark.control.AnalyticCtrl;

/**
 * @author Rodrigo
 *
 */
@ManagedBean	
@ViewScoped
public class AnalyticMB implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2986425860606060894L;
	private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private BarChartModel model = null;
    private ChartSeries boys = null;
    private ArrayList<String> lis = null;
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<String> patterns = new ArrayList<String>();
    
    public AnalyticMB(){
    	 
    }
 
	@PostConstruct
	public void init() {
		AnalyticCtrl ctrl = new AnalyticCtrl();
		lis = (ArrayList<String>) ctrl.getTotalLikesByDay();
		createBarModels();
	}
    
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
	private BarChartModel initBarModel() {

		model = new BarChartModel();
		boys = new ChartSeries();

		boys.setLabel("Comments");

		for (int i = 0; i < lis.size(); i++) {
			newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = null;
			try {
				myDate = newDateFormat.parse(lis.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newDateFormat.applyPattern("dd/MM/yyyy");
			String myDateString = newDateFormat.format(myDate);
			boys.set(myDateString, Integer.parseInt(lis.get(++i)));
		}
		
		// quantidade de par: data/count
		int quant = lis.size() / 2;
		String d = lis.get(lis.size() - 2);
		
		if(quant < 7){
			while(quant < 7){
				d = addDay(d);
				patterns.add(d);
				quant++;
			}
		}
			
		for (int i = 0; i < patterns.size(); i++) {
			newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = null;
			try {
				myDate = newDateFormat.parse(patterns.get(i));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newDateFormat.applyPattern("dd/MM/yyyy");
			String myDateString = newDateFormat.format(myDate);
			boys.set(myDateString, 0);

		}
		model.addSeries(boys);
		return model;
	}
    
    
    
	private String addDay(String sData) {
		try {
			Date data = formato.parse(sData);
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			cal.add(Calendar.DATE, 1);
			data = cal.getTime();
			newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String myDateString = newDateFormat.format(data);
			System.out.println(myDateString);
			return myDateString;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
//			return "number";
		}
		return null;
	}
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Total Comments by Day");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Day");
//        xAxis.
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Comments");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Day");        
    }
 
}
