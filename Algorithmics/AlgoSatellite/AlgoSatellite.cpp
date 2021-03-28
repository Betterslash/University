#include<iostream>
#include<fstream>
#include<vector>
using namespace std;
struct sateliti {
	int i, j, cati;
};
int x[1111][1111], i, j, n, m, w, range, a, b;
bool isclient[1111][1111];
ifstream fin("in.in");
ofstream fout("out.out");
void afis(int a, int b, int n, int m) { 
	for (int i = a; i <= n; i++) {
		for (int j = b; j <= m; j++)
			cout << x[i][j] << ' '; cout << '\n';
	}
	cout << '\n';
}
void afis2(int a, int b, int n, int m) {
	int s = 0;
	for (int i = a; i <= n; i++) {
		for (int j = b; j <= m; j++)
			if (isclient[i][j]) {
				cout << "1 ";
				s++;
			}
			else
				cout << "0 ";
		cout << '\n';
	}
	cout << '\n';
	cout << s;
	cout << '\n';
}
void pr1(int a, int b, int range) {
	int i, j;
	for (i = a - range; i <= a + range; i++) {
		if (i < 0)
			i = 0;
		for (j = b - range; j <= b + range; j++) {
			if (j < 0)
				j = 0;
			x[i][j]++;
		}
	}
}
void pr3(int a, int b, int range) {
	///afis(10, 10);
	int i, j;
	for (i = a - range; i <= a + range; i++) {
		if (i < 0)
			i = 0;
		for (j = b - range; j <= b + range; j++) {
			if (j < 0)
				j = 0;
			x[i][j]--;
		}
	}
	///afis(10, 10);
}
void pr2(int a, int b, int range) {
	int i, j;
	for (i = a - range; i <= a + range; i++) {
		if (i < 0)
			i = 0;
		for (j = b - range; j <= b + range; j++) {
			if (j < 0)
				j = 0;
			if (isclient[i][j]) {
				isclient[i][j] = false;
				pr3(i, j, range);
			}
		}
	}
	afis(330, 180, 400, 230);
	afis2(330, 180, 400, 230);
}
int main() {
	fin >> w;
	fout << w << '\n';
	while (w > 0) {
		for (i = 0; i <= 1010; i++)
			for (j = 0; j <= 1010; j++)
				x[i][j] = 0;
		w--;
		fin >> range;
		fin >> n;
		for (i = 1; i <= n; i++) {
			fin.get();
			fin >> a;
			fin.get();
			fin >> a >> b;
			isclient[a][b] = true;
			pr1(a, b, range);
		}
		int mx = 0, undei, undej;
		vector<sateliti>q;
		bool ok = true;
		afis(330, 180, 400, 230);
		afis2(0, 0, 1010, 1010);
		while (ok) {
			ok = false;
			mx = 0;
			for (i = 0;i <= 1010;i++)
				for (j = 0; j <= 1010; j++) {
					if (x[i][j] > mx) {
						mx = x[i][j];
						undei = i;
						undej = j;
					}
				}
			if (mx != 0) {
				q.push_back({ undei,undej,mx });
				ok = true;
				pr2(undei, undej, range);
			}
		}
		fout << q.size() << '\n';
		for (i = 0; i < q.size(); i++)
			fout << i + 1 << ": " << q[i].i << ' ' << q[i].j << '\n' << q[i].cati << '\n';
	}
	return 0;
}