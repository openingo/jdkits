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

package org.openingo.jdkits.tree;

import org.openingo.jdkits.collection.ListKit;
import org.openingo.jdkits.lang.StrKit;
import org.openingo.jdkits.validate.AssertKit;
import org.openingo.jdkits.validate.ValidateKit;

import java.util.Comparator;
import java.util.List;

/**
 * TreeBuilder
 *
 * @author Qicz
 */
public final class TreeBuilder {

    private TreeBuilder(){}

    /**
     * 解析树形数据
     * <p>包含root节点，root节点为非0或null<p/>
     *
     * @param nonNullRootNodeId      顶层节点id
     * @param entityList 节点数据集合
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(String nonNullRootNodeId,
                                                             List<E> entityList) {
        return buildTree(nonNullRootNodeId, entityList, true, null);
    }

    /**
     * 解析树形数据
     * <p>包含root节点，root节点为非0或null<p/>
     *
     * @param nonNullRootNodeId      顶层节点id
     * @param entityList 节点数据集合
     * @param comparator 自定义比较器
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(String nonNullRootNodeId,
                                                             List<E> entityList,
                                                             Comparator<? super E> comparator) {
        return buildTree(nonNullRootNodeId, entityList, true, comparator);
    }

    /**
     * 解析树形数据
     *
     * @param nonNullRootNodeId      顶层节点id，非0或null
     * @param entityList 节点数据集合
     * @param hasRoot    是否包含root节点
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(String nonNullRootNodeId,
                                                             List<E> entityList,
                                                             boolean hasRoot) {
        return buildTree(nonNullRootNodeId, entityList, hasRoot, null);
    }

    /**
     * 解析树形数据
     *
     * @param nonNullRootNodeId      顶层节点id，非0或null
     * @param entityList 节点数据集合
     * @param hasRoot    是否包含root节点
     * @param comparator 自定义比较器
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(String nonNullRootNodeId,
                                                             List<E> entityList,
                                                             boolean hasRoot,
                                                             Comparator<? super E> comparator) {
        AssertKit.isFalse(ValidateKit.isNull(nonNullRootNodeId) || StrKit.equalsAny(nonNullRootNodeId, "0", "null"), "nonNullRootNodeId cannot be null or 0 or \"null\".");
        return toTree(nonNullRootNodeId, entityList, hasRoot, comparator);
    }

    /**
     * Root Id Node
     */
    public enum RootNode {
        ZERO_ID("0"), // id is 0
        NULL_ID("null"); // id is null

        private String id;
        RootNode(String id) {
            this.id = id;
        }
    }

    /**
     * 解析树形数据
     * <p>包含root节点<p/>
     *
     * @param entityList 节点数据集合
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(RootNode rootNode, List<E> entityList) {
        return toTree(rootNode.id, entityList, true, null);
    }

    /**
     * 解析树形数据
     * <p>包含root节点<p/>
     *
     * @param entityList 节点数据集合
     * @param comparator 自定义比较器
     * @return 树形结构数据
     */
    public static <E extends ITreeNode<E>> List<E> buildTree(RootNode rootNode,
                                                             List<E> entityList,
                                                             Comparator<? super E> comparator) {
        return toTree(rootNode.id, entityList, true, comparator);
    }

    /**
     * 解析树形数据
     *
     * @param rootNodeId      顶层节点id
     * @param entities 节点数据集合
     * @param hasRoot    是否包含root节点
     * @param comparator          自定义比较器
     * @return 树形结构数据
     */
    private static <E extends ITreeNode<E>> List<E> toTree(String rootNodeId,
                                                           List<E> entities,
                                                           boolean hasRoot,
                                                           Comparator<? super E> comparator) {
        List<E> retTree = ListKit.emptyArrayList();
        // 获取顶层元素集合
        if (StrKit.isBlank(rootNodeId)) {
            // 传入rootNodeId为null或者为0,不处理hasRoot
            entities.stream()
                    .filter(entity -> (StrKit.isBlank(entity.nodeParentId()) ))
                    .forEach(retTree::add);
        } else if (StrKit.equalsAny(rootNodeId, "0", "null")) {
            entities.stream()
                    .filter(entity -> rootNodeId.equals(entity.nodeParentId()))
                    .forEach(retTree::add);
        } else {
            if (hasRoot) {
                // 包含顶层节点自身
                entities.stream()
                        .filter(entity -> rootNodeId.equals(entity.nodeId()))
                        .forEach(retTree::add);
            } else {
                entities.stream()
                        .filter(entity -> rootNodeId.equals(entity.nodeParentId()))
                        .forEach(retTree::add);
            }
        }
        if (ValidateKit.isEmpty(retTree)) {
            return retTree;
        }

        if (ValidateKit.isNotNull(comparator)) {
            retTree.sort(comparator);
        }
        // 获取每个顶层元素的子数据集合
        retTree.forEach(entity -> entity.putChildNodes(getChildNodes(entity.nodeId(), entities, comparator)));
        return retTree;
    }

    /**
     * 获取子数据集合
     *
     * @param nodeParentId    节点pid
     * @param entityList 节点数据集合
     * @param comparator          自定义比较器
     * @return 子数据集合
     */
    private static <E extends ITreeNode<E>> List<E> getChildNodes(String nodeParentId,
                                                                  List<E> entityList,
                                                                  Comparator<? super E> comparator) {
        List<E> childNodes = ListKit.emptyArrayList();
        // 子集的直接子对象
        entityList.stream()
                .filter(entity -> nodeParentId.equals(entity.nodeParentId()))
                .forEach(childNodes::add);
        // 排序
        if (ValidateKit.isNotNull(comparator)) {
            childNodes.sort(comparator);
        }
        // 子集的间接子对象,递归调用
        childNodes.forEach(entity -> entity.putChildNodes(getChildNodes(entity.nodeId(), entityList, comparator)));
        // 递归退出条件
        return childNodes;
    }
}
