#include <iostream>
using namespace std;
int main() {
	int n, m, i, j, jmin = 0, min, t;
	cout << "Vvedite kolichestvo strok ";
	cin >> n;
	cout << "Vvedite kolichestvo stolbzov ";
	cin >> m;
	int** matr = new int* [n]; //������� ��������� ������������ ������ 

	for (int i = 0; i < n; i++) { // ���������� ������ ������� 
		matr[i] = new int[m];
	}
	cout << "Vvedite elementu ";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> matr[i][j]; //����� ������� ������� � ��� �������� 
		}
	}

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++) {
			cout << matr[i][j] << " ";
		}
		cout << endl; // ��������� � ��������� ����
	}
	min = matr[0][0];
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			if (matr[i][j] < min)
			{
				min = matr[i][j];
				jmin = j; j--; // ���������� ������ ������� � ���� ����������� �������� � ���������� ������ ��������
			}
		}
	}
	cout << jmin << endl;
	for (i = 0; i < n; i++)
	{
		t = matr[i][jmin];
		matr[i][jmin] = matr[i][m - 1];
		matr[i][m - 1] = t; // ����� �������� ������� � ����������� ��������� �� �������� ���������� ������� 
	}
	cout << " " << endl;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++) {
			cout << matr[i][j] << " "; // ��������� �������, ���������� � ���������� ������ 
		}
		cout << endl; 
	}
	
	for (int i = 0; i < n; i++)
		delete matr[i];
	delete[]matr;
	matr = nullptr; // �������
	return 0;
}