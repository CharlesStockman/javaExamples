package org.charlesStockman.threads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUtils {
    /**
     * Test the ability to find a thread by its name
     */
    @Test
    public void findThreadByName() {
        String name = "Finalizer";

        Thread thread = Utils.findThreadByName(name);
        Assertions.assertNotNull(thread);
        Assertions.assertSame(thread.getName(), name);
    }


}
