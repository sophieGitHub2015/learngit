package com.putao.nlp.ahocorasick.analysis.trie.bintrie;

/**
 * @author
 */
public class _EmptyValueArray<V> extends _ValueArray<V>
{
    public _EmptyValueArray()
    {
    }

    @Override
    public V nextValue()
    {
        return null;
    }
}
