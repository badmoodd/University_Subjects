#include <iostream>
#include <cmath>
using namespace std;
int main()
{
	int n;
	cout << "Enter koluchestvo elementov: ";
	cin >> n;

	int *array=new int [n]; // ������ ������������ ������
	cout << "Enter element ";

	for (int i = 0; i < n; i++) 
	{
		cin >> array[i]; // ������ � ���������� ������ ������ � ��� ������� ����� ������ ��� �������� 
	}

	cout << " array: ";

	for (int i = 0; i < n; i++) {
		cout << array[i] << " ";
	}

	int count;
	int max_count = 1;
	int max_count_index = 0;

	for (int i = 0; i < n; i++) {      //������� �������� �������
		count = 0;                    //��������� ������� ����� ������� ��� ������� �������� �������
		for (int j = 0; j < n; j++) {
			//���������� �������� ��� ���������� ��������
			if (array[i] == array[j]) {
				count++;
			}
		}
		//���������� ������������� ���-�� ����������
		if (count > max_count) 
		{
			max_count = count;
			max_count_index = i;
		}
	}

	cout << "Max count :" << max_count << " and value : " << array[max_count_index] << endl;

	delete[]array; 

	return 0;
	}
