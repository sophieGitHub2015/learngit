package com.putao.nlp.ahocorasick.analysis.trie;


import com.putao.nlp.ahocorasick.analysis.io.ByteArray;

import java.io.DataOutputStream;
import java.util.TreeMap;

/**
 * Trie Interface
 */
public interface ITrie<V>
{
    int build(TreeMap<String, V> keyValueMap);
    boolean save(DataOutputStream out);
    boolean load(ByteArray byteArray, V[] value);
    V get(char[] key);
    V[] getValueArray(V[] a);
    boolean containsKey(String key);
}
