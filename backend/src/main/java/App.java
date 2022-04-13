
import java.io.IOException;
import java.util.*;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import fi.iki.elonen.NanoHTTPD;
import framework.core.DataPlugin;
import framework.core.Framework;
import framework.core.FrameworkImpl;
import framework.core.VisualPlugin;
import framework.gui.State;
import plugin.dataPlugin.dummyData.dummyData;

public class App extends NanoHTTPD {

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private Framework app;
//    private Template template;
    private List<DataPlugin> dataPlugins;
    private List<VisualPlugin> visualPlugins;

    public App() throws IOException {
        super(8080);

        this.app = new FrameworkImpl();
        dataPlugins = loadDataPlugins();
        visualPlugins = loadVisualPlugins();
        for (DataPlugin dp: dataPlugins){
            app.registerDataPlugin(dp);
        }

        for (VisualPlugin vp : visualPlugins) {
            app.registerVisualPlugin(vp);
        }

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Map<String, String> params = session.getParms();
        if (uri.equals("/dataplugin")) {
            System.out.println("Request received: choose data plugin");
            app.chooseDataPlugin((Integer.parseInt(params.get("id"))));
        } else if (uri.equals("render")){
            app.render();
        } else if (uri.equals("/start")){
            app.restart();
        }
        // Extract the view-specific data from the game and apply it to the template.
        State state = app.getState();
        System.out.println(state.toString());
        return newFixedLengthResponse(state.toString());

    }


    private static List<DataPlugin> loadDataPlugins() {
        List<DataPlugin> plugins = new ArrayList<>();
        // TODO: change the mock data
        plugins.add(new dummyData());
        plugins.add(new dummyData());
        return plugins;
    }

    private static List<VisualPlugin> loadVisualPlugins() {
        // TODO
        return new ArrayList<>();
    }

//    /**
//     * Load plugins listed in META-INF/services/...
//     *
//     * @return List of instantiated plugins
//     */
//    private static List<GamePlugin> loadPlugins() {
//        ServiceLoader<GamePlugin> plugins = ServiceLoader.load(GamePlugin.class);
//        List<GamePlugin> result = new ArrayList<>();
//        for (GamePlugin plugin : plugins) {
//            System.out.println("Loaded plugin " + plugin.getGameName());
//            result.add(plugin);
//        }
//        return result;
//    }
}

