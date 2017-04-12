package com.practice;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yugi
 * @apiNote
 * @since 2017-03-14
 */
public class GuiExecutor extends AbstractExecutorService {

    private static final GuiExecutor guiExecutor = new GuiExecutor();

    private GuiExecutor() {
    }

    public static GuiExecutor getInstance() {
        return guiExecutor;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void execute(Runnable command) {
        SwingUtilities.invokeLater(command);
    }
}
