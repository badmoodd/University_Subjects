#include <iostream>
using namespace std;
int main() {
	int n, m, i, j, jmin = 0, min, t;
	cout << "Vvedite kolichestvo strok ";
	cin >> n;
	cout << "Vvedite kolichestvo stolbzov ";
	cin >> m;
	int** matr = new int* [n]; //задаЄтс€ двумерный динамический массив 

	for (int i = 0; i < n; i++) { // перебирает каждый элемент 
		matr[i] = new int[m];
	}
	cout << "Vvedite elementu ";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> matr[i][j]; //ввожу элемент матрицы и она создаЄтс€ 
		}
	}

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++) {
			cout << matr[i][j] << " ";
		}
		cout << endl; // выводитс€ в матричном виде
	}
	min = matr[0][0];
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			if (matr[i][j] < min)
			{
				min = matr[i][j];
				jmin = j; j--; // перебираем каждый элемент и ищем минимальное значение и запоминаем индекс элемента
			}
		}
	}
	cout << jmin << endl;
	for (i = 0; i < n; i++)
	{
		t = matr[i][jmin];
		matr[i][jmin] = matr[i][m - 1];
		matr[i][m - 1] = t; // мен€ю элементы столбца с минимальным значением на элементы последнего столбца 
	}
	cout << " " << endl;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++) {
			cout << matr[i][j] << " "; // выводитс€ матрица, полученна€ в результате замены 
		}
		cout << endl; 
	}
	
	for (int i = 0; i < n; i++)
		delete matr[i];
	delete[]matr;
	matr = nullptr; // обнул€ю
	return 0;
}