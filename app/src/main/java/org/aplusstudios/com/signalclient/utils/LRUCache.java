package org.aplusstudios.com.signalclient.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {

  private final int maxSize;

  public LRUCache(int maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  protected boolean removeEldestEntry (Entry<K,V> eldest) {
    return size() > maxSize;
  }
}
