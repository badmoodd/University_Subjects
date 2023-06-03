#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;
int main()
{
	double x,y,s,h,a,b,c;
	int n, k;
	cout << "Введите значение k:";
	cin >> k;
	cout << "Введите значение a,   // Вывод сообщения
	cin >> a;
	cin >> b;
	h = (b - a) / 10;
	cout << setw(15) << "x" << setw(15) << "y" << setw(15) << "s" << endl;  // Вывод сообщения
	for ( x = a ;  x < b + h/2 ; x +=h )
	{
		s = 1;
		c = 1;
		y = sin(x) / x;
		for (n = 1; n < (k + 1); n++)
		{
			c *= -pow(x, 2) / (2 * n * (2 * n + 1));
			s += c;
		}
		cout << setw(15) << x << setw(15) << y << setw(15) << s << endl;
	}
 return 0;
}