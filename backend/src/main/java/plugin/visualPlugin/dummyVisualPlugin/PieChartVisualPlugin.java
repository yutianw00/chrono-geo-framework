package plugin.visualPlugin.dummyVisualPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;
import org.icepear.echarts.Bar;
import org.icepear.echarts.render.Engine;

import java.util.List;

public class PieChartVisualPlugin implements VisualPlugin {
    @Override
    public String getName() {
        return "Pie chart Visual";
    }

    @Override
    public String getIntro() {
        return "display a pie chart visualization";
    }

    @Override
    public void render(List<MyData> data) {
        return false;
    }

    @Override
    public String graphTitle() {
        return "Pie chart graph Title";
    }

    @Override
    public String graphDescription() {
        return "This will display the percent of data in different places at the same time";
    }

    public static void main(String[] args) {
        Bar bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[] { "Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie" })
                .addYAxis()
                .addSeries("2015", new Number[] { 43.3, 83.1, 86.4, 72.4 })
                .addSeries("2016", new Number[] { 85.8, 73.4, 65.2, 53.9 })
                .addSeries("2017", new Number[] { 93.7, 55.1, 82.5, 39.1 });
        Engine engine = new Engine();
        // The render method will generate our EChart into a HTML file saved locally in the current directory.
        // The name of the HTML can also be set by the first parameter of the function.
//        engine.render("index.html", bar);
        System.out.println(engine.renderHtml(bar));
    }
}
