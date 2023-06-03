
#include <iostream>
#include <cmath>

using namespace std;

int main() {
	setlocale(LC_ALL, "Russian");
	//объявление переменных
	int x;
	int y;
	int z;
	int k;
	int sum;
	double q;
	//инициализация переменных
	x = 8;
	y = 1e2;
	z = 10;
	k = -15;
	//сумма x + y + k + z
	sum = x + y + k + z;
	cout << sum;
	cout << endl;
	//деление
	q = y / (x + z + k);
	cout << q;
	cout << endl;

	for (int i = 0; i < z; i++) {
		cout << "Начало цикла ";
		//выведем i
		cout << "i=";
		cout << i;
		cout << endl;
		//выведем i*z
		cout << "i*z=";
		cout << i * z;
		cout << endl;
		cout << "Конец цикла";
		cout << endl;
	}
	//проверка
	if (sum > 0)
	{
		cout << "True";
	}
	if (q < 0)
	{
		cout << false;
	}
	return 0;
}
