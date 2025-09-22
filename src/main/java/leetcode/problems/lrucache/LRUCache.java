package leetcode.problems.lrucache;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LRUCache {
  private static final Logger LOGGER = LogManager.getLogger();

  private final int capacity;
  private final Map<Integer, IntNode> lruCache;

  private IntNode first;
  private IntNode last;

  public LRUCache(final int capacity) {
    this.capacity = capacity;
    this.lruCache = new HashMap<>();
    LOGGER.info("Capacity {}", capacity);
  }

  public int get(final int key) {
    LOGGER.info("GET: get key {}", key);
    if (this.lruCache.containsKey(key)) {
      LOGGER.info("LRU Cache contains key, moving corresponding node to front");
      IntNode node = this.lruCache.get(key);
      moveNodeToFront(node);
      this.printStateOfLruTracker();
      this.printFirstAndLast();
      LOGGER.debug("Returning {}", node.value);
      return node.value;
    } else {
      LOGGER.info("LRU Cache does not contain key");
      LOGGER.debug("Returning -1");
      return -1;
    }
  }

  public void put(final int key, final int value) {
    LOGGER.info("PUT: putting <{},{}>", key, value);
    if (this.lruCache.containsKey(key)) {
      LOGGER.info(
          "LRU Cache already contains key, updating value and moving corresponding node to front");
      IntNode nodeToUpdate = this.lruCache.get(key);
      nodeToUpdate.value = value;
      this.moveNodeToFront(nodeToUpdate);
      this.lruCache.put(key, nodeToUpdate);
    } else if (this.lruCache.size() < this.capacity) {
      LOGGER.info("LRU Cache does not contain key and is under capacity, proceeding...");
      IntNode newNode = new IntNode(key, value);
      this.addNodeToFront(newNode);
      this.lruCache.put(key, newNode);
    } else {
      LOGGER.info(
          "LRU Cache does not contain key and is at capacity, must evict the least recently used entry before proceeding...");
      LOGGER.debug("Evicting <{},{}>", this.last.key, this.last.value);
      this.lruCache.remove(this.last.key);
      this.evictLeastRecentlyUsedNode();
      IntNode newNode = new IntNode(key, value);
      this.addNodeToFront(newNode);
      this.lruCache.put(key, newNode);
    }
    this.printStateOfLRUCache();
    this.printStateOfLruTracker();
    this.printFirstAndLast();
  }

  private void evictLeastRecentlyUsedNode() {
    if (this.capacity == 1) {
      this.first = null;
      this.last = null;
    } else {
      IntNode nodeToEvict = this.last;
      this.last = nodeToEvict.prev;
      this.last.next = null;
      nodeToEvict.prev = null;
    }
  }

  private void addNodeToFront(final IntNode node) {
    if (this.first == null) {
      this.last = node;
    } else {
      node.next = this.first;
      this.first.prev = node;
    }
    this.first = node;
  }

  private void moveNodeToFront(final IntNode node) {
    if (node.prev != null) {
      if (node.next != null) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
      } else {
        node.prev.next = null;
        this.last = node.prev;
      }
      node.prev = null;
      node.next = this.first;
      this.first.prev = node;
      this.first = node;
    }
  }

  private void printStateOfLRUCache() {
    StringBuilder log = new StringBuilder();
    log.append("LRU Cache: {");
    if (this.lruCache.isEmpty()) {
      log.append("}");
    } else {
      for (Map.Entry<Integer, IntNode> entry : this.lruCache.entrySet()) {
        log.append("<")
            .append(entry.getKey())
            .append(",")
            .append(entry.getValue().value)
            .append(">,");
      }
      log.deleteCharAt(log.length() - 1);
      log.append("}");
    }
    LOGGER.debug(log.toString());
  }

  private void printStateOfLruTracker() {
    StringBuilder log = new StringBuilder();
    log.append("LRU Tracker: [");
    if (this.first != null) {
      IntNode node = this.first;
      do {
        log.append("<").append(node.key).append(",").append(node.value).append(">");
        if (node.next != null) {
          log.append(", ");
        }
        node = node.next;
      } while (node != null);
    }
    log.append("]");
    LOGGER.debug(log.toString());
  }

  private void printFirstAndLast() {
    StringBuilder log = new StringBuilder();
    if (this.first == null) {
      log.append("First: null\t");
    }
    if (this.first != null) {
      log.append("First: <")
          .append(this.first.key)
          .append(",")
          .append(this.first.value)
          .append(">\t");
    }
    if (this.last == null) {
      log.append("Last: null");
    }
    if (this.last != null) {
      log.append("Last: <").append(this.last.key).append(",").append(this.last.value).append(">");
    }
    LOGGER.debug(log.toString());
  }

  private static class IntNode {
    int key;
    int value;
    IntNode prev;
    IntNode next;

    public IntNode(final int key, final int value) {
      this.key = key;
      this.value = value;
    }
  }
}
