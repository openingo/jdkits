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
import org.openingo.jdkits.tree.TreeParser;

import java.util.Arrays;
import java.util.List;

/**
 * ITreeDemo
 *
 * @author Qicz
 */
public class ITreeDemo implements ITreeNode<ITreeDemo> {

    private Integer id;
    private Integer pid;
    private List<ITreeDemo> list;

    public Integer getId() {
        return id;
    }

    public Integer getPid() {
        return pid;
    }

    public List<ITreeDemo> getList() {
        return list;
    }

    public ITreeDemo(Integer id, Integer pid) {
        this.id = id;
        this.pid = pid;
    }

    public static List<ITreeDemo> getInit() {
        return Arrays.asList(
                new ITreeDemo(11, 0),
                new ITreeDemo(32, 11),
                new ITreeDemo(2, 11),
                new ITreeDemo(5, 32),
                new ITreeDemo(4, 32));
    }

    public static void main(String[] args) {
        List<ITreeDemo> init = ITreeDemo.getInit();
        List<ITreeDemo> treeList = TreeParser.getTreeList("0", init, true);
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
    public String nodeParentId() {
        return pid.toString();
    }

    /**
     * @param childNodes loading child nodes
     */
    @Override
    public void loadChildNodes(List<ITreeDemo> childNodes) {
        this.list = childNodes;
    }
}
