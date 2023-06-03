#include <iostream>

using namespace std;

//Вводятся два целочисленных массива, соответственно, длины N1 и N2. Вывести последовательность из элементов обоих массивов расположенных в порядке возрастания. (0 ≤ N1, N2 ≤ 100)
int main() {
	int n1, n2; // <
	cin >> n1 >> n2;

	int* arr1 = new int[n1];
	int* arr2 = new int[n2];

	for (int i = 0; i < n1; i++) {
		cin >> arr1[i];
	}

	for (int i = 0; i < n2; i++) {
		cin >> arr2[i];
	}

	int min = INT_MIN;
	int cmin = INT_MAX; //45 24 33 42 1

	for (int j = 0; j < n1 + n2; j++) {
		for (int i = 0; i < n1; i++) {
			if (arr1[i] < cmin && arr1[i] > min) {
				cmin = arr1[i];
			}
		}

		for (int i = 0; i < n2; i++) {
			if (arr2[i] < cmin && arr2[i] > min) {
				cmin = arr2[i];
			}
		}
		cout << cmin << " "; // Вывод сообщения
		min = cmin;
		cmin = 99999999;
	}
}