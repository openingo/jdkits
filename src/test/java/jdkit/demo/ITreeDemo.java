/*
 * MIT License
 *
 * Copyright (c) 2020 OpeningO Co.,Ltd.
 *
 *    https://openingo.org
 *    contactus(at)openingo.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jdkit.demo;

import org.openingo.jdkits.json.FastJsonKit;
import org.openingo.jdkits.tree.ITreeNode;
import org.openingo.jdkits.tree.TreeBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * ITreeDemo
 *
 * @author Qicz
 */
public class ITreeDemo implements ITreeNode<ITreeDemo> {

    private Integer id;
    private Integer pid;
    private Integer order;
    private List<ITreeDemo> list;
    private Integer nodeCnt;

    public Integer getId() {
        return id;
    }

    public Integer getPid() {
        return pid;
    }

    public List<ITreeDemo> getList() {
        return list;
    }

    public Integer getOrder() {
        return order;
    }

    public Integer getNodeCnt() {
        return this.list.size();
    }

    public ITreeDemo(Integer id, Integer pid, Integer order) {
        this.id = id;
        this.pid = pid;
        this.order = order;
    }

    public static List<ITreeDemo> getInit() {
        return Arrays.asList(
                new ITreeDemo(11, 0, 1),
                new ITreeDemo(32, 11, 11),
                new ITreeDemo(2, 11,2),
                new ITreeDemo(5, 32, 1),
                new ITreeDemo(4, 32, 2));
    }

    public static void main(String[] args) {
        List<ITreeDemo> init = ITreeDemo.getInit();
        List<ITreeDemo> treeList = TreeBuilder.buildTree(TreeBuilder.RootNode.ZERO_ID, init, Comparator.comparingInt(o -> o.order));
        //treeList = TreeBuilder.buildTree("11", init, false, Comparator.comparingInt(o -> o.order));
        System.out.println(FastJsonKit.toJson(treeList));
    }

    /**
     * @return current node id
     */
    @Override
    public String nodeId() {
        return id.toString();
    }

    /**
     * @return current node parent id
     */
    @Override
    public String rootNodeId() {
        return pid.toString();
    }

    /**
     * @param childNodes loading child nodes
     */
    @Override
    public void putChildNodes(List<ITreeDemo> childNodes) {
        this.list = childNodes;
    }
}
