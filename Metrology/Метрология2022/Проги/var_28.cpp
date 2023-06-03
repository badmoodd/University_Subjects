#include <iostream>
#include <cmath>

using namespace std;
int main()
{
    int n, x, y, b;
    cout << "Vvedite razmer massiva ";
    cin >> n;
    double* a = new double [n];
    srand(time(0));
    for (int i = 0; i < n; i++)
    {
        //cin >> a[i];
        a[i] = rand() % 20 - 10;
        cout << a[i] << " ";
    }
    cout << endl;
    for (x = 0; a[x] <= 0 && x < n; x++);
    for (y = n - 1; a[y] >= 0 && y >= 0; y--);
    for (long i = 1; i <= (y - x) / 2; i++)
    {
        b = a[x + i];
        a[x + i] = a[y - i];
        a[y - i] = b;
    }
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << " ";
    }
    delete[] a;
    return 0;
}