#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;
int main()
{
	int  k/*строки*/, l/*столбцы*/, i, j;
	cout << "Zadaite kol-vo strok i stolbcov ";
	cin >> k >> l;
	int** a = new int*[k];//указатель на двухмерный массив, хранится указатель на строки, которые имеют указатель на столбцы
	int* p = new int[k];
	
	for (i = 0; i < k; i++) 
		a[i] = new int[l];//создаём указатель столбцов в строке
	for (i = 0; i < k; i++) //ввод матрицы
	{
		for (j = 0; j < l; j++) 
		{
			cout << "a[" << i << "][" << j << "]= ";
			cin >> a[i][j];
		}
	}
		for (i = 0; i < k; i++)//вывод матрицы
		{
			for (j = 0; j < l; j++)
			{
				cout << setw(3) << a[i][j] << " ";
			}
			cout << endl;
		}
	for (i = 0; i < k; i++)	/*Дана матрица.ПОЛУЧИТЬ одномерный массив, занося в ячейку 0, если строка матрицы
						   с таким же номером имеет хотя бы один 0, и 1 в обратном случае*/
	{
    for (j = 0; j < l; j++)
		if (a[i][j] == 0)
		{
		p[i] = 0;
		break;
		}
	    else p[i] = 1;
	}
	cout << "Otvet: ";
	for (i = 0; i < k; i++)//вывод конечного массива
	cout << "p["<<i<<"]= " << p[i] << setw(3);
	for (i = 0; i < k; i++) //чистим задейственную память в строках
		delete []a[i];
	delete[] a;//удаляем весь массив
	delete[] p;
	return 0;
}