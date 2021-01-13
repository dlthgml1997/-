# https://www.acmicpc.net/problem/7576
from collections import deque

n, m = map(int, input().split())
box = []

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

def bfs(queue):
    day = -1
    while queue:
        day += 1
        for _ in range(len(queue)):
            x, y = queue.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < m and 0 <= ny < n and box[nx][ny] == 0:
                    box[nx][ny] = box[x][y] + 1
                    queue.append([nx, ny])

    for i in box:
        if 0 in i:
            return -1
    return day

def solution():
    queue = deque()
    for i in range(m):
        box.append(list(map(int, input().split())))
        for j in range(n):
            if box[i][j] == 1:
                queue.append([i, j])
    answer = bfs(queue)
    print(answer)

solution()