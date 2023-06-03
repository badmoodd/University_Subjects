#include <iostream>
#include <vector>
#include <set>

using namespace std;

void find_articulation_points(vector<int> graph[], vector<int>& disc, vector<int>& low, vector<bool>& visited, vector<int>& parent, set<int>& result, int v);

int timer = 0;

int main() {
    int number_of_vertexes, number_of_edges;

    cout << "Введите общее количество вершин >>> ";
    cin >> number_of_vertexes;
    cout <<"Введите общее количество всех ребер >>> ";
    cin >> number_of_edges;

    vector<int> graph[number_of_vertexes];
    vector<bool> visited(number_of_vertexes, false);
    vector<int> parent(number_of_vertexes, -1);
    vector<int> low(number_of_vertexes, 0);
    vector<int> disc(number_of_vertexes, 0);


    for (int i=0; i < number_of_edges; i++) {
        cout << "Введите " << i+1 <<" ребро, которое соединяет две вершины через пробел >>> ";
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    set<int> result;
    find_articulation_points(graph, disc, low, visited, parent, result, 0);

    if (result.empty()){
        cout << endl <<"Данный граф является двусвязным";
    }

    if (!result.empty()){
        cout << endl << "Данный граф не является двусвязным "<<endl << "Точками сочленения в графе являются:  ";
    }

    for ( const int &i : result)
        cout << i << " ";
    cout << endl;
    return 0;
}

void find_articulation_points(vector<int> graph[], vector<int>& disc, vector<int>& low,vector<bool>& visited, vector<int>& parent, set<int>& result,int v){

    visited[v] = true;
    timer++;
    low[v] = disc[v] = timer;
    int child = 0;
    for (auto i=graph[v].begin(); i != graph[v].end(); i++) {
        int av = *i;
        if (!visited[av]) {
            child++;
            parent[av] = v;
            find_articulation_points(graph, disc, low, visited, parent, result, av);
            low[v] = min (low[v], low[av]);
            if (parent[v] == -1 and child > 1)
                result.insert(v);
            else if (parent[v] != -1 and disc[v]<=low[av])
                result.insert(v);
        }
        else if (av != parent[v])
            low[v] = min (low[v], disc[av]);
    }
}