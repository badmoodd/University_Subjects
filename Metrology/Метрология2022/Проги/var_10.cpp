#include <iostream>

using namespace std;

int main()

{
	int size, i;
	double* array, temp;

	cout << "Vvedite razmer:"; // Вывод сообщения

	cin >> size;

	array = new double[size];

	for (i = 0; i < size; i++)
	{
		cout << "array [" << i + 1 << " ]=";
		cin >> array[i];

	}

	cout << "\nArray:\n";  // Вывод сообщения
	for (i = 0; i < size; i++)
		cout << array[i] << " ";
	cout << endl;

	for (i = 0; i < size / 2; i++)
	{
		temp = array[i];
		array[i] = array[size - i -1 ];
		array[size - i -1 ] = temp;
	}
	
	cout << "\nArray:\n"; // Вывод сообщения
	for (i = 0; i < size; i++)
		cout << array[i] << " ";
	cout << endl;

	delete[] array;
	array = NULL;
	return 0;

}