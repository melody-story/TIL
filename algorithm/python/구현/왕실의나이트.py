location = input()
row = int(location[1])
colum = int(ord(location[0])) - int(ord('a')) + 1 # 알파벳 숫자로 변환
steps = [(-2,1),(-2,-1),(2,1),(2,-1),(1,-2),(1,2),(-1,-2),(-1,2)]

result =0
for step in steps:
    next_row = row + step[1]
    next_colum = colum + step[0]
    if (next_colum >=1 and next_colum<=8 and next_row >=1 and next_row <=8):
        result +=1
print(result)
