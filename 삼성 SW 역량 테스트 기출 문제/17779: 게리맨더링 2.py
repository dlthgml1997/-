# https://www.acmicpc.net/problem/17779
import sys
input = sys.stdin.readline

def solution():

    N = int(input())
    capital = []
    for _ in range(N):
        capital.append(list(map(int, input().strip())))

    print(capital)