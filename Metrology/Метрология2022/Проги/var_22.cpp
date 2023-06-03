#include <iostream>
#include <cmath>

using namespace std;

int main()
{

    cout << "Введите размер массива";

    int n;
    cin >> n;
    int* mas = new int[n];
    int i;

    for (i = 0; i < n; i++)
    {
        cout << "elemant: " << i + 1 << ":";
        cin >> mas[i];
        cout << endl;
    }

    cout << endl;

    for (i = 0; i < n / 2; i++)
    {
        cout << mas[i] << ' ' << mas[n - i - 1] << ' ';
    }

    if (n % 2 == 1)
    {
        cout << mas[n / 2];
    }

    cout << endl;
    delete mas;
}
