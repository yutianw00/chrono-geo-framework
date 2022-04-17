package framework.core;

import framework.gui.DataCell;
import framework.gui.State;
import framework.gui.VisualCell;
import org.junit.Before;
import org.junit.Test;
import plugin.dataPlugin.dummyDataPlugin.DummyDataPlugin;
import plugin.visualPlugin.dummyVisualPlugin.DummyVisualPlugin;

import java.util.List;

import static org.junit.Assert.*;

public class FrameworkImplTest {

    Framework framework;

    @Before
    public void setUp() throws Exception {
        framework = new FrameworkImpl();
        DataPlugin dp1 = new DummyDataPlugin();
        DataPlugin dp2 = new DummyDataPlugin();
        VisualPlugin vp1 = new DummyVisualPlugin();
        framework.registerDataPlugin(dp1);
        framework.registerDataPlugin(dp2);
        framework.registerVisualPlugin(vp1);
    }

    @Test
    public void chooseDataPluginTestChosen() {
        framework.chooseDataPlugin(1);
        State state = framework.getState();
        assertTrue(state.getDataCells().get(1).isChosen());
    }

    @Test
    public void chooseDataPluginTestUnchosen() {
        framework.chooseDataPlugin(1);
        State state = framework.getState();
        assertFalse(state.getDataCells().get(0).isChosen());
    }


    @Test
    public void chooseVisualPlugin() {
        framework.chooseVisualPlugin(0);
        State state = framework.getState();
        assertTrue(state.getVisualCells().get(0).isChosen());
    }

    @Test
    public void getState() {
        framework.chooseVisualPlugin(0);
        framework.chooseDataPlugin(0);
        State state = framework.getState();
        List<DataCell> dataCellList = state.getDataCells();
        List<VisualCell> visualCellList = state.getVisualCells();
        assertTrue(dataCellList.size() == 2);
        assertTrue(visualCellList.size() == 1);
        assertTrue(dataCellList.get(0).isChosen());
        assertFalse(dataCellList.get(1).isChosen());
        assertTrue(visualCellList.get(0).isChosen());
        assert(state.getStatus().equals(""));
        assert(state.getErrMsg().equals(""));
    }

    @Test
    public void sortData() {
        framework.chooseDataPlugin(0);
        framework.chooseVisualPlugin(0);
        framework.importData("");
        framework.sortData();
        framework.render("");
    }

    @Test
    public void groupData() {
    }

    @Test
    public void restart() {
    }

    @Test
    public void render() {
    }
}