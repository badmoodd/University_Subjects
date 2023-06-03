#include<iostream>
#include<cmath>

using namespace std;

int main()

{
	int n, m, i = 0, j = 0, mini = 0, maxi = 0, minj = 0, maxj = 0, t;

	double** a;

	cout << "Vvedite n=";  // Вывод сообщения
	cin >> n;
	cout << "Vvedite m=";  // Вывод сообщения
	cin >> m;

	a = new double* [n];

	for(i = 0; i < n; i++)
	{
		a[i] = new double[m];
	}

	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			cin >> a[i][j];
		}
	}		

	cout << endl; // Вывод сообщения

	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			if (a[mini][minj] > a[i][j])
			{
				mini = i;
				minj = j;
			}
		}
	}

	for (i = 0; i < n; i++)
	{
		delete[]a[i];
	}

	delete[]a;
	a = nullptr;

	return 0;
}