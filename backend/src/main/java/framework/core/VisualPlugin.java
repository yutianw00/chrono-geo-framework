package framework.core;

import framework.core.utils.MyData;

import java.util.*;

public interface VisualPlugin {
    String getName();

    String getIntro();

    String render(List<MyData> data);

    String graphTitle();

    String graphDescription();
}

