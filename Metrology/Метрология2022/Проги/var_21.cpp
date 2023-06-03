#include<iostream>
#include<iomanip>
#include<cmath>
using namespace std;
typedef double (*fun)(double, double);
void tabl(double, double, double, fun, fun);
double y(double, double);
double s(double, double);

int main()
{
	cout << setw(8) << "x" << setw(18) << "y" << setw(18) << "s" << endl;
	tabl(-0.8, 0.9, 0.00001, y, s);
	cout << endl;
	return 0;
}

void tabl(double a, double b, double e, fun y1, fun s1)
{
	double rez_y, rez_s, h;
	h = (b - a) / 10;
	while (a<=b)
	{
		rez_y = y1(a, e);
		rez_s = s1(a, e);
		cout << setw(8) << a << setw(18) << rez_y << setw(18) << rez_s << endl;
		a+=h;
	}
}

double y(double a, double e)
{
	return sinh(pow(a, 2));
}

double s(double a, double e)
{
	double c, rez_s, x;
	c = rez_s = x = pow(a, 2);
	int k = 1;
	while (fabs(c) > e)
	{
		c *= pow(a, 4) / (2 * k * (2 * k + 1));
		rez_s += c;
		k++;
	}
	return rez_s;
}



