#include <iostream>
#include <iomanip>
using namespace std;
int main() {
	double  **s, smin, smax;
	int i, j, n, m, h, k, r;
	r = 0;
	h = 0;
	k = 0;
	cout << "Vvedite razmer n i m" << endl;
	cin >> n >> m;
	s = new double*[n];
	for (i = 0; i < n; i++)
		s[i] = new double[m];
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			cout << "Vvedite s[" << i << "][" << j << "]:";
			cin >> s[i][j];
		}
		cout << endl;
	}
	smin = s[0][0];
	smax = s[0][0];
	cout << " " << endl;
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			cout << setw(9) << s[i][j] << " ";
		}
		cout << endl;
	}
	cout << " " << endl;
  	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (smin > s[i][j]) {
				k = j;
				smin = s[i][k]; 
			}
			if (smax < s[i][j]) {
				h = j;
				smax = s[i][h];
			}
		}
	}
	for (i = 0; i < n; i++) {
			r = s[i][k];
			s[i][k] = s[i][h];
			s[i][h] = r;
	}
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			cout << setw(9) << s[i][j] << " ";
		}
		cout << endl;
	}
	for (i = 0; i < n; i++)
		delete []s[i];
	delete []s;
	return 0;
}
