#include<iostream>
#include<cmath>

using namespace std;
	
int main()

{
	int a[7], i = 0, j = 0, min = 0, max = 0; 

	for (i = 0; i < 7; i++)
	{
		cin >> a[i]; // Ввод матрицы
	}

	for (i = 0; i < 7; i++)
	{
		if (a[min] > a[i])
		{
			min = i;
		}
		if (a[max] < a[i])
		{
			max = i;
		}
	}


	for (i = 0, j = 0; i < 7; i++, j++)
	{
		if (i != min && i != max)
		{
			a[j] = a[i];
		}
		else 
		{ 
			j--;
		}
	}

	for (j = 0; j < 5; j++)
	{
		cout << a[j] << " "; // Вывод сообщения
	}

	return 0;
}