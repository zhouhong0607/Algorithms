package introductiontoalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 图练习
 * 
 * @author Administrator
 *
 */
public class Graph
{
	List<Vertex> vertexsList;// 顶点容器
	List<Edge> edgeList;// 边 容器

	public Graph()
	{

		vertexsList = new LinkedList<>();
		edgeList = new LinkedList<>();
	}

	/**
	 * 返回与v相连的所有边
	 * 
	 * @param v
	 * @return
	 */
	public List<Edge> adjacentEdge(Vertex v)
	{
		List<Edge> adEdges = new LinkedList<>();
		Iterator<Edge> iterator = edgeList.iterator();
		while (iterator.hasNext())
		{
			Edge edge = iterator.next();
			if (edge.contain(v))// 如果这条边里有一端顶点是v则把这条边加入到容器中
				adEdges.add(edge);
		}

		return adEdges;
	}

	/**
	 * 获得v相连的其他顶点
	 * 
	 * @param v
	 * @return
	 */
	public List<Vertex> adjacentVertex(Vertex v)
	{
		List<Edge> adEdges = this.adjacentEdge(v);// 获得相邻的边
		List<Vertex> vertexs = new LinkedList<>();

		Iterator<Edge> iterator = adEdges.iterator();
		while (iterator.hasNext())
		{
			Edge edge = iterator.next();
			vertexs.add(edge.start == v ? edge.end : edge.start);
		}
		return vertexs;
	}

	public static void main(String[] args) throws Exception
	{
			
		
		
		Stack<String> s;
		Graph graph = new Graph();
		graph.vertexsList = new LinkedList<>();
		Vertex vertex1 = new Vertex(1);
		Vertex vertex2 = new Vertex(2);
		Vertex vertex3 = new Vertex(3);
		Vertex vertex4 = new Vertex(4);
		Vertex vertex5 = new Vertex(5);

		Edge edge1 = new Edge(vertex1, vertex2, 1);
		Edge edge2 = new Edge(vertex1, vertex5, 1);
		Edge edge3 = new Edge(vertex5, vertex2, 1);
		Edge edge4 = new Edge(vertex5, vertex4, 1);
		Edge edge5 = new Edge(vertex2, vertex4, 1);
		Edge edge6 = new Edge(vertex2, vertex3, 1);
		Edge edge7 = new Edge(vertex4, vertex3, 1);
		// 加入顶点
		graph.vertexsList.add(vertex1);
		graph.vertexsList.add(vertex2);
		graph.vertexsList.add(vertex3);
		graph.vertexsList.add(vertex4);
		graph.vertexsList.add(vertex5);
		// 将边加入
		graph.edgeList.add(edge1);
		graph.edgeList.add(edge2);
		graph.edgeList.add(edge3);
		graph.edgeList.add(edge4);
		graph.edgeList.add(edge5);
		graph.edgeList.add(edge6);
		graph.edgeList.add(edge7);

		
		
		
//		graph.BFS(graph.vertexsList.get(0));
//		graph.printPath(vertex1, vertex4);
		
		graph.DFS();
	}

	/**
	 * 图节点
	 * 
	 * @author Administrator
	 *
	 */
	public static class Vertex
	{
		public final static int WHITE = 1;
		public final static int GRAY = 2;
		public final static int BLACK = 3;
		int value;
		int color;// 颜色
		int deep;// 深度
		Vertex precursor;// 前驱

		public Vertex(int v)
		{
			value = v;

		}
	}

	/**
	 * 边类
	 * 
	 * @author Administrator
	 *
	 */
	public static class Edge
	{
		Vertex start;// 头
		Vertex end;// 尾
		int weight;// 权重

		public Edge(Vertex s, Vertex e, int w)
		{
			start = s;
			end = e;
			weight = w;
		}

		/**
		 * 判断这条边的两个顶点是否有v
		 * 
		 * @param v
		 * @return
		 */
		public boolean contain(Vertex v)
		{
			if (this.start == v || this.end == v)
				return true;
			return false;
		}

	}

	/**
	 * 广搜 同时生成广度优先树，根节点是 s
	 * 
	 * @param s
	 */
	private void BFS(Vertex s)
	{
		Iterator<Vertex> iterator = this.vertexsList.iterator();
		while (iterator.hasNext())
		{
			Vertex v = iterator.next();
			v.color = Vertex.WHITE;
			v.deep = 10000;
			v.precursor = null;
		}

		s.color = Vertex.GRAY;
		s.deep = 0;
		s.precursor = null;

		Vertex[] queue = new Vertex[this.vertexsList.size()];// 构建队列
		int front = 0;// 队列头
		int rear = 0;// 队列尾
		// s插入队列
		queue[rear] = s;
		rear++;
		// 队列不为空则 继续搜索
		while (rear != front)
		{
			// 出队列;
			Vertex u = queue[front];
			System.out.println(u.value + ",");

			front = (front + 1) % queue.length;

			Iterator<Vertex> aiIterator = this.adjacentVertex(u).iterator();

			while (aiIterator.hasNext())
			{
				Vertex v = aiIterator.next();
				if (v.color == Vertex.WHITE)// 找到一个新的
				{
					v.color = Vertex.GRAY;
					v.deep = u.deep + 1;
					v.precursor = u;
					// 将V插入队列
					queue[rear] = v;
					rear = (rear + 1) % queue.length;
				}

			}
			u.color = Vertex.BLACK;

		}

	}

	private void DFS()
	{
		Iterator<Vertex> iterator=this.vertexsList.iterator();
		while(iterator.hasNext())
		{
			Vertex v=iterator.next();
			v.color=Vertex.WHITE;
			v.precursor=null;
		}
		iterator=this.vertexsList.iterator();
		while(iterator.hasNext())
		{
			Vertex u=iterator.next();
			if(u.color==Vertex.WHITE)
				DFSVisit(this,u);
		}
		
	}
	
	private void  DFSVisit(Graph graph,Vertex u)
	{
		
		System.out.println(u.value);
		
		u.color=Vertex.GRAY;
		Iterator<Vertex> iterator=graph.adjacentVertex(u).iterator();
		while(iterator.hasNext())
		{
			Vertex v=iterator.next();
			if(v.color==Vertex.WHITE)
			{
				v.precursor=u;
				DFSVisit(this, v);
			}
		}
		u.color=Vertex.BLACK;
		
	}
	
	
	/**
	 * 打印s->v的最短路径
	 * 
	 * @param s
	 * @param v
	 */
	private void printPath(Vertex s, Vertex v)
	{
		if (s == v)
			System.out.println(v.value + "->");
		else if (v.precursor == null)
		{
			System.out.println("不存在这条路径");
		} else

		{
			printPath(s, v.precursor);
			System.out.println(v.value + "->");
		}
	}

}
