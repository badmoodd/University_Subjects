#include <iostream>
using namespace std;
struct Date
{
	unsigned y;	//год
	unsigned m;	//месяц
	unsigned d;	//день
};
// ввод даты
void getDate(Date& str)
{ 
	cout << "ВВОД:" << endl;
	cin >> str.d >> str.m >> str.y;
}
//вывод даты
void showDate(Date& str)
{
	cout << "ДАТА : " << str.d<<"." << str.m <<"."<< str.y << endl;
}
// расчет номера юлианского дня по юлианскому календарю
int Calendar(Date& str)
{
	int JDN, a, y, m;
	a = (14 - str.m) / 12;
	y = str.y + 4800 - a;
	m = str.m + 12 * a - 3;
	JDN = str.d + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
	cout << "Номер юлианского дня : " << JDN << endl;
	return JDN;
};

int main()
{
	setlocale(LC_ALL, "Russian");
	int d,i;
	bool f = true;
	Date d1,d2;
	cout << "1) Введите первую дату" << endl;
	cout << "2) Введите вторую дату" << endl;
	cout << "3) Показать все даты" << endl;
	cout << "4) Вычисление номера юлианского дня по дате григорианского календаря и разницы между датами" << endl;
	cout << "5) Exit" << endl;
	while (f == true) {
		cout << "Выберити опцию: ";
		cin >> i;
		switch (i)
		{
		case 1:	getDate(d1); break;
		case 2: getDate(d2); break;
		case 3: {showDate(d1); showDate(d2); } break;
			//расчёт разницы двух дат через разницу по модулю
		case 4: {d = fabs(Calendar(d1) - Calendar(d2));
			cout << "Количество дней между датами не включая конечную дату : " << d << endl; }
			break;
		case 5: f = false;
		}
	}

	return 0;
}