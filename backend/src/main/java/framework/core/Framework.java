package framework.core;

import framework.gui.State;

public interface Framework {

    void sortData();

    void groupData();

    void chooseDataPlugin(int id);

    void chooseVisualPlugin(int id);

    void registerDataPlugin(DataPlugin dataPlugin);

    void registerVisualPlugin(VisualPlugin visualPlugin);

    void restart();

    void importData(String str);

    void render();

    State getState();
}
