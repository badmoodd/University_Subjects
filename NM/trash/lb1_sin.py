#!NM-labs-env/bin/python3


import math
from bigfloat import *

def func(t, k):
    num = t ** (2 * k + 1) * (-1) ** k
    den = math.factorial(2 * k + 1)
    result = num / den 
    # print(f"part {k}: {num}/{den}, result: {result}")
    print("k: {:>2}: {:>5}/{:<65}  = {}".format(k,num,den,result))

    return result


def sin(t, k):
    print()
    sin_sum = BigFloat(0.0)
    for i in range(k+1):
        sin_sum += func(t, i)
    
    print()

    return sin_sum


if __name__ == "__main__":
    x = float(input("(sin)>>> "))
    k = int(input("(k)>>> "))
    with precision(300):
        print(f"sin({x}) = {sin(x, k)}   t[0,{k}]")


