#include <iostream>
using namespace std;
struct Date
{
	unsigned y;	//���
	unsigned m;	//�����
	unsigned d;	//����
};
// ���� ����
void getDate(Date& str)
{ 
	cout << "����:" << endl;
	cin >> str.d >> str.m >> str.y;
}
//����� ����
void showDate(Date& str)
{
	cout << "���� : " << str.d<<"." << str.m <<"."<< str.y << endl;
}
// ������ ������ ���������� ��� �� ���������� ���������
int Calendar(Date& str)
{
	int JDN, a, y, m;
	a = (14 - str.m) / 12;
	y = str.y + 4800 - a;
	m = str.m + 12 * a - 3;
	JDN = str.d + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
	cout << "����� ���������� ��� : " << JDN << endl;
	return JDN;
};

int main()
{
	setlocale(LC_ALL, "Russian");
	int d,i;
	bool f = true;
	Date d1,d2;
	cout << "1) ������� ������ ����" << endl;
	cout << "2) ������� ������ ����" << endl;
	cout << "3) �������� ��� ����" << endl;
	cout << "4) ���������� ������ ���������� ��� �� ���� �������������� ��������� � ������� ����� ������" << endl;
	cout << "5) Exit" << endl;
	while (f == true) {
		cout << "�������� �����: ";
		cin >> i;
		switch (i)
		{
		case 1:	getDate(d1); break;
		case 2: getDate(d2); break;
		case 3: {showDate(d1); showDate(d2); } break;
			//������ ������� ���� ��� ����� ������� �� ������
		case 4: {d = fabs(Calendar(d1) - Calendar(d2));
			cout << "���������� ���� ����� ������ �� ������� �������� ���� : " << d << endl; }
			break;
		case 5: f = false;
		}
	}

	return 0;
}