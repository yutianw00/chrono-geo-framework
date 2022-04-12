package framework.core;

public interface Framework {

    void sortData();

    void groupData();

    void registerDataPlugin(DataPlugin dataPlugin);

    void registerVisualPlugin(VisualPlugin visualPlugin);

    void restart();

    void importData();

    String render();
}
