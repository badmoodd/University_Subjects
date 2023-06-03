#include <iostream> // Подключение стандартной библиотеки
#include <string>

using namespace std;

int numArr[1000], cnt;

int main()  //Основное тело программы
{
	char arr[1000] = { ' ' };
	int currNum = 0;
	int k = 1;
	cout << "Enter a numeric string: "; // Вывод сообщения
	char c = getchar();
	while (c != '\n') {
		arr[k++] = c;
		c = getchar();
	}
	cout << endl; // Перевод каретки
	int q = 1;
	for (int i = k; i >= 0; i--) {
		if (isdigit(arr[i])) {
			currNum += q * (arr[i] - '0');
			q *= 10;
		}
		else if (q != 1) {
			numArr[cnt++] = currNum;
			currNum = 0;
			q = 1;
		}
	}
	
}


