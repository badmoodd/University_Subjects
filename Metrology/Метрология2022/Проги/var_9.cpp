#include <iostream>

using namespace std;

int main()

{
	int col;
	int size, i;
	double* array;

	cout << "Vvedite razmer:"; // Вывод сообщения

	cin >> size;

	array = new double[size];

	for (i = 0; i < size; i++)
	{

		cout << "array [" << i + 1 << " ]=";
		cin >> array[i];

	}
	col = 0;
	for (i = 0; i < size; i++)
		if (array[i] < array[i - 1])
		{
			cout << i;
			cout << endl;
			col++;
		}
	cout << col;  // Вывод сообщения
	 
	
	delete[] array;
	array = NULL;
	return 0;

}