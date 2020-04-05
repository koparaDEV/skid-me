package net.zell7z.mods.memoryfix;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemoryUtilization {
    public void run() {
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(() -> {
            System.out.println("[DropGuard] Cleaning a dump memory!");
            System.gc();
        }, 0, 5, TimeUnit.MINUTES);
    }
}
