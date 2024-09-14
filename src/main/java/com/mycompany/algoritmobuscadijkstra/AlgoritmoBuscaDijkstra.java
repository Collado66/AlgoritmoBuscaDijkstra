/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.algoritmobuscadijkstra;

/**
 *
 * @author marce
 */

import java.util.*;

class Grafo {
    private int numVertices; // Número de vértices
    private LinkedList<Aresta> adjList[]; // Lista de adjacência com custo

    // Construtor
    Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Adiciona uma aresta ao grafo com custo
    void adicionarAresta(int origem, int destino, int custo) {
        adjList[origem].add(new Aresta(destino, custo));
        adjList[destino].add(new Aresta(origem, custo)); // Para grafos não direcionados
    }

    // Realiza a busca de Dijkstra a partir de um vértice fonte
    void dijkstra(int inicio) {
        // PriorityQueue para armazenar os nós com base no custo
        PriorityQueue<Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.custo));

        // Array para armazenar o custo mínimo para chegar a cada vértice
        int[] custo = new int[numVertices];
        Arrays.fill(custo, Integer.MAX_VALUE);
        custo[inicio] = 0;

        // Inicializa a fila de prioridade com o vértice inicial
        pq.add(new Aresta(inicio, 0));

        while (!pq.isEmpty()) {
            Aresta atual = pq.poll();
            int vertice = atual.destino;

            // Processa todos os vizinhos do vértice atual
            for (Aresta aresta : adjList[vertice]) {
                int novoCusto = custo[vertice] + aresta.custo;
                if (novoCusto < custo[aresta.destino]) {
                    custo[aresta.destino] = novoCusto;
                    pq.add(new Aresta(aresta.destino, novoCusto));
                }
            }
        }

        // Exibe o custo mínimo para alcançar cada vértice
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Custo mínimo para alcançar o vértice " + i + " é " + custo[i]);
        }
    }

    // Classe interna para representar uma aresta com custo
    private static class Aresta {
        int destino;
        int custo;

        Aresta(int destino, int custo) {
            this.destino = destino;
            this.custo = custo;
        }
    }
}

public class AlgoritmoBuscaDijkstra {
    public static void main(String[] args) {
        Grafo g = new Grafo(5);

        g.adicionarAresta(0, 1, 10);
        g.adicionarAresta(0, 4, 3);
        g.adicionarAresta(1, 2, 2);
        g.adicionarAresta(1, 4, 4);
        g.adicionarAresta(2, 3, 9);
        g.adicionarAresta(3, 4, 7);
        g.adicionarAresta(4, 2, 6);

        System.out.println("Algoritmo de Dijkstra a partir do vértice 0:");

        g.dijkstra(0);
    }
}
