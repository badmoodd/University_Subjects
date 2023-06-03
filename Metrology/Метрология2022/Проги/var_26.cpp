#include <iostream>
#include <Windows.h>
#include <iomanip>
using namespace std;
struct books
{
	char name[20];
	int pages;
	int age;
};
const int m = 20; 
const int n = 9;
void add(int, int, books*);
books search(int, books*);
books *H = new books[m];
books* b = new books[n];
int main()
{
	int key;
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	for (int i = 0; i < n; i++) 
	{
		cout << "Введите название, количество страниц и год издания книги " << i << ": ";
		cin >> b[i].name >> b[i].pages >> b[i].age;
	}
	for (int i = 0; i < m; i++) H[i].pages = -1;
	cout << "Создание хеш-таблицы" << endl;
	for (int i = 0; i < n; i++)
	{
		key = b[i].pages;
		add(i, key, H);
	}
	cout << "Вывод хеш-таблицы: " << endl;
	cout << setw(10) << "Название" << setw(10) << "Страницы" << setw(10) << "Год" << endl;
	for (int i = 0; i < m; i++) {
		if (H[i].pages == -1) {
		cout << "Пустая ячейка" << endl; continue;
		}
		cout << setw(10) << H[i].name << setw(10) << H[i].pages << setw(10) << H[i].age << endl;
	}
	cout << "Введите значение ключа (количество страниц) для поиска: ";
	cin >> key;
	books k = search(key, H);
	if (k.pages == -1) { cout << "Элемент не найден" << endl; return -1; }
	cout << "Найденная книга: " << endl;
	cout << setw(10) << "Название" << setw(10) << "Страницы" << setw(10) << "Год" << endl;
	cout << setw(10) << k.name << setw(10) << k.pages << setw(10) << k.age << endl;
	delete[]H;
	delete[]b;
	H = nullptr;
	b = nullptr;
	return 0;
}
void add(int k, int key, books* H)
{
	int i = abs(key % m);
	for (int j=0; H[i].pages != -1; j++)
	{
		i = i + pow(j, 2);
		i %= m;
	}
	H[i] = b[k];
}
books search(int key, books* H)
{
	int i = abs(key % m);
	for (int j=0; H[i].pages != -1; j++)
	{
		i = i + pow(j, 2);
		i %= m;
		if (H[i].pages == key) return H[i];
	}
	return H[i];
}