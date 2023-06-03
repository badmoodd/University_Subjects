# Цель программы: реализовать метод итераций Якоби
import numpy as np
import matplotlib.pyplot as plt

def draw_fuction():
	x=np.arange(0,8,0.01)
	y=np.arange(0,8,0.01)
	x,y=np.meshgrid(x,y)
	z=4*x+y-24
	w=2*x+5*y-30
	plt.contour(x,y,z,0)
	plt.contour(x,y,w,0)
def Jacobi(A,b,x_0,n):# Записать количество неизвестных
	x=x_0.copy()
	y=x_0.copy()
	k=0
	key_value=[x]
	while(k<6):
		for i in range(n):
			sum_value=0
			for j in range(n):
				if j!=i:
					sum_value=sum_value+A[i][j]*x[j]
			y[i]=(b[i]-sum_value)/A[i][i]
		x=y.copy()
		k=k+1
		key_value.append(x)
	return x,key_value

A_value=[[:wq,1],[2,5]]
b=[24,30]
x_0=np.zeros(2)
experiment_value=Jacobi(A_value,b,x_0,2)[0]# Матрица входных коэффициентов, правое конечное значение векторной группы
key=Jacobi(A_value,b,x_0,2)[1]
print(key)
x=[]
y=[]
for i in range(7):
	x.append(int(key[i][0]))
	y.append(int(key[i][1]))
print(x)
print(y)

step=np.zeros((13,2))
# Расширение маршрута
for i in range(13):
	if i%2==0:
		step[i]=key[i//2]
	else:
		step[i]=[key[(i+1)//2][0],key[i//2][1]]
print(step)

append_x=[]
append_y=[]
for i in range(13):
	append_x.append(step[i][0])
	append_y.append(step[i][1])
print(append_x)
print(append_y)

for i in range(5):
	plt.scatter(key[i][0],key[i][1], color='b')
	
draw_fuction()
# print(key[])
plt.plot(append_x,append_y,color='r')

plt.show()
