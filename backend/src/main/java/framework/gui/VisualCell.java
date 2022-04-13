package framework.gui;

import framework.core.DataPlugin;
import framework.core.VisualPlugin;

public class VisualCell {

    String name;
    String description;
    String link;
    boolean chosen;

    public VisualCell(VisualPlugin vp) {
        this.name = vp.getName();
        this.description = vp.getIntro();
        this.chosen = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(int id) {
        this.link = "visualplugin?id=" + id;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public String toString() {
        return "{ \"name\": \"" + this.name + "\"," +
                " \"description\": \"" + this.description + "\"," +
                " \"chosen\": " + this.chosen + "," +
                " \"link\": \"" + this.link + "\"}";
    }
}
