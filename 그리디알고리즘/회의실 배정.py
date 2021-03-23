# https://www.acmicpc.net/problem/1931
n = int(input())

times = []
for _ in range(n):
    a, b = map(int, input().split())
    times.append([a, b])

times.sort()
times.sort(key = lambda x: x[1])

end_time = 0
count = 0
i = 0

for st, et in times:
    if i <= st and st >= end_time:
        count += 1
        end_time = et
        i = et

print(count)
