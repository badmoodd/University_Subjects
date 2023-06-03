#include <iostream>
#include <fstream>
#include <string>
#include <Windows.h> 
using namespace std;

string path = "laba2.txt";

void  open()

{
    setlocale(LC_ALL, "ru");

    ofstream fout;              // файловый вывод 

    fout.open(path);
    if (!fout.is_open()) // проверяем на открытие 
    {
        cout << "Ошибка открытия файла " << endl;
    }
    else
    {
        for (int i = 0; i < 3; i++) // вводим 3 числа 
        {
            cout << "Введите число" << endl;
            int a;
            cin >> a;
            fout << a; // записываем в файл 
            fout << "\n";
        }

    }

    fout.close(); // закрываем файл 
}

void end()
{
    cout << "Программа завевершена) Хорошего настроения !!!";

}

int main()
{
    setlocale(LC_ALL, "ru");
    while (true)
    {
        cout << "Введите действие:" << endl << "1-Добавление текста в конец файла" << endl << "2-Выход" << endl;
        int x;
        cin >> x;
        switch (x)
        {
        case 1: open(); break;
        case 2: end(); 
            return 0;
        default: cout << "Некорректное число\n";

        }
        cout << "Программа не получилась";
    }
}