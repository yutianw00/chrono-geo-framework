package plugin.visualPlugin.dummyVisualPlugin;

import framework.core.VisualPlugin;
import framework.core.utils.MyData;

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
    public String render(List<MyData> data) {
        String s = "<div>" +
                "  <h2>This is a dummy content made by the visual plugin</h2>" +
                "  <p>Assume there's a html graph here.</p>" +
                "</div>";
////        s = "<!DOCTYPE html>\n" +
////                "<html lang=\"en-US\">\n" +
////                "<head>\n" +
////                "    <meta charset=\"UTF-8\">\n" +
////                "    <title>Tornado Fatalities by State</title>\n" +
////                "    <script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\n" +
////                "</head>\n" +
////                "<body>\n" +
        //  s =   //"<div>aaaaa</div>" +
//                "<script></script>";
        s=   "<div>" + "<div>aaaaaaa</div>" + "    <div id='target' >aaaaaaa</div>" +
                "        <script>" +
                "        var target_target = document.getElementById('target');" +
                "        var layout = {" +
                "    title: 'Tornado Fatalities by State'," +
                "    height: 450," +
                "    width: 700," +
                "" +
                "" +

                "};" +
                "" +
                "var trace0 =" +
                "{" +
                "x: [\'AL\',\'MS\',\'TX\',\'OK\',\'MO\',\'AR\',\'TN\',\'IN\',\'KS\',\'OH\',\'MI\',\'KY\',\'IL\',\'LA\',\'GA\',\'FL\',\'NC\',\'MA\',\'WI\',\'MN\',\'IA\',\'PA\',\'SC\',\'NE\',\'ND\',\'VA\',\'NY\',\'SD\',\'MD\',\'OR\',\'WA\',\'MT\',\'CO\',\'WY\',\'NM\',\'CT\',\'AZ\',\'WV\',\'DE\',\'NH\',\'UT\',\'ME\',\'NJ\',\'ID\',\'VT\',\'CA\',\'NV\',\'RI\',\'DC\']," +
                "y: [789.0, 646.0, 617.0, 469.0, 426.0, 407.0, 369.0, 310.0, 297.0, 260.0, 241.0, 227.0, 226.0, 221.0, 220.0, 163.0, 128.0, 109.0, 101.0, 99.0, 89.0, 83.0, 77.0, 56.0, 37.0, 33.0, 30.0, 18.0, 8.0, 6.0, 6.0, 5.0, 5.0, 4.0, 4.0, 4.0, 3.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]," +
                "orientation: 'v'," +
                "xaxis: 'x'," +
                "yaxis: 'y'," +
                "type: 'bar'," +
                "name: ''," +
                "};" +
                "" +
                "        var data = [ trace0];" +
                "Plotly.newPlot(target_target, data, layout);            </script>"+"<div>aaaaaaa</div>"  + "</div>";// +
//                "\n" +
//                "</body>\n" +
//                "</html>";
        return s;
    }

    @Override
    public String graphTitle() {
        return "Pie chart graph Title";
    }

    @Override
    public String graphDescription() {
        return "This will display the percent of data in different places at the same time";
    }
}
