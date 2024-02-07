# Graph Basics

*图的分类*

- 顶点 *Vertex*
- 边 *Edge*



- *Direction*
    - 无向图 *Undirected Graph*
    - 有向图 *Directed Graph*
- *Weigh*
    - 无权图
    - 有权图

> 没有自环边，没有平行边 ---> 简单图
>
> 联通分量：一个图中的所有节点不一定是全部相连的
>
> 是否有环 *loop*
>
> 无环图（🌲）
>
> 度 *degree*: 
>
> - 无向图：顶点相邻的边数
> - 有向图：入度，出度

## 图的基本表示



| 实现 | 空间 | 建图 | hasEdge | adj  |
| :-:-: | :-:-: | :--:--: | :--:--: | :-:-: |
| AdjMatrix | O(V^2) | O(E) | O(1) |O(V)|
| AdjList | O(V + E) | O(EV) | O(degree(v)) |O(degree(v))|
| AdjSet | O(V + E) | O(ElogV) | O(degree(v)) |O(degree(v))|





# DFS

复杂度 O(V + E)

对照二叉树的前序 & 后序遍历

有环：使用 visited 数据记录访问情况



# DFS Application

- 无向图的联通分量 *connected component*
    - 记录信息 -- ccid
    - 整张图有几个
    - 每个顶点分别在哪个联通分量

- 路径问题
    - 记录信息 -- pre
    - 单源路径 (非最短)

- 检测无向图的环
- 二分图检测
    - 记录信息 -- color
    - 顶点 V 可以分成不相交的两部分
    - 所有边的两个端点隶属于不同的部分




# BFS

> 利用队列，和树的BFS几乎一样，需要用 visited 数组避免元素重复入队

`O(V + E)`

- 连通图 E
- 非连通图 V

*应用*

- 单源最短路径
    - 使用 `pre` 数组



# floodfill

> 使用 DFS/BFS 解决二维网格的相关问题
> 
> LC : 200 1020 130 733 1034 529 827(hard)

```
// 1. 坐标映射
// 2D ==> 1D
// (x, y) ==> x*Y + y
// 1D ==> 2D
// M ==> (M / Y, M % Y)

// 2.四联通 dirs -- 用于遍历节点
//      八联通 dirs 包含8个方向

// 3.求联通分量CC中顶点的个数 recursive
```



# 图论搜索



无权图最短路径: BFS (LC 1091)

图论建模核心: *状态表达* (LC 752)

![image-20240207131249438](./README.assets/image-20240207131249438.png)

 *状态压缩*

>装水问题: 两桶水的状态，不同的位数表示不同的桶的水量

LC 773 表达六位数的状态

- int
- String 





 

