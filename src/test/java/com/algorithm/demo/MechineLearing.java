package com.algorithm.demo;

import com.algorithm.demo.dl4jexamples.MultiLayerAndIris;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MechineLearing {

    @Test
    public void init()
    {
        try {
            MultiLayerAndIris.train();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
