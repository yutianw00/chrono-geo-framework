
import java.io.IOException;
import java.util.*;

import fi.iki.elonen.NanoHTTPD;
import framework.core.DataPlugin;
import framework.core.Framework;
import framework.core.FrameworkImpl;
import framework.core.VisualPlugin;
import framework.gui.State;
import plugin.dataPlugin.dummyDataPlugin.DummyDataPlugin;
import plugin.visualPlugin.dummyVisualPlugin.DummyVisualPlugin;

import javax.xml.crypto.Data;

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
        } else if (uri.equals("/visualplugin")) {
            System.out.println("Request received: choose visual plugin");
            app.chooseVisualPlugin((Integer.parseInt(params.get("id"))));
        } else if (uri.equals("/render")){
            app.render(params.get("str"));
        } else if (uri.equals("/reset")){
            app.restart();
        } else if (uri.equals("/init")){
            System.out.println("init render receives");
        }
        // Extract the view-specific data from the game and apply it to the template.
        State state = app.getState();
        System.out.println(state.toString());
        return newFixedLengthResponse(state.toString());

    }

    /**
     * Load data plugins listed in META-INF/services/framework.core.DataPlugin
     *
     * @return List of instantiated data plugins
     */
    private static List<DataPlugin> loadDataPlugins() {
        ServiceLoader<DataPlugin> plugins = ServiceLoader.load(DataPlugin.class);

        List<DataPlugin> result = new ArrayList<>();
        for (DataPlugin plugin : plugins) {
            System.out.println("Loaded DATA plugin " + plugin.getName());
            result.add(plugin);
        }
        return result;
    }

    /**
     * Load data plugins listed in META-INF/services/framework.core.VisualPlugin
     *
     * @return List of instantiated visual plugins
     */
    private static List<VisualPlugin> loadVisualPlugins() {
        ServiceLoader<VisualPlugin> plugins = ServiceLoader.load(VisualPlugin.class);

        List<VisualPlugin> result = new ArrayList<>();
        for (VisualPlugin plugin : plugins) {
            System.out.println("Loaded VISUAL plugin " + plugin.getName());
            result.add(plugin);
        }
        return result;
    }

    /*
    private static List<VisualPlugin> loadVisualPlugins() {
        // TODO
        List<VisualPlugin> plugins = new ArrayList<>();
        // TODO: change the mock data
        plugins.add(new DummyVisualPlugin());
        plugins.add(new DummyVisualPlugin());
        return plugins;
    }
    */

}

