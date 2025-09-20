package leetcode.problems.lrucache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LRUCache {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final Integer SEPARATOR = 999999;
  private final LinkedList<Integer> lruTracker = new LinkedList<>();
  private final Map<Integer, Integer> lruCache = new HashMap<>();
  private final int capacity;

  public LRUCache(final int capacity) {
    this.capacity = capacity;
  }

  public int get(final int key) {
    LOGGER.debug("Attempting to get value for key {}", key);
    this.printCurrentStateOfLRUCache();
    if (lruCache.containsKey(key)) {
      LOGGER.debug("LRU Cache contains key {}, retrieving value {}", key, lruCache.get(key));
      this.lruTracker.addFirst(key);
      // TODO Consider printing state of LRU Tracker
      return lruCache.get(key);
    } else {
      LOGGER.debug("LRU Cache does not contain key {}, returning -1", key);
      // TODO Consider printing state of LRU Tracker
      return -1;
    }
  }

  public void put(final int key, final int value) {
    LOGGER.debug("Attempting to put <{},{}>", key, value);
    this.printCurrentStateOfLRUCache();
    // If the LRU Cache is not full
    if (!isFull()) {
      if (!lruCache.containsKey(key)) {
        LOGGER.debug("LRU Cache does not contain key {}", key);
        LOGGER.debug("Putting <{},{}>", key, value);
      } else {
        LOGGER.debug("LRU Cache already contains value {} for key {}", lruCache.get(key), key);
        LOGGER.debug("Replacing with <{},{}>", key, value);
      }
    } else {
      // We have to evict the least recently used item to add our new item
      LOGGER.debug("Evicting the least recently used key {}", this.lruTracker.peekLast());
      int leastRecentlyUsedKey = this.lruTracker.removeLast();
    }
    lruCache.put(key, value);
    lruTracker.addFirst(key);
    this.printCurrentStateOfLRUCache();
  }

  private boolean isFull() {
    boolean isFull = this.lruTracker.size() == this.capacity;
    if (isFull) {
      LOGGER.debug(
          "LRU Tracker LinkedList is full (LRU Linked List Size: {}, Initial Capacity: {})",
          this.lruTracker.size(),
          this.capacity);
    } else {
      LOGGER.debug(
          "LRU Tracker LinkedList is not full(LRU Linked List Size: {}, Initial Capacity: {})",
          this.lruTracker.size(),
          this.capacity);
    }
    return isFull;
  }

  private void printCurrentStateOfLRUCache() {
    LOGGER.debug("Current state of LRU Cache...");
    if (this.lruCache.isEmpty()) {
      LOGGER.debug("EMPTY");
    }
    for (Map.Entry<Integer, Integer> entry : this.lruCache.entrySet()) {
      LOGGER.debug("Key: {}, Value: {}", entry.getKey(), entry.getValue());
    }
  }
}
