n = int(input())
space = []
for _ in range(n):
    space.append(list(map(int, input().strip())))

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

visited = [[0] * n for _ in range(n)]
total_apt = 0
answer = []

def bfs(x, y):
    global answer
    global total_apt
    cnt = 1
    total_apt += 1
    queue = []
    queue.append([x, y])
    while queue:
        x, y = queue.pop(0)
        visited[x][y] = 1
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and space[nx][ny] == 1 and visited[nx][ny] == 0:
                cnt += 1
                visited[nx][ny] = 1
                queue.append([nx, ny])

    answer.append(cnt)


def find_one(x, y):
    queue = []
    queue.append([x, y])
    visited_one = [[0] * n for _ in range(n)]
    while queue:
        x, y = queue.pop(0)
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if space[nx][ny] == 1 and visited[nx][ny] == 0:
                    bfs(nx, ny)
                if visited_one[nx][ny] == 0:
                    queue.append([nx, ny])
                visited_one[nx][ny] = 1

def solution():
    find_one(0, 0)

    print(total_apt)
    answer.sort()
    for a in answer:
        print(a)

solution()


