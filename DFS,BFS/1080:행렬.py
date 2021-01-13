# https://www.acmicpc.net/problem/1080
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
A = []
B = []
cnt = 0

for _ in range(n):
    A.append(list(map(int, input().strip())))

for _ in range(n):
    B.append(list(map(int, input().strip())))

def reverseNum(x, y):
    global cnt
    cnt += 1
    for i in range(x, x+3):
        for j in range(y, y+3):
            if A[i][j] == 0:
                A[i][j] = 1
            else:
                A[i][j] = 0


def solution():
    global cnt
    for i in range(n-2):
        for j in range(m-2):
            if A[i][j] != B[i][j]:
                reverseNum(i, j)

    for i in range(n):
        for j in range(m):
            if A[i][j] != B[i][j]:
                cnt = -1
                print(cnt)
                return

    print(cnt)


solution()