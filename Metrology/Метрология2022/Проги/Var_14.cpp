#include <iostream>
#include <tchar.h>
#include <conio.h>

using namespace std;

int main()
{
			   double n, s = 0, a, m, max1, min1;
			   cout << "Wwedite kolvo elementow: "; // ����� ���������
			   cin >> n;
			   cout << "Wwedite element 1: ";  // ����� ���������
			   cin >> a;
			   min1 = a;
			   max1 = a;
			   for (int i = 1; i < n; i++) {
					cout << "Wwedite element " << i + 1 << ": "; // ����� ���������
					cin >> a;
					if (a > max1) {
							 max1 = a;
					}
					if (a < min1) {
						  min1 = a;
					}
					s += a;
			   }
			   m = s / n;
			   cout << "\nSrednee elementow: " << m;  // ����� ���������
			   cout << "\nMaximum elementow: " << max1;  // ����� ���������
			   cout << "\nMinimum elementow: " << min1;
			   getch();  // ����� ����������� �������
			   return 0;
}
