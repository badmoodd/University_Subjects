#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    double arr[100];
	int a, pol=-1, otr=-1;
	int i,j,s,h;
	cout << "Vvedite razmer masiwa : ";  // ����� ���������
	cin >> a;
	for (int i = 0; i < a; i++)
	  {
		cout << "Vvedite element arr[" << i << "] = ";  // ����� ���������
		cin >> arr[i];
		if (arr[i] < 0)
			otr = i;
	  }
	for (i = 0; i < a; i++)
	{
		if (arr[i] > 0)
		{
			pol = i; 
			break;  // ����� �� �����
		}
	}
	if (pol < 0 || otr < 0)
	{
		cout << "Newozmojno";
		return 1;
	}
	cout <<"Indeks perwogo poloj. = " << pol << endl;
	cout <<"Indeks poslednego otric. = " << otr << endl;
	s = 1;
	for (i = pol + 1; i < (pol+otr)/2; i++)
	  {
		j = otr - s;
		h = arr[i];
		arr[i]=arr[j];
		arr[j] = h;
		s++;
	  }
	cout << "Konechni vid matrici : ";
	for (i = 0; i < a; i++)
	  {
		cout << arr[i] << " ";
	  }
	return 0;
}