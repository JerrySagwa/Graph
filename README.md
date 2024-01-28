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







































