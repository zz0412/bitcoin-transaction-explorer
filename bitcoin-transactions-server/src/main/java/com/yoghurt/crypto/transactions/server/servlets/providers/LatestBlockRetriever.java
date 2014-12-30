package com.yoghurt.crypto.transactions.server.servlets.providers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class LatestBlockRetriever {
  private static final long INITIAL_DELAY = 1;
  private static final long BLOCK_RETRIEVE_DELAY = 30;

  private final AtomicReference<String> atomicLastBlockHash = new AtomicReference<>();

  private final ScheduledExecutorService executor;

  private final Runnable command = new Runnable() {
    @Override
    public void run() {
      final String lastBlockHash = hook.getLatestBlockHash();

      if (lastBlockHash != null) {
        atomicLastBlockHash.set(lastBlockHash);
      }
    }
  };

  private final BlockchainRetrievalHook hook;

  public LatestBlockRetriever(final BlockchainRetrievalHook hook) {
    this.hook = hook;
    executor = Executors.newSingleThreadScheduledExecutor();
  }

  public void start() {
    executor.scheduleWithFixedDelay(command, INITIAL_DELAY, BLOCK_RETRIEVE_DELAY, TimeUnit.SECONDS);
  }

  public String getLatestHash() {
    return atomicLastBlockHash.get();
  }

  public void stop() {
    executor.shutdownNow();
  }
}
