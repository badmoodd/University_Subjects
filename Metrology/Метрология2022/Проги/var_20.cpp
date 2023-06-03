#include <iostream>
#include <stdio.h>
#include <string.h>
#include <windows.h>
using namespace std;
int main()
{
	setlocale(LC_ALL, "Russian");
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	int n,i;
	cin >> n;
	struct s //список данных
	{
		char* marka = new char [10];
		short god;
		char* obiem = new char [5];
		short scorost;
	}l/*переменная для перемещения данных*/, * car = new s[n];
	for ( i = 0; i < n; i++)//вводим значения
	{
		cout << "№"<<i + 1 << '\n';
		cout << "Введите марку ";
		cin >> car[i].marka;
		cout << "Введите год выпуска ";
		cin >> car[i].god;
		cout << "Введите объём ";
		cin >> car[i].obiem;
		cout << "Введите скорость ";
		cin >> car[i].scorost;
	}
	for (i = 0; i < n - 1; i++)
	{
		for (int j = i + 1; j < n; j++)
			if(car[i].scorost < car[j].scorost)//делаем в порядке убывания по скорости
			{
				/*l = car[j];
				car[j] = car[i];
				car[i] = l;*/
					l.scorost = car[j].scorost;
				car[j].scorost = car[i].scorost;
				car[i].scorost = l.scorost;

				l.marka = car[j].marka;
				car[j].marka = car[i].marka;
				car[i].marka = l.marka;

				l.god = car[j].god;
				car[j].god = car[i].god;
				car[i].god = l.god;

				l.obiem = car[j].obiem;
				car[j].obiem = car[i].obiem;
				car[i].obiem = l.obiem;

			}
	}
	cout << "марка" << '\t' << "год" << '\t' << "объём" << '\t' << "скорость";//выводим значения
	for ( i = 0; i < n; i++)
		if(car[i].god >= 2005)
	{
		cout << '\n';
		cout << car[i].marka << '\t';
		cout << car[i].god << '\t';
		cout << car[i].obiem << '\t';
		cout << car[i].scorost << '\t';
	}
	delete[]car;
	return 0;
}