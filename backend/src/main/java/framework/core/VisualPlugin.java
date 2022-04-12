package framework.core;

import java.util.*;

public interface VisualPlugin {
    /**
     * Transform the output data from the framework to be ready to render in the front-end.
     * Store the answer to this.outPut
     * @param input input from the framework
     */
    public void transform(List<Integer> input);

    /**
     * First step to transform the data from the framework to the intermediateState.
     * @param input
     * @return
     */
    public List<Integer> tranData(List<Integer> input);

    /**
     * Second step to transform the data into suitable state that can be render in the front end.
     * @param intermediateState intermediate state of output
     * @return List of String to be render in the front-end
     */
    public List<String> outPutData(List<Integer> intermediateState);

    /**
     * Potential transformation of intermediate state.
     */
    public void helper();
}

