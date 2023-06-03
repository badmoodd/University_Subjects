st = input()
lst = st.split(" ")
with open('4out.txt', 'w') as file:
    for i in lst:
        file.write(i)
        print(i)
        file.write(' ')
