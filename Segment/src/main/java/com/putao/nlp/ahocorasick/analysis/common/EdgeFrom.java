package com.putao.nlp.ahocorasick.analysis.common;

/**
 * 记录了起点的边
 * @author
 */
public class EdgeFrom extends Edge
{
    public int from;

    public EdgeFrom(int from, double weight, String name)
    {
        super(weight, name);
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "EdgeFrom{" +
                "from=" + from +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
