# Cache

<ul>
This repository contains implementation of two caches:
<li>LFU - least frequently used</li>
<li>LRU - least recently used</li>
</ul>

<ul>
Caches are created by CacheFactory with two parameters:
<li>Capacity</li>
<li>Eviction strategy type</li>
</ul>

LRU is the default cache type.

Cache keys must implement equals() and hashCode() methods.
