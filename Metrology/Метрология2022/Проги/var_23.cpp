#include <iostream>
#include <fstream>
#include <string>
#include <Windows.h> 
using namespace std;

string path = "laba2.txt";

void  open()

{
    setlocale(LC_ALL, "ru");

    ofstream fout;              // �������� ����� 

    fout.open(path);
    if (!fout.is_open()) // ��������� �� �������� 
    {
        cout << "������ �������� ����� " << endl;
    }
    else
    {
        for (int i = 0; i < 3; i++) // ������ 3 ����� 
        {
            cout << "������� �����" << endl;
            int a;
            cin >> a;
            fout << a; // ���������� � ���� 
            fout << "\n";
        }

    }

    fout.close(); // ��������� ���� 
}

void end()
{
    cout << "��������� �����������) �������� ���������� !!!";

}

int main()
{
    setlocale(LC_ALL, "ru");
    while (true)
    {
        cout << "������� ��������:" << endl << "1-���������� ������ � ����� �����" << endl << "2-�����" << endl;
        int x;
        cin >> x;
        switch (x)
        {
        case 1: open(); break;
        case 2: end(); 
            return 0;
        default: cout << "������������ �����\n";

        }
        cout << "��������� �� ����������";
    }
}