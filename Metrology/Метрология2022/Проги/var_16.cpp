#include<iostream>
#include<cmath>
#include<iomanip>

using namespace std;

int main()
{
	double a, b, f = 1, x, y, i, s = 0, k, x1, x2, y1, y2, s1, s2;
	
	cout << "Vvedite a, b, k" << endl;
		cin >> a;
		cin >> b;
		cin >> k;

		for (x = a; x <= b; x += ((b - a) / 10))
		{
			s = 0;
			y = exp(2 * x);

			for (i = 0; i <= k; i++)
			{
				s += pow(2*x,i)/f;
			}

			cout << "x=" << setw(11) << x<<"    "; // Вывод сообщения
			cout << "y=" << setw(11) << y;    // Вывод сообщения
			cout << "     s=" << setw(11) << s << endl;  // Вывод сообщения
		}
		cout << endl;
		cout << "Vvedite x1, x2, y1, y2" << endl;
		cin >> x1;
		cin >> x2;
		cin >> y1;
		cin >> y2;

		s1 = (x1 + y1 + x2 + y2) / 2;

		s2 = s1 + s;

		cout << s2;

		return 0;
}