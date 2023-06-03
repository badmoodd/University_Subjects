#include <iostream>
using namespace std;
int i, pluse1, pluse2 , n, k, l[2], result = 1;
double *a;
int main()
{

    cout << "Vvedite razmer massiva: "; // Вывод сообщения
    cin >> n; a = new double[n];
    for (i = 0; i < n; i++)
    {
        cout << "Vvedite " << i + 1 << " element massiva: ";
        cin >> a[i];
        cout << endl; // Вывод сообщения
    }
    cout << endl;  // Перевод каретки
    for (i = 0; i < n; i++)
    {
        cout << a[i] << " ";
    }
    cout << endl; // Вывод сообщения
    k = 0;
    for (i = n - 1; i >= 0; i--)
    {
        if (a[i] > 0 && k < 2)
        {
            l[k] = i;
            k++;
        }
    }
    if (k == 0)
    {
        cout << "Polozhitelnih elemntov net"; return 1;
    }
    if (l[0] <= l[1])
        for (i = l[0] + 1; i < l[1]; i++)
            result *= a[i];
    else
        for (i = l[0] - 1; i > l[1]; i--)
            result *= a[i];
    cout << result;  // Вывод сообщения
    delete[]a;
    return 0;
}