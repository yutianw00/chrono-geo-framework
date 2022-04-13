package framework.core;

import framework.gui.State;

public interface Framework {

    void sortData();

    void groupData();

    void chooseDataPlugin(int id);

    void registerDataPlugin(DataPlugin dataPlugin);

    void registerVisualPlugin(VisualPlugin visualPlugin);

    void restart();

    void importData();

    void render();

    State getState();
}
