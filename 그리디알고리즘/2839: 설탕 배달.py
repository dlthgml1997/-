# https://www.acmicpc.net/problem/2839
def solution():

    n = int(input())

    for i in range(n // 3 + 1):
        for j in range(n // 5 + 1):
            answer = 3 * i + 5 * j
            if answer == n:
                print(i+j)
                return

    print(-1)

solution()