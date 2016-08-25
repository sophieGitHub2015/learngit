package com.putao.nlp.ahocorasick.analysis.trie.bintrie;

/**
 * 对值数组的包装，可以方便地取下一个
 * @author
 */
public class _ValueArray<V>
{
    V[] value;
    int offset;

    public _ValueArray(V[] value)
    {
        this.value = value;
    }

    public V nextValue()
    {
        return value[offset++];
    }

    /**
     * 仅仅给子类用，不要用
     */
    protected _ValueArray()
    {
    }

    public _ValueArray setValue(V[] value)
    {
        this.value = value;
        return this;
    }
}
